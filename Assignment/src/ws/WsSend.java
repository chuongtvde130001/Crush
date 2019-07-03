package ws;

import com.google.gson.Gson;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.MessageDAO;
import model.FriendStorage;
import model.Message;
import model.MessageStorage;
import servlet.ServletListener;

import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ServerEndpoint("/sendMessage")
public class WsSend {
    private static Gson gson = new Gson();
    private static Message mObj;
    private static MessageStorage mesStorage;
    private static FriendStorage friStorage;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Open SEND:" + session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Close SEND:" +  session.getId());
    }

    @OnMessage
    public synchronized void onMessage(String message, Session session) throws Exception{
        if(mesStorage == null)
            mesStorage = ServletListener.getMesStorage();
        if(friStorage == null)
            friStorage = ServletListener.getFriStorage();

        System.out.println("onMessage::From=" + session.getId() + " SEND " + message);
        mObj = gson.fromJson(message,Message.class);

        //Write message to mesStorage
        mesStorage.addMessage(mObj.getFid(),friStorage.getMyFriend(mObj.getFid(), mObj.getFrom()),mObj.getContent());
        MessageDAO.writeMessage(mObj);
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("Error:" + t.getMessage());
    }
}