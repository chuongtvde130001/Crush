package ws;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.MessageDAO;
import model.Message;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;

@ServerEndpoint("/getOldMessage")
public class WsGetOld {

    private static Gson gson = new Gson();
    private static JsonParser parser = new JsonParser();
    private static ArrayList<String> list = new ArrayList<>();

    @OnMessage
    public synchronized void onMessage(String message, Session session) throws Exception {

        JsonObject jObj = parser.parse(message).getAsJsonObject();
        int fid = jObj.get("fid").getAsInt();
        int i = jObj.get("i").getAsInt();
        int n = jObj.get("n").getAsInt();

        ArrayList<Message> re = MessageDAO.getNLstMessage(fid, i, n);
        session.getBasicRemote().sendText(gson.toJson(re));
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("Error:" + t.getMessage());
    }
}