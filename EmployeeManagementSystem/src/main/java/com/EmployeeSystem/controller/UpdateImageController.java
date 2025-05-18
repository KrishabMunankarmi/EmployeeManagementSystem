package com.EmployeeSystem.controller;

import com.EmployeeSystem.config.DbConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/updateimage")
@MultipartConfig // Enables file upload support for multipart/form-data
public class UpdateImageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Folder inside webapp where images are stored
    private static final String IMAGE_DIR = "resources";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get existing session or null if none exists
        HttpSession session = request.getSession(false);
        // If no session or no empid attribute, redirect to login page
        if (session == null || session.getAttribute("empid") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Retrieve employee ID from session
        int empId = (Integer) session.getAttribute("empid");

        // Get the uploaded file part from the form input named "profileImage"
        Part filePart = request.getPart("profileImage");

        // Check if a file was uploaded
        if (filePart != null && filePart.getSize() > 0) {
            // Create a unique filename to avoid collisions, prefixing with timestamp
            String fileName = System.currentTimeMillis() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            // Get the absolute path to the root of the webapp
            String appPath = request.getServletContext().getRealPath("");

            // Define the directory path where images will be saved
            String uploadPath = appPath + File.separator + IMAGE_DIR;

            // Create the directory if it doesn't exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            // Full path where the file will be saved
            String fullPath = uploadPath + File.separator + fileName;

            // Save the uploaded file to the disk
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, Paths.get(fullPath), StandardCopyOption.REPLACE_EXISTING);
            }

            // Store the relative path to the image, to be saved in the database
            String relativePath = IMAGE_DIR + "/" + fileName;

            // Save the relative image path to the employee record in the database
            try (Connection conn = DbConfig.getDbConnection()) {
                String sql = "UPDATE employee SET ImagePath = ? WHERE EmpID = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, relativePath);
                    stmt.setInt(2, empId);
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // After updating, redirect the user back to their profile page
            response.sendRedirect(request.getContextPath() + "/profile");
        } else {
            // If no file was uploaded, redirect back with an error query parameter
            response.sendRedirect(request.getContextPath() + "/profile?error=NoFile");
        }
    }
}
