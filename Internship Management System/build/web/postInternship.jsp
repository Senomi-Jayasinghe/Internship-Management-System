<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
    <title>Post Internship</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        request.setAttribute("errorMessage", "ACCESS DENIED!");
        request.getRequestDispatcher("error.jsp").forward(request, response);
        return;
    }
%>
<body class="body-form">
    <div class="form-container">
        <h2>Post an Internship</h2>
        <form action="InternshipServlet" method="post">
            <input type="hidden" name="companyId" value="<%= user.getId() %>" />
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required>

            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="4" required></textarea>

            <label for="deadline">Deadline:</label>
            <input type="date" id="deadline" name="deadline" required>

            <button class="button-form" type="submit">Post Internship</button>
        </form>
    </div>
</body>
</html>