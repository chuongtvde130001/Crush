package controller;

import dao.BanDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import model.User;
import dao.UserDAO;
import utils.DateUtil;

public class ProcessLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        User user = UserDAO.checkLogin(username, password);
        if (user != null) {
            Cookie uidCk = new Cookie("uid", String.valueOf(user.getUid()));
            Cookie usrCk = new Cookie("username", user.getUserName());
            uidCk.setMaxAge(-1);
            usrCk.setMaxAge(-1);
            response.addCookie(uidCk);
            response.addCookie(usrCk);

            if (user.getStatus() == 2) {
                response.sendRedirect("update_info.jsp");
            } else if (user.getStatus() == 0) {
                if (user.getUserRight() == 1) {
                    response.sendRedirect("ProcessView?pageid=1");
                } else {
                    response.sendRedirect("/");
                }
            } else if (user.getStatus() == 3) {
                if (BanDAO.getUserBannedExpired(user.getUid())) {
                    if (BanDAO.unbanUser(user.getUid()) == true) {
                        response.sendRedirect("/");
                    }
                } else {
                    request.setAttribute("error", "Your account has been banned!");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return; //Do not add session
                }
            }
        } else {
            request.setAttribute("error", "Username or Password is incorrect!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        // Lưu user vào session
        session.setAttribute("user", user);
    }
}
