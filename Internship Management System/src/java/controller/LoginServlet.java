package controller;

import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User user = userDAO.logUser(email, password);
            HttpSession session = request.getSession();
            
            if (user == null) {
                request.setAttribute("errorMessage", "Invalid User Login");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            else if ("Student".equals(user.getRole())){
                session.setAttribute("user", user);
                response.sendRedirect("dashboardStudent.jsp");
            }
            else if("Company".equals(user.getRole())){
                session.setAttribute("user", user);
                response.sendRedirect("InternshipServlet");
            }
            else if("Admin".equals(user.getRole())){
                session.setAttribute("user", user);
                response.sendRedirect("dashboardAdmin.jsp");
            }     
        }
        catch (Exception ex){
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
