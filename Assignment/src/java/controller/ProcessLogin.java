package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import model.User;
import dao.UserDAO;

public class ProcessLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        User user = UserDAO.checkLogin(username, password);
        if (user != null) {
            // Lưu user vào session
            session.setAttribute("user", user);
            Cookie uidCk = new Cookie("uid", String.valueOf(user.getUid()));
            Cookie usrCk = new Cookie("username", user.getUserName());
            uidCk.setMaxAge(-1);
            usrCk.setMaxAge(-1);
            response.addCookie(uidCk);
            response.addCookie(usrCk);
            if (user.getStatus() == 2) {
                request.getRequestDispatcher("update_info.jsp").forward(request, response);
            } else if (user.getStatus() == 1) {
                response.sendRedirect("home.jsp");
            } else {
                response.sendRedirect("home.jsp");
            }
        }else {
            request.setAttribute("error", "Username or Password is incorrect!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
