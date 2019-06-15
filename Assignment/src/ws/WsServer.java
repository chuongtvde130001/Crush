package ws;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.io.IOException;

import static ws.Test.ppp;

@ServerEndpoint("/sendMessage")
public class WsServer {

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