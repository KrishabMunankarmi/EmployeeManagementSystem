package com.EmployeeSystem.controller;

import com.EmployeeSystem.config.DbConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadProfileData(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadProfileData(request, response);
    }

    private void loadProfileData(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("empid") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int empId = (Integer) session.getAttribute("empid");

        try (Connection conn = DbConfig.getDbConnection()) {
            String sql = "SELECT e.EmpID, e.FullName, e.Age, e.ContactNo, " +
                       "d.DepartmentName, p.PositionTitle, c.ContractPeriod " +
                       "FROM employee e " +
                       "LEFT JOIN EmployeeDepartment ed ON e.EmpID = ed.EmpID " +
                       "LEFT JOIN department d ON ed.DepartmentID = d.DepartmentID " +
                       "LEFT JOIN employeedepartmentposition edp ON e.EmpID = edp.EmpID " +
                       "LEFT JOIN position p ON edp.PositionID = p.PositionID " +
                       "LEFT JOIN employeecontract ec ON e.EmpID = ec.EmpID " +
                       "LEFT JOIN contract c ON ec.ContractID = c.ContractID " +
                       "WHERE e.EmpID = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, empId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    request.setAttribute("empid", rs.getInt("EmpID"));
                    request.setAttribute("fullName", rs.getString("FullName"));
                    request.setAttribute("age", rs.getInt("Age"));
                    request.setAttribute("contact", rs.getString("ContactNo"));
                    request.setAttribute("department", rs.getString("DepartmentName"));
                    request.setAttribute("position", rs.getString("PositionTitle"));
                    request.setAttribute("contractPeriod", rs.getString("ContractPeriod"));
                }
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Error loading profile data");
        }

        request.getRequestDispatcher("/WEB-INF/pages/Profile.jsp").forward(request, response);
    }
}