<%@ page import="model.User"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Internship" %>
<%@ page isELIgnored="false" %>
<%
    List<Internship> internships = (List<Internship>) request.getAttribute("internships");
%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        request.setAttribute("errorMessage", "ACCESS DENIED!");
        request.getRequestDispatcher("error.jsp").forward(request, response);
        return;
    }
    int studentId = user.getId();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="css/style.css">
    <script>
        function loadInternships() { 
            fetch("ajax/internshipList.jsp") 
                .then(response => response.text()) 
                .then(data => { 
                    document.getElementById("internshipDropdown").innerHTML = data; 
                }); 
        }
    </script>
</head>
<body>
    <%@include file="navbar.jsp" %>
    <div >
        <span>Search for Internships:</span>
        <input type="text" id="searchInput" placeholder="Search by title..." style="margin-top: 15px; padding: 8px; width: 300px; border: 1px solid #ccc; border-radius: 5px;">
        <h2>Available Internships</h2>
        <table class="internship-table">
            <tr>
                <th>Title</th>
                <th>Description</th>
                <th>Deadline</th>
                <th>Company</th>
                <th>Action</th>
            </tr>
            <%
                for (model.Internship i : internships) {
            %>
            <tr>
                <td><%= i.getTitle() %></td>
                <td><%= i.getDescription() %></td>
                <td><%= i.getDeadline() %></td>
                <td><%= i.getCompanyId() %></td>
                <td>
                    <form action="ApplicationServlet" method="post">
                        <input type="hidden" name="internshipId" value="<%= i.getId() %>">
                        <input type="hidden" name="studentId" value="<%= studentId%>">
                        <button class="button-table" type="submit">Apply</button>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
</html>