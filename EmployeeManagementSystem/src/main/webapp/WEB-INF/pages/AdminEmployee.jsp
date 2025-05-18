<%-- Admin Employee JSP --%>

<%@ page import="java.util.List" %>
<%@ page import="com.EmployeeSystem.model.EmployeeSystemModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employees - Employee Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminEmployee.css">
</head>
<body>
<jsp:include page="AdminHeader.jsp" />

<main class="employees-container">
    <div class="employees-header">
        <h1>Employee Directory</h1>
        <div class="search-add">
            <form method="get" action="adminemployee" class="search-box">
                <input type="text" name="search" placeholder="Search employees..." value="${param.search}">
                <button type="submit">Search</button>
            </form>
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
                <th class="actions-column">Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<EmployeeSystemModel> employees = (List<EmployeeSystemModel>) request.getAttribute("employees");
                if (employees != null && !employees.isEmpty()) {
                    for (EmployeeSystemModel emp : employees) {
            %>
            <tr>
                <td>EMP-<%= emp.getEmpid() %></td>
                <td><div class="employee-info"><span><%= emp.getName() %></span></div></td>
                <td><%= emp.getDepartment() %></td>
                <td><%= emp.getPosition() %></td>
                <td><%= emp.getConperiod() %></td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/adminemployee" onsubmit="return confirm('Are you sure you want to delete this employee?');">
                        <input type="hidden" name="empid" value="<%= emp.getEmpid() %>">
                        <button class="delete-btn" type="submit">Delete</button>
                    </form>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="6" style="text-align: center;">No employees found.</td>
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
