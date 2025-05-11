package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.User;
import util.DBConnection;
import java.sql.ResultSet;

public class UserDAO {
    
    public void registerUser(User user) throws ClassNotFoundException, SQLException{
        String InsertUser = "INSERT INTO users(name, email, password, role) VALUES (?, ?, ?, ?)";
        
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(InsertUser)
            ) 
        {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());

            stmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public User logUser(String username, String password) {
        User user = null;
        String getUser = "SELECT * FROM users WHERE email = ? AND password = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(getUser);
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(username);
                user.setPassword(password); 
                user.setRole(rs.getString("role"));
            }

        } catch (Exception ex) {
            ex.printStackTrace(); 
        }

        return user;
    }
}
