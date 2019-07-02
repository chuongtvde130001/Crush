package ws;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.MessageStorage;
import servlet.ServletListener;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashMap;

@ServerEndpoint("/getNewMessage")
public class WsGetNew {

    private static Gson gson = new Gson();
    private static JsonParser parser = new JsonParser();
    private static ArrayList<String> list = new ArrayList<>();
    private static MessageStorage mesStrorage;

    @OnMessage
    public synchronized void onMessage(String message, Session session) throws Exception {
        if(mesStrorage==null)
            mesStrorage = ServletListener.getMesStorage();
        System.out.println("onMessage::From=" + session.getId() + " GET "+message);
        JsonObject jObj = parser.parse(message).getAsJsonObject();

        int uid = jObj.get("uid").getAsInt();

        HashMap<Integer,ArrayList<String>> output = mesStrorage.getMessage(uid);

        System.out.println("YY"+gson.toJson(output));
        session.getBasicRemote().sendText(gson.toJson(output));
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("Error:" + t.getMessage());
    }
}