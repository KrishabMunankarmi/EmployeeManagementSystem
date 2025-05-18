<%-- Admin Department JSP --%>

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
<jsp:include page="AdminHeader.jsp" />

<main class="departments-container">
    <div class="departments-header">
        <h1>Departments</h1>
        <div class="search-add">
            <form method="get" action="admindepartment" class="search-box">
                <input type="text" name="search" placeholder="Search departments..." value="${param.search}">
                <button type="submit">Search</button>
            </form>
            <a href="admindepartment?action=new">
                <button class="add-department">Add Department</button>
            </a>
        </div>
    </div>

    <table class="departments-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Department Name</th>
                <th class="actions-column">Actions</th>
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
                <td class="action-links">
                    <a href="admindepartment?action=edit&id=<%= dept.getDepartmentID() %>">Edit</a> |
                    <form method="post" action="admindepartment" onsubmit="return confirm('Delete this department?');" style="display:inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="<%= dept.getDepartmentID() %>">
                        <button class="delete-btn" type="submit">Delete</button>
                    </form>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="3" style="text-align: center;">No departments found.</td>
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
