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

    // Handle GET request to view profile
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadProfileData(request, response);
    }

    // Handle POST request (optional form submissions can also reload profile data)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadProfileData(request, response);
    }

    // Loads profile data for the currently logged-in employee
    private void loadProfileData(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the current session without creating a new one
        HttpSession session = request.getSession(false);

        // If session is invalid or employee is not logged in, redirect to login
        if (session == null || session.getAttribute("empid") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Get employee ID from session
        int empId = (Integer) session.getAttribute("empid");

        // Query the database to fetch profile information
        try (Connection conn = DbConfig.getDbConnection()) {
            String sql = "SELECT e.EmpID, e.FullName, e.Age, e.ContactNo, e.ImagePath, " +
                         "d.DepartmentName, p.PositionTitle, c.ContractPeriod " +
                         "FROM employee e " +
                         "LEFT JOIN EmployeeDepartment ed ON e.EmpID = ed.EmpID " +
                         "LEFT JOIN department d ON ed.DepartmentID = d.DepartmentID " +
                         "LEFT JOIN employeedepartmentposition edp ON e.EmpID = edp.EmpID " +
                         "LEFT JOIN position p ON edp.PositionID = p.PositionID " +
                         "LEFT JOIN employeecontract ec ON e.EmpID = ec.EmpID " +
                         "LEFT JOIN contract c ON ec.ContractID = c.ContractID " +
                         "WHERE e.EmpID = ?";

            // Prepare and execute the SQL query
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, empId);
                ResultSet rs = stmt.executeQuery();

                // If employee data is found, set it as request attributes
                if (rs.next()) {
                    request.setAttribute("empid", rs.getInt("EmpID"));
                    request.setAttribute("fullName", rs.getString("FullName"));
                    request.setAttribute("age", rs.getInt("Age"));
                    request.setAttribute("contact", rs.getString("ContactNo"));
                    request.setAttribute("department", rs.getString("DepartmentName"));
                    request.setAttribute("position", rs.getString("PositionTitle"));
                    request.setAttribute("contractPeriod", rs.getString("ContractPeriod"));

                    // Set profile image path, use default if none is stored
                    String imagePath = rs.getString("ImagePath");
                    if (imagePath == null || imagePath.trim().isEmpty()) {
                        imagePath = "resources/Man.png"; // Default fallback image
                    }
                    request.setAttribute("imagePath", imagePath);
                }
            }

        } catch (SQLException e) {
            // Handle SQL exception and show error message
            request.setAttribute("error", "Error loading profile data");
            e.printStackTrace();
        }

        // Forward request to the profile JSP page
        request.getRequestDispatcher("/WEB-INF/pages/Profile.jsp").forward(request, response);
    }
}
