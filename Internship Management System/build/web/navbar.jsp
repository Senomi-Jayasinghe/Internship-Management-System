<%@page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #004080;
            padding: 10px 20px;
            font-family: 'Segoe UI', sans-serif;
            color: white;
            height: 30px;
        }

        .navbar-left {
            display: flex;
            align-items: center;
        }

        .navbar-left .logo {
            font-weight: bold;
            margin-right: 20px;
            font-size: 18px;
        }

        .navbar-left a,
        .navbar-right a {
            text-decoration: none;
            color: white;
            margin-right: 15px;
            transition: color 0.2s ease-in-out;
        }

        .navbar-left a:hover,
        .navbar-right a:hover {
            color: #99ccff;
        }

        .logout-btn {
            background-color: #0066cc;
            padding: 6px 12px;
            border-radius: 5px;
        }

        .logout-btn:hover {
            background-color: #0052a3;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <div class="navbar-left">
            <span class="logo">InternMgtSystem</span>
            <%  User u = (User) session.getAttribute("user"); 
                if("Student".equals(u.getRole())) { %>
                <a href="dashboardStudent.jsp">Dashboard</a>
                <a href="ApplicationServlet">View Applications</a>
                <a href="InternshipServlet">Browse Internships</a>
            <% } else if ("Company".equals(u.getRole())) { %>
                <a href="InternshipServlet">Dashboard</a>
                <a href="postInternship.jsp">Post Internship</a>
                <a href="InternshipServlet">View Posted Internships</a>
            <% } %>
        </div>
        <div class="navbar-right">
            <a href="LogoutServlet" class="logout-btn">Logout</a>
        </div>
    </div>
</body>
</html>