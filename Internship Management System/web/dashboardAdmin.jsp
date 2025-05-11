<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="style.css">
    <style>
        .dashboard-wrapper {
            padding: 40px;
            text-align: center;
            font-family: 'Segoe UI', sans-serif;
        }
        .welcome-text {
            font-size: 26px;
            font-weight: bold;
            margin-bottom: 30px;
        }
        .dashboard-container {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 30px;
        }
        .dashboard-card {
            width: 250px;
            height: 150px;
            border-radius: 16px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
            background-color: #ffffff;
            text-align: center;
            padding: 30px 20px;
            transition: transform 0.2s ease;
            cursor: pointer;
        }
        .dashboard-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 16px rgba(0,0,0,0.25);
        }
        .dashboard-card a {
            text-decoration: none;
            color: #007BFF;
            font-weight: bold;
            font-size: 18px;
        }
    </style>
</head>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        request.setAttribute("errorMessage", "ACCESS DENIED!");
        request.getRequestDispatcher("error.jsp").forward(request, response);
        return;
    }
%>
<body>
    <%@include file="navbar.jsp" %>
    <div class="dashboard-wrapper">
        <div class="welcome-text">
            Welcome <%= user.getName() %>!
        </div>
        <div class="dashboard-container">
            <div class="dashboard-card">
                <a href="UpdateInternshipServlet">Approve/Reject Internships</a>
            </div>
            <div class="dashboard-card">
                <a href="InternshipServlet">View Internship Stats</a>
            </div>
        </div>
    </div>
</body>
</html>