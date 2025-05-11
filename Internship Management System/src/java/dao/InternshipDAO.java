package dao;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Internship;
import util.DBConnection;

public class InternshipDAO {
    
    public void postInternship(Internship internship) throws ClassNotFoundException, SQLException{
        String postIntern = "INSERT INTO internships(title, description, deadline, company_id) VALUES (?, ?, ?, ?)";
        
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(postIntern)
            ) 
        {
            stmt.setString(1, internship.getTitle());
            stmt.setString(2, internship.getDescription());
            stmt.setObject(3, internship.getDeadline());
            stmt.setInt(4, internship.getCompanyId());

            stmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Internship> getAllInternships(int studentId) throws Exception {
        List<Internship> internships = new ArrayList<>();
        String query = "SELECT i.* FROM internships i "
                + "LEFT JOIN applications a ON i.id = a.internship_id AND a.student_id = ? "
                + "WHERE a.student_id IS NULL OR a.status = 'rejected';";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Internship internship = new Internship();
                    internship.setId(rs.getInt("id"));
                    internship.setTitle(rs.getString("title"));
                    internship.setDescription(rs.getString("description"));
                    internship.setDeadline(rs.getDate("deadline").toLocalDate());
                    internship.setCompanyId(rs.getInt("company_id"));
                    internships.add(internship);
                }
            }
        }
        return internships;
    }
    
    public List<Internship> getInternshipsByCompanyId(int companyId) {
        List<Internship> internships = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM internships WHERE company_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, companyId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Internship internship = new Internship();
                internship.setId(rs.getInt("id"));
                internship.setTitle(rs.getString("title"));
                internship.setDescription(rs.getString("description"));
                internship.setDeadline(rs.getDate("deadline").toLocalDate());
                internship.setCompanyId(rs.getInt("company_id"));
                internships.add(internship);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return internships;
    }
    
    public Map<Integer, String> getAllInternshipTitles() throws Exception {
        Map<Integer, String> titles = new HashMap<>();
        String query = "SELECT id, title FROM internships";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                titles.put(rs.getInt("id"), rs.getString("title"));
            }
        }
        return titles;
    }

}
