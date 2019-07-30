package api;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import model.MessageStorage;
import servlet.ServletListener;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;

@Path("/getNewMessage")
public class ApiGetNew {

    private static Gson gson = new Gson();
    private static JsonParser parser = new JsonParser();
    private static ArrayList<String> list = new ArrayList<>();
    private static MessageStorage mesStrorage;

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getNewMes(@QueryParam("uid") int uid) {
        if (mesStrorage == null)
            mesStrorage = ServletListener.getMesStorage();
        HashMap<Integer, ArrayList<String>> output = mesStrorage.getMessage(uid);
        System.out.println("YY" + gson.toJson(output));
        return gson.toJson(output);
    }
}
