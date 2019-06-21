package ws;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Friend;
import model.MessageStorage;
import servlet.ServletListener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;

@ServerEndpoint("/getMessage")
public class WsGet {

    private static Gson gson = new Gson();
    private static JsonParser parser = new JsonParser();
    private static ArrayList<String> list = new ArrayList<>();
    private static MessageStorage mesStrorage;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Open GET:" + session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Close GET:" +  session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        if(mesStrorage==null)
            mesStrorage = (MessageStorage) ServletListener.getCurrentServlet().getAttribute("mesStorage");
        System.out.println("onMessage::From=" + session.getId() + " GET "+message);
        JsonObject jObj = parser.parse(message).getAsJsonObject();

        int fid = jObj.get("fid").getAsInt();
        int uid = jObj.get("uid").getAsInt();

//        System.out.println(mesStrorage.getMessage(uid,fid)==null);
        if((list = mesStrorage.getMessage(uid,fid))!=null)
            session.getBasicRemote().sendText(gson.toJson(list));
        else{
            session.getBasicRemote().sendText("null");
        }
    }

    public static void main(String[] args) {
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("Error:" + t.getMessage());
    }
}