package com.EmployeeSystem.controller;

import com.EmployeeSystem.model.DepartmentModel;
import com.EmployeeSystem.service.AdminDepartmentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/department"})
public class DepartmentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminDepartmentService service = new AdminDepartmentService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get action parameter
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                // Show form for new department
                request.getRequestDispatcher("/WEB-INF/pages/DepartmentForm.jsp").forward(request, response);
                break;
            case "edit":
                // Load department for editing
                int editId = Integer.parseInt(request.getParameter("id"));
                DepartmentModel dept = service.getDepartmentById(editId);
                request.setAttribute("department", dept);
                request.getRequestDispatcher("/WEB-INF/pages/DepartmentForm.jsp").forward(request, response);
                break;
            case "delete":
                // Delete department by ID
                int deleteId = Integer.parseInt(request.getParameter("id"));
                service.deleteDepartment(deleteId);
                response.sendRedirect("department");
                break;
            default:
                // List departments (with optional search)
                String searchTerm = request.getParameter("search");
                List<DepartmentModel> departments;

                if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                    departments = service.searchDepartmentsByName(searchTerm);
                } else {
                    departments = service.getAllDepartments();
                }

                request.setAttribute("departments", departments);
                request.getRequestDispatcher("/WEB-INF/pages/Department.jsp").forward(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String deptName = request.getParameter("departmentName");
        String idStr = request.getParameter("departmentID");

        if (idStr == null || idStr.isEmpty()) {
            // Add new department
            service.addDepartment(deptName);
        } else {
            // Update existing department
            int id = Integer.parseInt(idStr);
            service.updateDepartment(id, deptName);
        }

        // Redirect to department list
        response.sendRedirect("department");
    }
}
