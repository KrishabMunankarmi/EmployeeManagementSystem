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

        // Check if user session is valid
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("empid") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Get employee ID from session
        int empId = (Integer) session.getAttribute("empid");

        // Fetch employee details
        EmployeeSystemModel employee = profileService.getEmployeeById(empId);

        // Set attributes to pre-fill form fields
        if (employee != null) {
            request.setAttribute("fullName", employee.getName());
            request.setAttribute("age", employee.getAge());
            request.setAttribute("contact", employee.getContact());
        }

        // Forward to EditProfile.jsp
        request.getRequestDispatcher("/WEB-INF/pages/EditProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if user session is valid
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("empid") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Retrieve form data
        int empId = (Integer) session.getAttribute("empid");
        String fullName = request.getParameter("fullName");
        int age = Integer.parseInt(request.getParameter("age"));
        String contact = request.getParameter("contact");

        // Update profile
        boolean success = profileService.updateEmployeeProfile(empId, fullName, age, contact);

        if (success) {
            // Update session and redirect on success
            session.setAttribute("fullName", fullName);
            session.setAttribute("successMessage", "Profile updated successfully!");
            response.sendRedirect(request.getContextPath() + "/profile");
        } else {
            // Set error message and return to edit page
            request.setAttribute("errorMessage", "Failed to update profile.");
            request.getRequestDispatcher("/WEB-INF/pages/EditProfile.jsp").forward(request, response);
        }
    }
}
