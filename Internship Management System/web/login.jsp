<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body class="body-form">
        <div class="form-container">
            <h2>Login :)</h2>
            <form action="LoginServlet" method="post">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>

                <button class="button-form" type="submit">Login</button>
            </form>
            <br>
            <center>
                <span>
                    New User? Register <a href="registerStudent.jsp">Here</a>
                </span>
            </center>
        </div>
    </body>
</html>