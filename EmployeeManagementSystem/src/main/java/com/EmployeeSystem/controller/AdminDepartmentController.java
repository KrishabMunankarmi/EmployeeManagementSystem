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

@WebServlet(urlPatterns = {"/admindepartment"})
public class AdminDepartmentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AdminDepartmentService service = new AdminDepartmentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                // Showing add form
                request.getRequestDispatcher("/WEB-INF/pages/AddDepartment.jsp").forward(request, response);
                break;
            case "edit":
                // Showing edit form
                int editId = Integer.parseInt(request.getParameter("id"));
                DepartmentModel dept = service.getDepartmentById(editId);
                request.setAttribute("department", dept);
                request.getRequestDispatcher("/WEB-INF/pages/AddDepartment.jsp").forward(request, response);
                break;
            default:
                // Listing departments (with optional search)
                String searchTerm = request.getParameter("search");
                List<DepartmentModel> departments;

                if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                    departments = service.searchDepartmentsByName(searchTerm);
                } else {
                    departments = service.getAllDepartments();
                }

                request.setAttribute("departments", departments);
                request.getRequestDispatcher("/WEB-INF/pages/AdminDepartment.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equalsIgnoreCase(action)) {
            // Handle delete request
            try {
                int deleteId = Integer.parseInt(request.getParameter("id"));
                service.deleteDepartment(deleteId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            response.sendRedirect("admindepartment");
            return;
        }

        // Handle add or update department
        String deptName = request.getParameter("departmentName");
        String idStr = request.getParameter("departmentID");

        if (idStr == null || idStr.isEmpty()) {
            // Adding department
            service.addDepartment(deptName);
        } else {
            // Updating department
            int id = Integer.parseInt(idStr);
            service.updateDepartment(id, deptName);
        }

        response.sendRedirect("admindepartment");
    }
}
