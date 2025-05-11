<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Approve Applications</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            padding: 20px;
            background-color: #f5f5f5;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 12px 16px;
            border: 1px solid #ccc;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        .btn {
            padding: 6px 14px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
        }

        .btn-approve {
            background-color: #28a745;
            color: white;
        }

        .btn-reject {
            background-color: #dc3545;
            color: white;
        }

        form {
            display: inline;
        }
    </style>
</head>
<body>
    <%@include file="navbar.jsp" %>
    <h2>All Applications</h2>
    <table>
        <tr>
            <th>Student Name</th>
            <th>Internship Title</th>
            <th>Company Name</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <%
            List<Map<String, String>> appList = (List<Map<String, String>>) request.getAttribute("appList");
            for (Map<String, String> app : appList) {
        %>
        <tr>
            <td><%= app.get("studentName") %></td>
            <td><%= app.get("internshipTitle") %></td>
            <td><%= app.get("companyName") %></td>
            <td><%= app.get("status") %></td>
            <td>
                <form action="UpdateInternshipServlet" method="post">
                    <input type="hidden" name="applicationId" value="<%= app.get("applicationId") %>">
                    <input type="hidden" name="newStatus" value="approved">
                    <button type="submit" class="btn btn-approve">Approve</button>
                </form>
                <form action="UpdateInternshipServlet" method="post">
                    <input type="hidden" name="applicationId" value="<%= app.get("applicationId") %>">
                    <input type="hidden" name="newStatus" value="rejected">
                    <button type="submit" class="btn btn-reject">Reject</button>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>