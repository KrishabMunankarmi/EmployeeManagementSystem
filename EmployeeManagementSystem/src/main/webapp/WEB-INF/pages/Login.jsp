<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Employee Management</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Login.css"/>
</head>
<body>
    <div class="container">
        <div class="left-side">
            <h1 class="project-title">Employee Management System</h1>
            <div class="logo-placeholder">
                <img src="${pageContext.request.contextPath}/resources/Logo.png" alt="Logo">
            </div>
        </div>
        <div class="right-side">
            <h2 class="login-title">Log in to your account</h2>

            <form class="login-form" action="${pageContext.request.contextPath}/login" method="post">
                <label for="name">Username:</label>
                <input type="text" id="name" name="name" required>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>

                <button type="submit">Login</button>
            </form>

            <p style="color:red;">
                ${error != null ? error : ""}
            </p>

            <a href="${pageContext.request.contextPath}/register" class="register-link">Don't have an Employee Account?</a>
        </div>
    </div>
</body>
</html>
