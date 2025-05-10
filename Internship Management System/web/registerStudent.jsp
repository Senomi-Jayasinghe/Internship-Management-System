<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User Registration</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
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

                <button type="submit">Register</button>
            </form>
        </div>
    </body>
</html>