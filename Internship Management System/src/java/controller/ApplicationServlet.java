package controller;

import dao.ApplicationDAO;
import dao.InternshipDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import model.Application;
import model.User;

@WebServlet(name = "ApplicationServlet", urlPatterns = {"/ApplicationServlet"})
public class ApplicationServlet extends HttpServlet {

    ApplicationDAO applicationDAO = new ApplicationDAO();
    InternshipDAO internshipDAO = new InternshipDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        int studentId = user.getId();
        
        try {
            List<Application> applications = applicationDAO.getApplicationsByStudentId(studentId);
            Map<Integer, String> internshipTitles = internshipDAO.getAllInternshipTitles();

            request.setAttribute("applications", applications);
            request.setAttribute("internshipTitles", internshipTitles);
            request.getRequestDispatcher("viewApplications.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error retrieving applications", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            int internshipId = Integer.parseInt(request.getParameter("internshipId"));
            int studentId = Integer.parseInt(request.getParameter("studentId"));        

            applicationDAO.applyToInternship(internshipId, studentId);

            response.sendRedirect("dashboardStudent.jsp");

        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
