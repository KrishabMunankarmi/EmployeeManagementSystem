package com.EmployeeSystem.controller;

import com.EmployeeSystem.model.EmployeeSystemModel;
import com.EmployeeSystem.config.DbConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employees")
public class EmployeesController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handles GET request to list employees (with optional search)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EmployeeSystemModel> employees = new ArrayList<>();
        String search = request.getParameter("search");

        try (Connection conn = DbConfig.getDbConnection()) {
            // Build SQL query to join employee-related tables
            StringBuilder sql = new StringBuilder(
                "SELECT " +
                "    e.EmpID, " +
                "    e.FullName, " +
                "    e.ContactNo, " +
                "    d.DepartmentName, " +
                "    p.PositionTitle, " +
                "    c.ContractPeriod " +
                "FROM employee e " +
                "LEFT JOIN employeedepartment ed ON e.EmpID = ed.EmpID " +
                "LEFT JOIN department d ON ed.DepartmentID = d.DepartmentID " +
                "LEFT JOIN employeedepartmentposition edp ON e.EmpID = edp.EmpID " +
                "LEFT JOIN position p ON edp.PositionID = p.PositionID " +
                "LEFT JOIN employeecontract ec ON e.EmpID = ec.EmpID " +
                "LEFT JOIN contract c ON ec.ContractID = c.ContractID"
            );

            // Check if a search term is provided
            boolean hasSearch = search != null && !search.trim().isEmpty();
            if (hasSearch) {
                sql.append(" WHERE e.FullName LIKE ?");
            }

            // Prepare statement and bind parameter if searching
            try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
                if (hasSearch) {
                    stmt.setString(1, "%" + search.trim() + "%");
                }

                // Execute query and populate employee list
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    EmployeeSystemModel emp = new EmployeeSystemModel();
                    emp.setEmpid(rs.getInt("EmpID"));
                    emp.setName(rs.getString("FullName"));
                    emp.setContact(rs.getString("ContactNo"));
                    emp.setDepartment(rs.getString("DepartmentName"));
                    emp.setPosition(rs.getString("PositionTitle"));
                    emp.setConperiod(rs.getString("ContractPeriod"));
                    employees.add(emp);
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // Log error
        }

        // Set employee list in request and forward to JSP
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/WEB-INF/pages/Employees.jsp").forward(request, response);
    }

    // Delegate POST requests to doGet (for search functionality)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
