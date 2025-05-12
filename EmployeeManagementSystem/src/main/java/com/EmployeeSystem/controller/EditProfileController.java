package com.EmployeeSystem.controller;

import com.EmployeeSystem.model.EmployeeSystemModel;
import com.EmployeeSystem.service.ProfileService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/editprofile")
public class EditProfileController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final ProfileService profileService = new ProfileService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("empid") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int empId = (Integer) session.getAttribute("empid");
        EmployeeSystemModel employee = profileService.getEmployeeById(empId);

        if (employee != null) {
            request.setAttribute("fullName", employee.getName());
            request.setAttribute("age", employee.getAge());
            request.setAttribute("contact", employee.getContact());
        }

        request.getRequestDispatcher("/WEB-INF/pages/EditProfile.jsp").forward(request, response);
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
        String fullName = request.getParameter("fullName");
        int age = Integer.parseInt(request.getParameter("age"));
        String contact = request.getParameter("contact");

        boolean success = profileService.updateEmployeeProfile(empId, fullName, age, contact);

        if (success) {
            session.setAttribute("fullName", fullName); // Update session with new full name
            session.setAttribute("successMessage", "Profile updated successfully!");
            response.sendRedirect(request.getContextPath() + "/profile"); // Redirect to profile page
        } else {
            request.setAttribute("errorMessage", "Failed to update profile.");
            request.getRequestDispatcher("/WEB-INF/pages/EditProfile.jsp").forward(request, response);
        }
    }
}
