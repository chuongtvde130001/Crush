package dao;

import com.google.gson.Gson;
import dbconfig.DBConfig;
import model.Friend;
import model.Message;
import model.MessageStorage;
import servlet.ServletListener;

import javax.servlet.ServletContext;
import java.sql.*;
import java.util.HashMap;

public class MessageDAO {

    private static final String writeMesSt = "INSERT INTO MESSAGES (FID,FR,Content,Time,Status) VALUES (?,?,?,getdate(),0)";

    public static boolean writeMessage(Message mes){
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(writeMesSt);
            ps.setInt(1, mes.getFid());
            ps.setInt(2, mes.getFrom());
            ps.setString(3, mes.getContent());
            ps.execute();
            ps.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args){
        writeMessage(new Gson().fromJson("{\"fid\":1,\"from\":1,\"content\":\"hi\"}",Message.class));
    }
    public int getAllUnreadMessage(int index){
        return 0;
    }
}
