package java.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.model.User;
import java.dao.UserDAO;
import java.util.MD5;

/**
 *
 * @author Do Duong
 */
public class ProcessLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProcessLogin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessLogin at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        // Lưu user vào session
        System.out.println("XX"+ MD5.getMd5(password));
        User user = UserDAO.checkLogin(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            Cookie uidCk = new Cookie("uid",String.valueOf(user.getUid()));
            Cookie usrCk = new Cookie("username",user.getUserName());
            uidCk.setMaxAge(-1);
            usrCk.setMaxAge(-1);
            response.addCookie(uidCk);
            response.addCookie(usrCk);
            response.sendRedirect("home.jsp");
        }else{
            response.sendRedirect("home.jsp");
        }
    }
}
