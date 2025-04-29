<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Access Denied</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #fce4ec;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }
        .unauthorized-box {
            text-align: center;
            background-color: white;
            padding: 40px 60px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        h1 {
            color: #d32f2f;
        }
        p {
            margin-top: 10px;
            font-size: 1rem;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #1976d2;
            color: white;
            text-decoration: none;
            border-radius: 6px;
        }
        a:hover {
            background-color: #1565c0;
        }
    </style>
</head>
<body>
<div class="unauthorized-box">
    <h1>Access Denied</h1>
    <p>You don't have permission to view this page.</p>
    <a href="${pageContext.request.contextPath}/dashboard">Return to Dashboard</a>
</div>
</body>
</html>
