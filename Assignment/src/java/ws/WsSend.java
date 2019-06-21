package ws;

import com.google.gson.Gson;

import dao.MessageDAO;
import model.Friend;
import model.Message;
import model.MessageStorage;
import servlet.ServletListener;

import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.util.HashMap;

@ServerEndpoint("/sendMessage")
public class WsSend {
    private static Gson gson = new Gson();
    private static Message mObj;
    private static MessageStorage mesStrorage;
    private static HashMap<Integer,Friend> friends;

    {
        if(mesStrorage ==null)
            mesStrorage = (MessageStorage) ServletListener.getCurrentServlet().getAttribute("mesStrorage");
        if(friends == null)
            friends = (HashMap<Integer,Friend>) ServletListener.getCurrentServlet().getAttribute("friend");
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Open SEND:" + session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Close SEND:" +  session.getId());
    }

    @OnMessage
    public synchronized void onMessage(String message, Session session) {
        System.out.println("onMessage::From=" + session.getId() + " SEND " + message);
        mObj = gson.fromJson(message,Message.class);

        //Write message to mesStorage
        mesStrorage.addMessage(friends.get(mObj.getFid()).getFriendOf(mObj.getFrom()),mObj.getFid(),mObj.getContent());
        MessageDAO.writeMessage(mObj);
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("Error:" + t.getMessage());
    }
}