package ws;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.FriendDAO;

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
        try {
            JsonObject jObj = parser.parse(message).getAsJsonObject();
            boolean actionCrush = jObj.get("crush").getAsBoolean();
            int uid = jObj.get("uid").getAsInt();
            int target = jObj.get("target").getAsInt();
            session.getBasicRemote().sendText(gson.toJson((actionCrush == true) ? FriendDAO.actionCrush(uid, target) : FriendDAO.actionUnCrush(uid, target)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("Error:" + t.getMessage());
    }
}