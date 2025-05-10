package controller;

import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
            
            if (user == null) {
                request.setAttribute("errorMessage", "Invalid User Login");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            else if ("student".equals(user.getRole())){
                response.sendRedirect("dashboardStudent.jsp");
            }
            else if("company".equals(user.getRole())){
                response.sendRedirect("dashboardCompany.jsp");
            }
            
            response.sendRedirect("dashboardStudent.jsp");
        }
        catch (Exception ex){
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

}
