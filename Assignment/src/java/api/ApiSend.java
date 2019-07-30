package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.MessageDAO;
import model.FriendStorage;
import model.Message;
import model.MessageStorage;
import servlet.ServletListener;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


@Path("/sendMessage")
public class ApiSend {

    private static Gson gson = new Gson();
    private static Message mObj;
    private static MessageStorage mesStorage;
    private static FriendStorage friStorage;

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public void send(final String data) {
        if(mesStorage == null)
            mesStorage = ServletListener.getMesStorage();
        if(friStorage == null)
            friStorage = ServletListener.getFriStorage();

        mObj = gson.fromJson(data,Message.class);
        //Write message to mesStorage
        mesStorage.addMessage(mObj.getFid(),friStorage.getMyFriend(mObj.getFid(), mObj.getFrom()),mObj.getContent());
        MessageDAO.writeMessage(mObj);
    }
}
