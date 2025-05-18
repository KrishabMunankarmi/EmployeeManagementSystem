<%-- Department JSP --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.EmployeeSystem.model.DepartmentModel" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Departments - Employee Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminDepartment.css">
</head>
<body>
<jsp:include page="Header.jsp" />

<main class="departments-container">
    <div class="departments-header">
        <h1>Departments</h1>
        <div class="search-add">
            <form method="get" action="department" class="search-box">
                <input type="text" name="search" placeholder="Search departments..." value="${param.search}">
                <button type="submit">Search</button>
            </form>
        </div>
    </div>

    <table class="departments-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Department Name</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<DepartmentModel> departments = (List<DepartmentModel>) request.getAttribute("departments");
                if (departments != null && !departments.isEmpty()) {
                    for (DepartmentModel dept : departments) {
            %>
            <tr>
                <td>DEPT-<%= dept.getDepartmentID() %></td>
                <td><%= dept.getDepartmentName() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="2" style="text-align: center;">No departments found.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</main>

<jsp:include page="Footer.jsp" />
</body>
</html>
