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

@WebServlet("/adminemployee")
public class AdminEmployeeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EmployeeSystemModel> employees = new ArrayList<>();

        try (Connection conn = DbConfig.getDbConnection()) {
            String sql = "SELECT " +
                    "e.EmpID, e.FullName, e.ContactNo, " +
                    "d.DepartmentName, p.PositionTitle, c.ContractPeriod " +
                    "FROM employee e " +
                    "LEFT JOIN employeedepartment ed ON e.EmpID = ed.EmpID " +
                    "LEFT JOIN department d ON ed.DepartmentID = d.DepartmentID " +
                    "LEFT JOIN employeedepartmentposition edp ON e.EmpID = edp.EmpID " +
                    "LEFT JOIN position p ON edp.PositionID = p.PositionID " +
                    "LEFT JOIN employeecontract ec ON e.EmpID = ec.EmpID " +
                    "LEFT JOIN contract c ON ec.ContractID = c.ContractID";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
            e.printStackTrace();
        }

        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/WEB-INF/pages/AdminEmployee.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deleteId = request.getParameter("empid");

        if (deleteId != null && !deleteId.isEmpty()) {
            try (Connection conn = DbConfig.getDbConnection()) {
                conn.setAutoCommit(false);

                try {
                    int empId = Integer.parseInt(deleteId);

                    try (PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM employeedepartment WHERE EmpID = ?")) {
                        stmt1.setInt(1, empId);
                        stmt1.executeUpdate();
                    }

                    try (PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM employeecontract WHERE EmpID = ?")) {
                        stmt2.setInt(1, empId);
                        stmt2.executeUpdate();
                    }

                    try (PreparedStatement stmt3 = conn.prepareStatement("DELETE FROM employeedepartmentposition WHERE EmpID = ?")) {
                        stmt3.setInt(1, empId);
                        stmt3.executeUpdate();
                    }

                    try (PreparedStatement stmt4 = conn.prepareStatement("DELETE FROM employee WHERE EmpID = ?")) {
                        stmt4.setInt(1, empId);
                        stmt4.executeUpdate();
                    }

                    conn.commit();
                } catch (Exception e) {
                    conn.rollback();
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect(request.getContextPath() + "/adminemployee");
    }
}
