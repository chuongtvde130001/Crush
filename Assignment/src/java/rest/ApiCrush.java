package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.FriendDAO;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/crush")
public class ApiCrush {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(String input) {
        System.out.println("XXXX"+input);
        return "Hello Jersey";
    }
//
//    private static Gson gson = new Gson();
//    private static JsonParser parser = new JsonParser();
//
//    @OnMessage
//    public synchronized void onMessage(String message, Session session) throws Exception {
//        JsonObject jObj = parser.parse(message).getAsJsonObject();
//        boolean actionCrush = jObj.get("crush").getAsBoolean();
//        int uid = jObj.get("uid").getAsInt();
//        int target = jObj.get("target").getAsInt();
//        session.getBasicRemote().sendText(gson.toJson((actionCrush==true) ? FriendDAO.actionCrush(uid, target):FriendDAO.actionUnCrush(uid, target)));
//    }
}
