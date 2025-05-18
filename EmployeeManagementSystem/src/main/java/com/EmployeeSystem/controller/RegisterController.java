package com.EmployeeSystem.controller;

import com.EmployeeSystem.service.RegisterService;
import com.EmployeeSystem.util.ImageUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/register")
@MultipartConfig // Enables support for multipart/form-data requests (file upload)
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Create instance of RegisterService to handle employee registration logic
    private final RegisterService registerService = new RegisterService();

    // Create instance of ImageUtil to handle image upload and processing
    private final ImageUtil imageUtil = new ImageUtil();

    // Handles GET requests to show the registration form
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the Register.jsp page inside WEB-INF
        request.getRequestDispatcher("/WEB-INF/pages/Register.jsp").forward(request, response);
    }

    // Handles POST requests when the registration form is submitted
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Define the redirect URL back to the registration page
        String redirectURL = request.getContextPath() + "/register";

        try {
            // Retrieve the uploaded image file from the multipart request
            Part filePart = request.getPart("image");

            // Get the absolute file system path of the web application root directory
            String appRealPath = getServletContext().getRealPath("/");

            // Initialize variable to hold the relative path to the saved image
            String imageRelativePath = null;

            // If an image was uploaded (file part exists and size > 0)
            if (filePart != null && filePart.getSize() > 0) {
                // Call the ImageUtil to save the image and get the relative path
                imageRelativePath = imageUtil.uploadImage(filePart, appRealPath);

                // Print debug info for the uploaded image path
                System.out.println("DEBUG: imagePath received = " + imageRelativePath);
            }

            // Call the registration service to register the employee with the form data and image path
            boolean success = registerService.registerEmployee(request, imageRelativePath);

            // Redirect with success query parameter if registration was successful
            if (success) {
                response.sendRedirect(redirectURL + "?success=true");
            } else {
                // Redirect with error=1 if registration failed without exceptions
                response.sendRedirect(redirectURL + "?error=1");
            }
        } catch (IllegalArgumentException e) {
            // Handle specific exception and redirect with error=3 (invalid arguments)
            e.printStackTrace();
            response.sendRedirect(redirectURL + "?error=3");
        } catch (Exception e) {
            // Catch all other exceptions, print stack trace and redirect with error=2 (general error)
            e.printStackTrace();
            response.sendRedirect(redirectURL + "?error=2");
        }
    }
}
