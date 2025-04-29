<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employees - Employee Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Employees.css">
</head>
<body>
    <jsp:include page="Header.jsp" />
    
    <main class="employees-container">
        <div class="employees-header">
            <h1>Employee Directory</h1>
            <div class="search-add">
                <div class="search-box">
                    <input type="text" placeholder="Search employees...">
                    <button><i class="fas fa-search"></i></button>
                </div>
                <button class="add-employee">
                    <i class="fas fa-user-plus"></i> Add Employee
                </button>
            </div>
        </div>
        
        <table class="employees-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Department</th>
                    <th>Position</th>
                    <th>Contract</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>EMP-1001</td>
                    <td>
                        <div class="employee-info">
                            <img src="${pageContext.request.contextPath}/resources/Bikash.png" alt="User">
                            <span>John Doe</span>
                        </div>
                    </td>
                    <td>Information Technology</td>
                    <td>Senior Developer</td>
                    <td><span class="status active">Active</span></td>
                    <td>
                        <div class="action-buttons">
                            <button class="view-btn"><i class="fas fa-eye"></i></button>
                            <button class="edit-btn"><i class="fas fa-edit"></i></button>
                            <button class="delete-btn"><i class="fas fa-trash"></i></button>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>EMP-1002</td>
                    <td>
                        <div class="employee-info">
                            <img src="${pageContext.request.contextPath}/resources/Prerana.png" alt="User">
                            <span>Jane Smith</span>
                        </div>
                    </td>
                    <td>Human Resources</td>
                    <td>HR Manager</td>
                    <td><span class="status active">Active</span></td>
                    <td>
                        <div class="action-buttons">
                            <button class="view-btn"><i class="fas fa-eye"></i></button>
                            <button class="edit-btn"><i class="fas fa-edit"></i></button>
                            <button class="delete-btn"><i class="fas fa-trash"></i></button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        
        <div class="pagination">
            <button class="page-btn"><i class="fas fa-chevron-left"></i></button>
            <span class="current-page">1</span>
            <button class="page-btn"><i class="fas fa-chevron-right"></i></button>
        </div>
    </main>

    <jsp:include page="Footer.jsp" />
</body>
</html>