package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {
        String jdbcURL = "jdbc:mysql://localhost:3306/projectmanagementdb";
        String jdbcUsername = "root";
        String jdbcPassword = "";

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
}
