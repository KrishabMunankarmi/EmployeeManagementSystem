<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Profile - Employee Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Profile.css">
</head>
<body>
    <jsp:include page="Header.jsp" />

    <main class="profile-container">
        <h1 class="profile-title">My Profile</h1>

        <div class="profile-content">
            <div class="profile-picture">
                <img src="${pageContext.request.contextPath}/resources/Man.png" alt="Profile Picture">
                <button class="edit-btn">Change Photo</button>
            </div>

            <div class="profile-details">
                <div class="detail-group">
                    <label>Employee ID:</label>
                    <span>${empid}</span>
                </div>
                <div class="detail-group">
                    <label>Full Name:</label>
                    <span>${fullName}</span>
                </div>
                <div class="detail-group">
                    <label>Age:</label>
                    <span>${age}</span>
                </div>
                <div class="detail-group">
                    <label>Contact:</label>
                    <span>${contact}</span>
                </div>
                <div class="detail-group">
                    <label>Department:</label>
                    <span>${department}</span>
                </div>
                <div class="detail-group">
                    <label>Position:</label>
                    <span>${position}</span>
                </div>
                <div class="detail-group">
                    <label>Contract Period:</label>
                    <span>${contractPeriod}</span>
                </div>
            </div>
        </div>

        <div class="profile-actions">
		    <a href="${pageContext.request.contextPath}/editprofile" class="action-btn edit-profile">Edit Profile</a>
		    <a href="${pageContext.request.contextPath}/changepassword" class="action-btn change-password">Change Password</a>
		</div>

    </main>

    <jsp:include page="Footer.jsp" />
</body>
</html>
