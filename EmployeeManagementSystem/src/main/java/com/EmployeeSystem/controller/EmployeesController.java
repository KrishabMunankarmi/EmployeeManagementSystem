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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EmployeeSystemModel> employees = new ArrayList<>();

        try (Connection conn = DbConfig.getDbConnection()) {
            String sql = "SELECT \r\n"
                    + "    e.EmpID,\r\n"
                    + "    e.FullName,\r\n"
                    + "    e.ContactNo,\r\n"
                    + "    d.DepartmentName,\r\n"
                    + "    p.PositionTitle,\r\n"
                    + "    c.ContractPeriod\r\n"
                    + "FROM employee e\r\n"
                    + "LEFT JOIN employeedepartment ed ON e.EmpID = ed.EmpID\r\n"
                    + "LEFT JOIN department d ON ed.DepartmentID = d.DepartmentID\r\n"
                    + "LEFT JOIN employeedepartmentposition edp ON e.EmpID = edp.EmpID\r\n"
                    + "LEFT JOIN position p ON edp.PositionID = p.PositionID\r\n"
                    + "LEFT JOIN employeecontract ec ON e.EmpID = ec.EmpID\r\n"
                    + "LEFT JOIN contract c ON ec.ContractID = c.ContractID;";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    EmployeeSystemModel emp = new EmployeeSystemModel();
                    emp.setEmpid(rs.getInt("EmpID"));
                    emp.setName(rs.getString("FullName"));
                    emp.setContact(rs.getString("ContactNo"));
                    emp.setDepartment(rs.getString("DepartmentName"));
                    emp.setPosition(rs.getString("PositionTitle"));  // Corrected mapping
                    emp.setConperiod(rs.getString("ContractPeriod"));  // Corrected mapping
                    employees.add(emp);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/WEB-INF/pages/Employees.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
