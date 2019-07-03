package ws;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/crush")
public class WsCrush {

    private static Gson gson = new Gson();
    private static JsonParser parser = new JsonParser();

    @OnMessage
    public synchronized void onMessage(String message, Session session) throws Exception {
        JsonObject jObj = parser.parse(message).getAsJsonObject();
        int uid = jObj.get("uid").getAsInt();
        int target = jObj.get("crushWho").getAsInt();
//        session.getBasicRemote().sendText(gson.toJson(MessageDAO.getConLength(fid)));
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("Error:" + t.getMessage());
    }
}