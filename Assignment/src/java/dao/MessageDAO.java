package java.dao;

import com.google.gson.Gson;
import java.dbconfig.DBConfig;
import java.model.Message;

import java.sql.*;

public class MessageDAO {

    private static final String writeMesSt = "INSERT INTO MESSAGES (FID,FR,Content,Time,Status) VALUES (?,?,?,getdate(),0)";

    public static boolean writeMessage(Message mes){
        System.out.println(mes.getFid()+"XXX"+mes.getFrom());
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
    public void getAllUnreadMes(){

    }
}
