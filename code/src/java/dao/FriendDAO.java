package dao;

import dbconfig.DBConfig;
import model.Message;
import model.User;
import servlet.ServletListener;
import utils.ImageSaver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class FriendDAO {

    private static final String getFriSt = "SELECT\n" +
            "CASE\n" +
            "    WHEN UserA != ? THEN UserA\n" +
            "    ELSE UserB\n" +
            "END\n" +
            "FROM FRIENDS\n" +
            "WHERE FID = ?";
    private static final String getFidSt = "SELECT TOP 1 FID FROM FRIENDS WHERE UserA=? AND UserB=? OR UserA=? AND UserB=?";
    private static final String getFriendSt = "SELECT R1.FID, USERS.UID, USERS.FullName, USERS.Avatar, SUB.FR, SUB.Content FROM\n" +
            "(SELECT FID,\n" +
            "CASE\n" +
            "    WHEN UserA != ? THEN UserA\n" +
            "    ELSE UserB\n" +
            "END AS UID\n" +
            "FROM FRIENDS\n" +
            "WHERE UserA = ? OR UserB = ?\n" +
            ") AS R1\n" +
            "LEFT JOIN USERS ON (R1.UID = USERS.UID)\n" +
            "LEFT JOIN (\n" +
            "SELECT LAS.FID,FR, Content FROM\n" +
            "(SELECT FID, MAX(MID) as MID FROM MESSAGES GROUP BY FID) AS LAS\n" +
            "LEFT JOIN MESSAGES ON (LAS.MID=MESSAGES.MID)) AS SUB ON (R1.FID=SUB.FID)";
    private static final String checkCrushSt = "SELECT * FROM CRUSH WHERE UserA = ? AND UserB= ?";
    private static final String addCrushSt = "INSERT INTO CRUSH(UserA, UserB) VALUES(?,?)";
    private static final String addFriendSt = "INSERT INTO FRIENDS(UserA, UserB, FR) VALUES(?,?,getdate())";
    private static final String deleteCrushSt = "DELETE FROM CRUSH WHERE CID=?";
    private static final String unCrushSt = "DELETE FROM CRUSH WHERE UserA=? AND UserB=?";
    private static final String tarCrushUsr =
            "SELECT UID,FullName,Age,Gender,Avatar FROM CRUSH \n" +
            "LEFT JOIN USERS ON(CRUSH.UserA = USERS.UID)\n" +
            "WHERE UserB = ?";

    public synchronized static int getFriUid(int fid,int userA){
        int uid = -1;
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(getFriSt);
            ps.setInt(1, userA);
            ps.setInt(2, fid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                uid = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uid;
    }
    //Temporary
    public synchronized static int getFid(int userA, int userB) {
        int fid = -1;
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(getFidSt);
            ps.setInt(1, userA);
            ps.setInt(2, userB);
            ps.setInt(3, userB);
            ps.setInt(4, userA);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                fid = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fid;
    }

    // Get friend's info to user object
    public synchronized static HashMap<Message, User> getFriends(int uid) {
        HashMap<Message, User> map = new HashMap<>();
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(getFriendSt);
            ps.setInt(1, uid);
            ps.setInt(2, uid);
            ps.setInt(3, uid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                User usr = new User();
                usr.setUid(rs.getInt(2));
                usr.setFullName(rs.getString(3));
                usr.setAvatar(ImageSaver.imagePath+rs.getString(4));
                map.put(new Message(rs.getInt(1),rs.getInt(5),rs.getString(6)),usr);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    //Action Crush
    public synchronized static boolean actionCrush(int uid, int target){
        boolean isFriend=false;
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(checkCrushSt);
            ps.setInt(1, target);
            ps.setInt(2, uid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                ps = conn.prepareStatement(deleteCrushSt);
                ps.setInt(1, rs.getInt(1));
                ps.execute();
                ps = conn.prepareStatement(addFriendSt);
                //Send notification to target and you
                ServletListener.getNotiStorage().addNoti(uid,target,1);
                ServletListener.getNotiStorage().addNoti(target,uid,1);
                isFriend=true;
            }else {
                ps = conn.prepareStatement(addCrushSt);
                ServletListener.getNotiStorage().addNoti(uid,target,2);
            }
            ps.setInt(1, uid);
            ps.setInt(2, target);
            ps.execute();
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isFriend;
    }

    public synchronized static boolean actionUnCrush(int uid, int target){
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(unCrushSt);
            ps.setInt(1, uid);
            ps.setInt(2, target);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public synchronized static ArrayList<User> getTarsCrushUser(int uid) {
        ArrayList<User> list = new ArrayList<>();
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(tarCrushUsr);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                User usr = new User();
                usr.setUid(rs.getInt(1));
                usr.setFullName(rs.getString(2));
                usr.setAge(rs.getInt(3));
                usr.setGender(UserDAO.getStrGender(rs.getInt(4)));
                usr.setAvatar(ImageSaver.imagePath+rs.getString(5));
                list.add(usr);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
