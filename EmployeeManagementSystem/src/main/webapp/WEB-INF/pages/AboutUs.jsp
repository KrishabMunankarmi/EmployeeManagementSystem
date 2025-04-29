<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>About Us - Employee Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AboutUs.css">
</head>
<body>
    <jsp:include page="Header.jsp" />

    <main class="about-container">
        <section class="about-hero">
            <h1>Our Story</h1>
            <p>Founded in 2025, we're revolutionizing HR management with cutting-edge technology and human-centered design.</p>
        </section>

        <section class="team-section">
            <h2>Meet The Leadership</h2>
            
            <div class="team-grid">
                <div class="team-card">
                    <div class="team-photo">
                        <img src="${pageContext.request.contextPath}/resources/Aarav.png" alt="Aarav Shrestha">
                        <div class="social-links">
                            <a href="#"><i class="fab fa-linkedin"></i></a>
                            <a href="#"><i class="fab fa-twitter"></i></a>
                        </div>
                    </div>
                    <h3>Aarav Shrestha</h3>
                    <p class="position">CEO and Founder</p>
                    <p class="bio">With 20 years in HR technology, Aarav founded our company to solve the pain points he experienced firsthand.</p>
                </div>

                <div class="team-card">
                    <div class="team-photo">
                        <img src="${pageContext.request.contextPath}/resources/Prerana.png" alt="Prerana Gurung">
                        <div class="social-links">
                            <a href="#"><i class="fab fa-linkedin"></i></a>
                            <a href="#"><i class="fab fa-github"></i></a>
                        </div>
                    </div>
                    <h3>Prerana Gurung</h3>
                    <p class="position">CTO</p>
                    <p class="bio">Prerana leads our engineering team with expertise in scalable cloud architectures and AI-driven solutions.</p>
                </div>

                <div class="team-card">
                    <div class="team-photo">
                        <img src="${pageContext.request.contextPath}/resources/Bikash.png" alt="Bikash Adhikari">
                        <div class="social-links">
                            <a href="#"><i class="fab fa-linkedin"></i></a>
                            <a href="#"><i class="fab fa-instagram"></i></a>
                        </div>
                    </div>
                    <h3>Bikash Adhikari</h3>
                    <p class="position">Head of Customer Success</p>
                    <p class="bio">Bikash ensures our clients get maximum value from our platform with his 15+ years in HR consulting.</p>
                </div>
            </div>
        </section>

        <section class="mission-section">
            <h2>Our Mission</h2>
            <div class="mission-content">
                <div class="mission-text">
                    <p>We believe every organization deserves access to powerful, intuitive HR tools that empower both employees and administrators.</p>
                    <p>Our platform combines enterprise-grade security with consumer-grade usability to transform workplace management.</p>
                </div>
                <div class="mission-image">
                    <img src="${pageContext.request.contextPath}/resources/EmpManage.png" alt="Team collaborating">
                </div>
            </div>
        </section>

        <section class="contact-info-section">
            <h2>Contact Us</h2>
            <p>If you have any questions or need support, feel free to reach out to us:</p>
            <ul class="contact-list">
                <li>Phone: +1 (123) 456-7890</li>
                <li>Email: support@employeesystem.com</li>
                <li>Address: Kathmandu, Nepal</li>
            </ul>
        </section>
    </main>

    <jsp:include page="Footer.jsp" />
</body>
</html>
