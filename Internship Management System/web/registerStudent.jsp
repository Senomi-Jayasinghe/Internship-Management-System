<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User Registration</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <style>
        select {
            width: 100%;
            padding: 8px;
            border: 1px solid #b3cce6;
            border-radius: 5px;
            margin-bottom: 15px;
            cursor: pointer;
        }

        select:focus {
            border-color: #0066cc;
            outline: none;
            background-color: #f0f8ff;
        }
    </style>
    <body class="body-form">
        <div class="form-container">
            <h2>User Registration</h2>
            <form action="RegisterServlet" method="post">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>

                <label for="role">Role:</label>
                <select id="role" name="role" required>
                    <option value="">-- Select Role --</option>
                    <option value="student">Student</option>
                    <option value="company">Company</option>
                </select>

                <button class="button-form" type="submit">Register</button>
            </form>
            <br>
            <center>
                <span>
                    Already Signed Up? Login <a href="login.jsp">Here</a>
                </span>
            </center>
        </div>
    </body>
</html>