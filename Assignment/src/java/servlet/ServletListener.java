package servlet;

import model.FriendStorage;
import model.MessageStorage;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;

public class ServletListener implements ServletContextListener {

    private static ServletContext svlet;
    private static MessageStorage mesStorage;
    private static FriendStorage friStorage;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        mesStorage = new MessageStorage();
        friStorage = new FriendStorage();

        svlet = sce.getServletContext();
        svlet.setAttribute("mesStorage", mesStorage);
        svlet.setAttribute("friStorage", friStorage);
    }

    public static MessageStorage getMesStorage() {
        return mesStorage;
    }

    public static FriendStorage getFriStorage() {
        return friStorage;
    }

    public static ServletContext getCurServlet(){
        return svlet;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
}
