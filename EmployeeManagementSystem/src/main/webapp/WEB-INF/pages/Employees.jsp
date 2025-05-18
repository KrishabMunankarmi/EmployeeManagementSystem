<%-- Employees JSP --%>

<%@ page import="java.util.List" %>
<%@ page import="com.EmployeeSystem.model.EmployeeSystemModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
		    <form method="get" action="employees" class="search-box">
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
                <th>Contact</th>
                <th>Department</th>
                <th>Position</th>
                <th class="actions-column">Contract</th>
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
                <td>
                    <div class="employee-info">
                        <span><%= emp.getName() %></span>
                    </div>
                </td>
                <td><%= emp.getContact() %></td>
                <td><%= emp.getDepartment() %></td>
                <td><%= emp.getPosition() %></td>
                <td><%= emp.getConperiod() %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="7" style="text-align: center;">No employees found</td>
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
