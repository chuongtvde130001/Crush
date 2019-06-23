package servlet;

import model.Friend;
import model.MessageStorage;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;

public class ServletListener implements ServletContextListener {

    private static ServletContext svlet;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        svlet = sce.getServletContext();
        svlet.setAttribute("mesStorage",new MessageStorage());
        svlet.setAttribute("friend",new HashMap<Integer,Friend>());
        ((HashMap)sce.getServletContext().getAttribute("friend")).put(1,new Friend(1,1,2));
    }

    public static ServletContext getCurrentServlet(){
        return svlet;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
