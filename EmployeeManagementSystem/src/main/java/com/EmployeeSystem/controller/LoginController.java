package com.EmployeeSystem.controller;

import com.EmployeeSystem.config.DbConfig;
import com.EmployeeSystem.util.CookieUtil;
import com.EmployeeSystem.util.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet(urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle POST request for login
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get user input from login form
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        // Hardcoded admin login check
        if ("admin".equalsIgnoreCase(name) && "admin".equals(password)) {
            // Create session and set admin attributes
            HttpSession session = request.getSession();
            session.setAttribute("empid", 0);
            session.setAttribute("fullName", "Administrator");
            session.setAttribute("role", "admin");

            // Set role cookie for admin
            CookieUtil.addCookie(response, "role", "admin", 5 * 60);

            // Redirect to admin dashboard
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return;
        }

        // Attempt to authenticate as a regular employee
        try (Connection conn = DbConfig.getDbConnection()) {
            String sql = "SELECT EmpID, FullName, Password FROM employee WHERE FullName = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            // Check if employee with given name exists
            if (rs.next()) {
                String storedHash = rs.getString("Password");

                // Verify password using hashing utility
                if (PasswordUtil.verifyPassword(password, storedHash)) {
                    // Create session and set employee attributes
                    HttpSession session = request.getSession();
                    session.setAttribute("empid", rs.getInt("EmpID"));
                    session.setAttribute("fullName", rs.getString("FullName"));
                    session.setAttribute("role", "employee");

                    // Set role cookie for employee
                    CookieUtil.addCookie(response, "role", "employee", 5 * 60);

                    // Redirect to employee home
                    response.sendRedirect(request.getContextPath() + "/home");
                } else {
                    // Incorrect password
                    request.setAttribute("error", "Invalid username or password");
                    request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
                }
            } else {
                // No user found with given name
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
            }
        } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            // Handle database or password verification errors
            request.setAttribute("error", "Login failed. Please try again.");
            request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
        }
    }

    // Handle GET request by showing the login form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
    }
}
