package dao;

import com.google.gson.Gson;
import dbconfig.DBConfig;
import model.Message;

import java.sql.*;
import java.util.Calendar;

public class MessageDAO {

    private static final String writeMesSt = "INSERT INTO MESSAGES (FID,FR,Content,Time,Status) VALUES (?,?,?,?,0)";

    public static boolean writeMes(Message mes){
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(writeMesSt);
            ps.setString(1, String.valueOf(mes.getFid()));
            ps.setString(2, String.valueOf(mes.getFrom()));
            ps.setString(3, mes.getContent());
            ps.setTimestamp(4, new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
            ps.execute();
            ps.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args){
        new Gson().toJson(new Message(1,1,"hi"));
    }
    public void getAllUnreadMes(){

    }
}
