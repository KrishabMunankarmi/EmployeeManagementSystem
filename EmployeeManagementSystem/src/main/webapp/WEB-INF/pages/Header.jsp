<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    :root {
        --main-font: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }
    html, body {
        font-family: var(--main-font) !important;
    }
    body * {
        font-family: inherit !important;
    }
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Header.css"/>

<header class="main-header">
    <div class="header-content">
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/resources/Logo.png" alt="Company Logo" class="logo">
            <div class="logo-text">
                <span>Employee</span>
                <span>Management</span>
                <span>System</span>
            </div>
        </div>
        <nav class="main-nav">
            <ul>
                <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/profile">Profile</a></li>
                <li><a href="${pageContext.request.contextPath}/employees">Employees</a></li>
                <li><a href="${pageContext.request.contextPath}/department">Departments</a></li>
                <li><a href="${pageContext.request.contextPath}/aboutus">About Us</a></li>
            </ul>
        </nav>
        <div class="user-actions">
            <span class="welcome-msg">
                Welcome, ${sessionScope.fullName != null ? sessionScope.fullName : 'User'}
            </span>
            <a href="${pageContext.request.contextPath}/login" class="logout-btn">Logout</a>
        </div>
    </div>
</header>
