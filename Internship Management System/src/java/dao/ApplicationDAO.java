package dao;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Application;
import util.DBConnection;

public class ApplicationDAO {
    
    public void applyToInternship(int internshipId, int studentId) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO applications(internship_id, student_id, status) "
                + "VALUES (?, ?, 'Applied')";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

           stmt.setInt(1, internshipId);
           stmt.setInt(2, studentId);

           stmt.executeUpdate();
        }
        catch(Exception ex){
            ex.printStackTrace();
        } 
    }
    
    public List<Application> getApplicationsByStudentId(int studentId) throws Exception {
        List<Application> applications = new ArrayList<>();

        String query = "SELECT * FROM applications WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Application app = new Application();
                app.setId(rs.getInt("id"));
                app.setInternshipId(rs.getInt("internship_id"));
                app.setStudentId(rs.getInt("student_id"));
                app.setStatus(rs.getString("status"));
                applications.add(app);
            }
        }

        return applications;
    }
    
    public List<Map<String, String>> getAllApplicationInfo() throws Exception {
        List<Map<String, String>> applications = new ArrayList<>();
        String query = "SELECT a.id AS application_id, u.name AS student_name, i.title AS internship_title, " +
                       "c.name AS company_name, a.status " +
                       "FROM applications a " +
                       "JOIN users u ON a.student_id = u.id " +
                       "JOIN internships i ON a.internship_id = i.id " +
                       "JOIN users c ON i.company_id = c.id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Map<String, String> app = new HashMap<>();
                app.put("applicationId", rs.getString("application_id"));
                app.put("studentName", rs.getString("student_name"));
                app.put("internshipTitle", rs.getString("internship_title"));
                app.put("companyName", rs.getString("company_name"));
                app.put("status", rs.getString("status"));
                applications.add(app);
            }
        }
        return applications;
    }

    public void updateApplicationStatus(int applicationId, String newStatus) throws Exception {
        String query = "UPDATE applications SET status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newStatus);
            stmt.setInt(2, applicationId);
            stmt.executeUpdate();
        }
    }

}
