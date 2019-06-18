package ws;

import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ServerEndpoint("/sendMessage")
public class WsSend {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Open:" + session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Close:" +  session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("onMessage::From=" + session.getId() + " Message=" + message);
        try {
            session.getBasicRemote().sendText("Hello Client " + session.getId() + "!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("Error:" + t.getMessage());
    }
}