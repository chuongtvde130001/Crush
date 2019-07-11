package dao;

import dbconfig.DBConfig;
import model.Message;
import java.sql.*;
import java.util.ArrayList;

public class MessageDAO {

    private static final String writeMesSt = "INSERT INTO MESSAGES (FID,FR,Content,Time,Status) VALUES (?,?,?,getdate(),0)";
    private static final String getMes = "SELECT FID,FR,Content FROM MESSAGES WHERE FID = ?\n" +
            "ORDER BY MID DESC \n" +
            "OFFSET ?*? ROWS\n" +
            "FETCH NEXT ? ROWS ONLY\n";
    private static final String getConLength = "SELECT COUNT(MID) FROM MESSAGES WHERE FID=?";

    public synchronized static boolean writeMessage(Message mes){
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
    //i is n*i row skipped, n is number of row to read
    public synchronized static ArrayList<Message> getNLstMessage(int fid, int i, int n){
        ArrayList<Message> list = new ArrayList<>();
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(getMes);
            ps.setInt(1, fid);
            ps.setInt(2, i);
            ps.setInt(3, n);
            ps.setInt(4, n);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new Message(rs.getInt(1),rs.getInt(2),rs.getString(3)));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public synchronized static int getConLength(int fid){
        int length = -1;
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(getConLength);
            ps.setInt(1, fid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
               length = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }
}
