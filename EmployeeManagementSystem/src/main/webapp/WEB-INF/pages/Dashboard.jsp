<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Employee Management</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Dashboard.css">
    <style>

        .emoji-icon {
            font-size: 1.8rem;
            line-height: 1;
            display: inline-block;
        }
        .stat-emoji { min-width: 50px; text-align: center; }
        .activity-emoji { min-width: 40px; text-align: center; }
        .action-emoji { font-size: 2rem; margin-bottom: 0.5rem; }
    </style>
</head>
<body>
    <jsp:include page="AdminHeader.jsp" />
    
    <main class="dashboard-container">
        <div class="dashboard-header">
            <h1> Admin Dashboard</h1>
        </div>
        
        <div class="stats-grid">

            <div class="stat-card">
                <div class="stat-emoji emoji-icon">👥</div>
                <div class="stat-info">
                    <h3>Total Employees</h3>
                    <p class="stat-value">1,245</p>
                    <p class="stat-change positive">5% from last month</p>
                </div>
            </div>
            

            <div class="stat-card">
                <div class="stat-emoji emoji-icon">📂</div>
                <div class="stat-info">
                    <h3>Active Projects</h3>
                    <p class="stat-value">32</p>
                    <p class="stat-change positive">3 new this week</p>
                </div>
            </div>
            

            <div class="stat-card">
                <div class="stat-emoji emoji-icon">🏢</div>
                <div class="stat-info">
                    <h3>Departments</h3>
                    <p class="stat-value">12</p>
                    <p class="stat-change">No change</p>
                </div>
            </div>
            

            <div class="stat-card">
                <div class="stat-emoji emoji-icon">🎉</div>
                <div class="stat-info">
                    <h3>Upcoming Holidays</h3>
                    <p class="stat-value">2</p>
                    <p class="stat-change">Next: Company Anniversary</p>
                </div>
            </div>
        </div>
        
        <div class="dashboard-content">
            <div class="recent-activity">
                <h2><span class="emoji-icon">🔄</span> Recent Activity</h2>
                <div class="activity-list">
                    <div class="activity-item">
                        <div class="activity-emoji emoji-icon">🆕</div>
                        <div class="activity-details">
                            <p><strong>John Doe</strong> joined Marketing</p>
                            <small>2 hours ago</small>
                        </div>
                    </div>
                    
                    <div class="activity-item">
                        <div class="activity-emoji emoji-icon">🎂</div>
                        <div class="activity-details">
                            <p><strong>Sarah Smith</strong> has a birthday today!</p>
                            <small>Send wishes</small>
                        </div>
                    </div>
                    
                    <div class="activity-item">
                        <div class="activity-emoji emoji-icon">📅</div>
                        <div class="activity-details">
                            <p>Team meeting scheduled</p>
                            <small>All hands required</small>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="quick-actions">
                <h2><span class="emoji-icon">⚡</span> Quick Actions</h2>
                <div class="actions-grid">
                    <a href="${pageContext.request.contextPath}/employees" class="action-card">
                        <span class="action-emoji emoji-icon">👨‍💼</span>
                        <span>Team Management</span>
                    </a>
                    
                    <a href="${pageContext.request.contextPath}/attendance" class="action-card">
                        <span class="action-emoji emoji-icon">⏱️</span>
                        <span>Time Tracking</span>
                    </a>
                    
                    <a href="${pageContext.request.contextPath}/payroll" class="action-card">
                        <span class="action-emoji emoji-icon">💰</span>
                        <span>Payroll</span>
                    </a>
                    
                    <a href="${pageContext.request.contextPath}/reports" class="action-card">
                        <span class="action-emoji emoji-icon">📊</span>
                        <span>Analytics</span>
                    </a>
                </div>
            </div>
        </div>
    </main>
    
    <jsp:include page="Footer.jsp" />
</body>
</html>