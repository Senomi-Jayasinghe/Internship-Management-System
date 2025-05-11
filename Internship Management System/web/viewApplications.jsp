<%@page import="java.util.*, model.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Applications</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <div>
        <h2>My Internship Applications</h2>
        <table class="internship-table">
            <tr>
                <th>Internship Title</th>
                <th>Status</th>
            </tr>
            <%
                List<Application> applications = (List<Application>) request.getAttribute("applications");
                Map<Integer, String> internshipMap = (Map<Integer, String>) request.getAttribute("internshipTitles");

                if (applications != null) {
                    for (Application app : applications) {
                        String title = internshipMap.get(app.getInternshipId());
            %>
            <tr>
                <td><%= title != null ? title : "Unknown" %></td>
                <td><%= app.getStatus() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="2">No applications found.</td>
            </tr>
            <% } %>
        </table>
    </div>
</body>
</html>