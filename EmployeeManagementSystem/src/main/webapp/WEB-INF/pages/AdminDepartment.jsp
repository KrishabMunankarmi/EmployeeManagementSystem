<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Departments - Employee Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Department.css">
</head>
<body>
    <jsp:include page="AdminHeader.jsp" />
    
    <main class="departments-container">
        <div class="departments-header">
            <h1>Departments</h1>
        </div>
        
        <div class="departments-grid">
            <div class="department-card">
                <div class="card-header">
                    <h2>Information Technology</h2>
                    <span class="employee-count">24 Employees</span>
                </div>
                <div class="card-body">
                    <p>Responsible for all technical infrastructure and software development</p>
                    <div class="card-actions">
                        <button class="action-btn view"><i class="fas fa-eye"></i> View</button>
                        <button class="action-btn edit"><i class="fas fa-edit"></i> Edit</button>
                    </div>
                </div>
            </div>
            
            <div class="department-card">
                <div class="card-header">
                    <h2>Human Resources</h2>
                    <span class="employee-count">12 Employees</span>
                </div>
                <div class="card-body">
                    <p>Handles recruitment, employee relations, and benefits administration</p>
                    <div class="card-actions">
                        <button class="action-btn view"><i class="fas fa-eye"></i> View</button>
                        <button class="action-btn edit"><i class="fas fa-edit"></i> Edit</button>
                    </div>
                </div>
            </div>
            
             <div class="department-card">
                <div class="card-header">
                    <h2>Development</h2>
                    <span class="employee-count">6 Employees</span>
                </div>
                <div class="card-body">
                    <p>Handles development, and software designing</p>
                    <div class="card-actions">
                        <button class="action-btn view"><i class="fas fa-eye"></i> View</button>
                        <button class="action-btn edit"><i class="fas fa-edit"></i> Edit</button>
                    </div>
                </div>
            </div>
            
            <div class="department-card">
                <div class="card-header">
                    <h2>Testing</h2>
                    <span class="employee-count">8 Employees</span>
                </div>
                <div class="card-body">
                    <p>Software Testing and software analysis</p>
                    <div class="card-actions">
                        <button class="action-btn view"><i class="fas fa-eye"></i> View</button>
                        <button class="action-btn edit"><i class="fas fa-edit"></i> Edit</button>
                    </div>
                </div>
            </div>

        </div>
    </main>

</body>
</html>