<%-- Add Department JSP --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.EmployeeSystem.model.DepartmentModel" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= request.getAttribute("department") != null ? "Edit" : "Add" %> Department - Employee Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AddDepartment.css">
</head>
<body>
<jsp:include page="AdminHeader.jsp" />

<main class="department-form-container">
    <h1><%= request.getAttribute("department") != null ? "Edit" : "Add" %> Department</h1>

    <form method="post" action="${pageContext.request.contextPath}/admindepartment" class="department-form">
        <%
            DepartmentModel dept = (DepartmentModel) request.getAttribute("department");
            boolean isEdit = (dept != null);
        %>
        <% if (isEdit) { %>
            <input type="hidden" name="departmentID" value="<%= dept.getDepartmentID() %>">
        <% } %>

        <div class="form-group">
            <label for="departmentName">Department Name:</label>
            <input type="text" id="departmentName" name="departmentName" value="<%= isEdit ? dept.getDepartmentName() : "" %>" required>
        </div>

        <div class="form-actions">
            <button type="submit" class="save-btn"><%= isEdit ? "Update" : "Add" %> Department</button>
            <a href="${pageContext.request.contextPath}/admindepartment" class="cancel-btn">Cancel</a>
        </div>
    </form>
</main>

<jsp:include page="Footer.jsp" />
</body>
</html>
