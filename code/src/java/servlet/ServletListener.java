package servlet;

import dao.UserDAO;
import model.FriendStorage;
import model.MessageStorage;
import model.NotiStorage;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;

public class ServletListener implements ServletContextListener {

    private static ServletContext svlet;
    private static MessageStorage mesStorage;
    private static FriendStorage friStorage;
    private static NotiStorage notiStorage;
    private static int totalUsers; 
    

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        mesStorage = new MessageStorage();
        friStorage = new FriendStorage();
        notiStorage = new NotiStorage();
        totalUsers = UserDAO.getUsersCount();
        
        svlet = sce.getServletContext();
        svlet.setAttribute("mesStorage", mesStorage);
        svlet.setAttribute("totalUsers", totalUsers);
        svlet.setAttribute("friStorage", friStorage);
        svlet.setAttribute("notiStorage", notiStorage);
    }

    public static MessageStorage getMesStorage() {
        return mesStorage;
    }

    public static FriendStorage getFriStorage() {
        return friStorage;
    }

    public static NotiStorage getNotiStorage() {
        return notiStorage;
    }

    public static ServletContext getCurServlet(){
        return svlet;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
