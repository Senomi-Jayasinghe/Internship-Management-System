package controller;

import dao.ApplicationDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UpdateInternshipServlet", urlPatterns = {"/UpdateInternshipServlet"})
public class UpdateInternshipServlet extends HttpServlet {

    ApplicationDAO applicationDAO = new ApplicationDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Map<String, String>> appList = applicationDAO.getAllApplicationInfo();
            request.setAttribute("appList", appList);
            request.getRequestDispatcher("approveApplications.jsp").forward(request, response);
        }
        catch (Exception ex){
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int applicationId = Integer.parseInt(request.getParameter("applicationId"));
            String newStatus = request.getParameter("newStatus");
            
            applicationDAO.updateApplicationStatus(applicationId, newStatus);
            response.sendRedirect("UpdateInternshipServlet"); // go back to list after update
            
        } catch (Exception ex){
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
