package ws;

import com.google.gson.Gson;

import dao.MessageDAO;
import model.Message;

import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ServerEndpoint("/sendMessage")
public class WsSend {
    public static Gson gson = new Gson();
    public static Message mesObj;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Open:" + session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Close:" +  session.getId());
    }

    @OnMessage
    public synchronized void onMessage(String message, Session session) {
        System.out.println("onMessage::From=" + session.getId() + " Message=" + message);
        mesObj = gson.fromJson(message,Message.class);
        MessageDAO.writeMessage(mesObj);
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("Error:" + t.getMessage());
    }
}