package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.MessageDAO;
import model.Message;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getOldMessage")
public class ApiGetOld {

    private static Gson gson = new Gson();
    private static JsonParser parser = new JsonParser();
    private static ArrayList<String> list = new ArrayList<>();

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getOldMes(@QueryParam("fid") int fid,@QueryParam("i") int i,@QueryParam("n") int n) {
        ArrayList<Message> re = MessageDAO.getNLstMessage(fid, i, n);
        return gson.toJson(re);
    }
}