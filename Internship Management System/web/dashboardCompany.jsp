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
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%@include file="navbar.jsp" %>
    <div >
        <h2>Posted Internships</h2>
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
                <td style="display: flex; gap: 5px;">
                    <form action="EditInternshipServlet" method="get">
                        <input type="hidden" name="id" value="<%= i.getId() %>"/>
                        <button class="button-table" type="submit" style="background-color: green;">Edit</button>
                    </form>
                    <form action="DeleteInternshipServlet" method="post">
                        <input type="hidden" name="id" value="<%= i.getId() %>"/>
                        <button type="submit" class="button-table" style="background-color: red;"
                                onclick="return confirm('Are you sure you want to delete this internship?');">
                            Delete
                        </button>
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