package com.EmployeeSystem.controller;

import com.EmployeeSystem.service.RegisterService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final RegisterService registerService = new RegisterService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/Register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectURL = request.getContextPath() + "/register";

        try {
            boolean success = registerService.registerEmployee(request);
            if (success) {
                response.sendRedirect(redirectURL + "?success=true");
            } else {
                response.sendRedirect(redirectURL + "?error=1");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            response.sendRedirect(redirectURL + "?error=3");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(redirectURL + "?error=2");
        }
    }
}