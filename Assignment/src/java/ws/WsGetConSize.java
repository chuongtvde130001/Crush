package ws;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.MessageDAO;

import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

//Get Conversation Size
@ServerEndpoint("/getConSize")
public class WsGetConSize {
    private static Gson gson = new Gson();
    private static JsonParser parser = new JsonParser();
    @OnMessage
    public synchronized void onMessage(String message, Session session) throws Exception {
        JsonObject jObj = parser.parse(message).getAsJsonObject();
        int fid = jObj.get("fid").getAsInt();
        session.getBasicRemote().sendText(gson.toJson(MessageDAO.getConLength(fid)));
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("Error:" + t.getMessage());
    }
}
