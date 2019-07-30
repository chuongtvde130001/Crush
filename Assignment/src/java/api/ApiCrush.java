package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.FriendDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/crush")
public class ApiCrush {

    private static Gson gson = new Gson();
    private static JsonParser parser = new JsonParser();

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String crush(final String data) {
        JsonObject jObj = parser.parse(data).getAsJsonObject();
        boolean actionCrush = jObj.get("crush").getAsBoolean();
        int uid = jObj.get("uid").getAsInt();
        int target = jObj.get("target").getAsInt();
        System.out.println("XXX"+actionCrush+"XXX"+uid+"XXX"+target);
        return gson.toJson((actionCrush==true) ? FriendDAO.actionCrush(uid, target):FriendDAO.actionUnCrush(uid, target));
    }
}
