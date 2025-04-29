<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Home.css">
</head>
<body>
    <jsp:include page="Header.jsp" />
    
    <main class="home-container">
        <section class="hero-section">
            <div class="hero-content">
                <h1>Streamline Your HR Operations</h1>
                <p class="subtitle">Efficient employee management for modern businesses</p>
                <div class="cta-buttons">
                    <a href="${pageContext.request.contextPath}/login" class="btn btn-primary">Get Started</a>
                    <a href="${pageContext.request.contextPath}/features" class="btn btn-outline">Learn More</a>
                </div>
            </div>
            <div class="hero-image">
                <img src="${pageContext.request.contextPath}/resources/EmpManage.png" alt="Team Illustration">
            </div>
        </section>

        <section class="features-section">
            <h2 class="section-title">Key Features</h2>
            <div class="features-grid">
                <div class="feature-card">
                    <i class="fas fa-users feature-icon"></i>
                    <h3>Employee Management</h3>
                    <p>Centralized employee records and profiles</p>
                </div>
                <div class="feature-card">
                    <i class="fas fa-chart-bar feature-icon"></i>
                    <h3>Reporting</h3>
                    <p>Comprehensive analytics and insights</p>
                </div>
                <div class="feature-card">
                    <i class="fas fa-clock feature-icon"></i>
                    <h3>Time Tracking</h3>
                    <p>Attendance and leave management</p>
                </div>
            </div>
        </section>
    </main>

    <jsp:include page="Footer.jsp" />
</body>
</html>
