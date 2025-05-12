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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if ("admin".equalsIgnoreCase(name) && "admin".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("empid", 0);
            session.setAttribute("fullName", "Administrator");
            session.setAttribute("role", "admin");
            CookieUtil.addCookie(response, "role", "admin", 5 * 60);
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return;
        }

        try (Connection conn = DbConfig.getDbConnection()) {
            String sql = "SELECT EmpID, FullName, Password FROM employee WHERE FullName = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("Password");

                if (PasswordUtil.verifyPassword(password, storedHash)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("empid", rs.getInt("EmpID"));
                    session.setAttribute("fullName", rs.getString("FullName"));
                    session.setAttribute("role", "employee");
                    CookieUtil.addCookie(response, "role", "employee", 5 * 60);
                    response.sendRedirect(request.getContextPath() + "/home");
                } else {
                    request.setAttribute("error", "Invalid username or password");
                    request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
            }
        } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            request.setAttribute("error", "Login failed. Please try again.");
            request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
    }
}
