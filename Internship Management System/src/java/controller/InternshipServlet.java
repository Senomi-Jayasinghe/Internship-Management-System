package controller;

import dao.InternshipDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import model.Internship;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet(name = "InternshipServlet", urlPatterns = {"/InternshipServlet"})
public class InternshipServlet extends HttpServlet {

    InternshipDAO internshipDAO = new InternshipDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");

            if (user == null) {
                request.setAttribute("errorMessage", "ACCESS DENIED!");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            if ("Student".equals(user.getRole())) {
                // Show all internships to students that they have not applied
                int studentId = user.getId();
                List<Internship> internships = internshipDAO.getAllInternships(studentId);
                request.setAttribute("internships", internships);
                request.getRequestDispatcher("applyInternship.jsp").forward(request, response);

            } else if ("Company".equals(user.getRole())) {
                // Show only that company's internships
                int companyId = user.getId();
                List<Internship> companyInternships = internshipDAO.getInternshipsByCompanyId(companyId);
                request.setAttribute("internships", companyInternships);
                request.getRequestDispatcher("dashboardCompany.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Invalid user role!");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            String title = request.getParameter("title");
            String desc = request.getParameter("description");
            LocalDate deadline = LocalDate.parse(request.getParameter("deadline"));
            int companyId = Integer.parseInt(request.getParameter("companyId"));

            Internship internship = new Internship();
            internship.setTitle(title);
            internship.setDescription(desc);
            internship.setDeadline(deadline);
            internship.setCompanyId(companyId);

            internshipDAO.postInternship(internship);
            response.sendRedirect("dashboardCompany.jsp");
        }
        catch(Exception ex){
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}