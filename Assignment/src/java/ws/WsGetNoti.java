package ws;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.MessageDAO;
import dao.UserDAO;
import model.Message;
import model.NotiStorage;
import model.User;
import servlet.ServletListener;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashMap;

@ServerEndpoint("/getNoti")
public class WsGetNoti {

    private static Gson gson = new Gson();
    private static JsonParser parser = new JsonParser();
    private static ArrayList<String> list = new ArrayList<>();

    @OnMessage
    public synchronized void onMessage(String message, Session session) throws Exception {
        System.out.println("SOMEONE GET GET NOTI!!!!");
        try {
            JsonObject jObj = parser.parse(message).getAsJsonObject();
            int uid = jObj.get("uid").getAsInt();

            HashMap<String, ArrayList<Integer>> raw = ServletListener.getNotiStorage().getNotis(uid);
            HashMap<String, ArrayList<User>> out = new HashMap<>();
            System.out.println("HAVE NOTI"+raw);
            if (raw != null && raw.size() != 0) {
                for (String i : raw.keySet()) {
                    out.put(i, new ArrayList<>());
                    for (Integer j : raw.get(i)) {
                        out.get(i).add(UserDAO.getUser(j));
                    }
                }
            }
            session.getBasicRemote().sendText(gson.toJson(out));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("Error:" + t.getMessage());
    }
}