package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.FriendDAO;
import dao.UserDAO;
import servlet.ServletListener;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;

@Path("/getNoti")
public class ApiGetNoti {

    private static Gson gson = new Gson();
    private static JsonParser parser = new JsonParser();

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String crush(@QueryParam("uid") int uid) {
        System.out.println("SOMEONE GET GET NOTI!!!!" +uid);
        HashMap<String, ArrayList<Integer>> raw = ServletListener.getNotiStorage().getNotis(uid);
        System.out.println("NOTI SIZE: "+uid+":"+((raw==null) ? 0:raw.size()));
        if (raw != null && raw.size() != 0) {
            HashMap<String, ArrayList<Object>> out = new HashMap<>();
            ArrayList<Object> fris = null;
            System.out.println("HAVE NOTI"+raw);
            for (String i : raw.keySet()) {
                out.put(i, new ArrayList<>());
                for (Integer j : raw.get(i)) {
                    out.get(i).add(UserDAO.getUser(j));
                }
            }
            if(out.get("friend")!=null){
                // <uid,fid>
                fris = new ArrayList<>();
                for(Integer j : raw.get("friend")){
                    fris.add(FriendDAO.getFid(j,uid));
                }
                out.put("fid",fris);
            }
            return gson.toJson(out);
        }
        return null;
    }
}

