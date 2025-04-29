package com.EmployeeSystem.controller;

import com.EmployeeSystem.config.DbConfig;
import com.EmployeeSystem.util.PasswordUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet("/changepassword")
public class ChangePasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("empid") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/pages/ChangePassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("empid") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int empId = (Integer) session.getAttribute("empid");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "New passwords do not match");
            request.getRequestDispatcher("/WEB-INF/pages/ChangePassword.jsp").forward(request, response);
            return;
        }

        try (Connection conn = DbConfig.getDbConnection()) {
            String sql = "SELECT Password FROM employee WHERE EmpID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, empId);
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    String storedHash = rs.getString("Password");
                    if (PasswordUtil.verifyPassword(currentPassword, storedHash)) {
                        String newHash = PasswordUtil.hashPassword(newPassword);
                        updatePassword(conn, empId, newHash);
                        request.setAttribute("success", "Password changed successfully");
                    } else {
                        request.setAttribute("error", "Current password is incorrect");
                    }
                }
            }
        } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            request.setAttribute("error", "Error changing password");
        }
        
        request.getRequestDispatcher("/WEB-INF/pages/ChangePassword.jsp").forward(request, response);
    }

    private void updatePassword(Connection conn, int empId, String newHash) throws SQLException {
        String sql = "UPDATE employee SET Password = ? WHERE EmpID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newHash);
            stmt.setInt(2, empId);
            stmt.executeUpdate();
        }
    }
}