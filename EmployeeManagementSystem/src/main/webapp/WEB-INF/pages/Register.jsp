<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - Employee Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Register.css">
    <script>
        function validateForm() {
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('re-password').value;
            
            if (password !== confirmPassword) {
                alert('Passwords do not match!');
                return false;
            }

            const contact = document.getElementById('contact').value;
            if (!/^\d{10}$/.test(contact)) {
                alert('Contact number must be 10 digits!');
                return false;
            }

            const age = parseInt(document.getElementById('age').value);
            if (age < 18 || age > 100) {
                alert('Age must be between 18 and 100!');
                return false;
            }

            return true;
        }
    </script>
</head>
<body>
    <div class="register-container">
        <div class="register-left">
            <h1 class="register-title">Employee Management System</h1>
            <div class="register-logo">
                <img src="${pageContext.request.contextPath}/resources/Logo.png" alt="Logo">
            </div>
            <p class="register-tagline">Join our organization today</p>
        </div>
        
        <div class="register-right">
            <div class="register-form-container">
                <h2>Create Your Account</h2>
                <p class="register-subtitle">Fill in your details to register</p>
                
                <% if(request.getParameter("error") != null) { %>
                    <div class="alert alert-error">
                        <i class="fas fa-exclamation-circle"></i>
                        <%= request.getParameter("error").equals("3") ? "Passwords do not match" : 
                           request.getParameter("error").equals("1") ? "Registration failed" : 
                           request.getParameter("error").equals("2") ? "Database error" : 
                           "An error occurred" %>
                    </div>
                <% } %>
                
                <% if(request.getParameter("success") != null) { %>
                    <div class="alert alert-success">
                        <i class="fas fa-check-circle"></i>
                        Registration successful! Please login.
                    </div>
                <% } %>
                
               <form class="register-form" action="${pageContext.request.contextPath}/register" method="post" onsubmit="return validateForm()">
                    <div class="form-row">
                        <div class="form-group">
                            <label for="empid">Employee ID</label>
                            <input type="text" id="empid" name="empid" required>
                        </div>
                        <div class="form-group">
                            <label for="name">Full Name</label>
                            <input type="text" id="name" name="name" required>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" id="password" name="password" required>
                        </div>
                        <div class="form-group">
                            <label for="re-password">Confirm Password</label>
                            <input type="password" id="re-password" name="re-password" required>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="age">Age</label>
                            <input type="number" id="age" name="age" required min="18" max="100">
                        </div>
                        <div class="form-group">
                            <label for="contact">Contact No.</label>
                            <input type="tel" id="contact" name="contact" required pattern="[0-9]{10,15}" 
                                   title="10-15 digit phone number">
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="posid">Position ID</label>
                            <input type="number" id="posid" name="posid" required>
                        </div>
                        <div class="form-group">
                            <label for="position">Position Title</label>
                            <input type="text" id="position" name="position" required>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="deptid">Department ID</label>
                            <input type="number" id="deptid" name="deptid" required>
                        </div>
                        <div class="form-group">
                            <label for="department">Department Name</label>
                            <input type="text" id="department" name="department" required>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="conid">Contract ID</label>
                            <input type="number" id="conid" name="conid" required>
                        </div>
                        <div class="form-group">
                            <label for="conperiod">Contract Period</label>
                            <input type="text" id="conperiod" name="conperiod" required>
                        </div>
                    </div>
                    
                    <button type="submit" class="register-btn">Register</button>
                    
                    <div class="register-footer">
                        Already have an account? <a href="${pageContext.request.contextPath}/login">Login here</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>