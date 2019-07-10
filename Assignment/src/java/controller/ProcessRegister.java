package controller;

import dao.UserDAO;
import model.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Validate;

/**
 *
 * @author Do Duong
 */
public class ProcessRegister extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        if ((Validate.checkUserName(username) == true) && (Validate.checkEmail(email) == true)) {
            request.setAttribute("error", "Username and Email is already exist");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (Validate.checkUserName(username) == true) {
            request.setAttribute("error", "Username is already exist");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (Validate.checkEmail(email) == true) {
            request.setAttribute("error", "Email is already exist");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            User usr = UserDAO.register(username, password, email);
            response.sendRedirect("login.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
