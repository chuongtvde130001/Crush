package dao;

import dbconfig.DBConfig;
import model.User;
import utils.ImageSaver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class FriendDAO {

    private static final String getFidSt = "SELECT\n" +
            "CASE\n" +
            "    WHEN UserA != ? THEN UserA\n" +
            "    ELSE UserB\n" +
            "END\n" +
            "FROM FRIENDS\n" +
            "WHERE FID = ?";
    private static final String getFriendSt = "SELECT FID, USERS.UID, USERS.FullName, USERS.Avatar FROM\n" +
            "(SELECT FID,\n" +
            "CASE\n" +
            "    WHEN UserA != ? THEN UserA\n" +
            "    ELSE UserB\n" +
            "END AS UID\n" +
            "FROM FRIENDS\n" +
            "WHERE UserA = ? OR UserB = ?\n" +
            ") AS R1\n" +
            "LEFT JOIN USERS ON (R1.UID = USERS.UID)";
    private static final String checkCrushSt = "SELECT * FROM CRUSH WHERE UserA = ? AND UserB= ?";
    private static final String addCrushSt = "INSERT INTO CRUSH(UserA, UserB) VALUES(?,?)";
    private static final String addFriendSt = "INSERT INTO FRIENDS(UserA, UserB, FR) VALUES(?,?,getdate())";
    private static final String deleteCrushSt = "DELETE FROM CRUSH WHERE CID=?";
    private static final String unCrushSt = "DELETE FROM CRUSH WHERE UserA=? AND UserB=?";
    private static final String tarCrushUsr =
            "SELECT UID,FullName,Age,Gender,Avatar FROM CRUSH \n" +
            "LEFT JOIN USERS ON(CRUSH.UserA = USERS.UID)\n" +
            "WHERE UserB = ?";

    public static int getFriUid(int fid,int userA){
        int uid = -1;
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(getFidSt);
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

    // Get friend's info to user object
    public static HashMap<Integer, User> getFriends(int uid) {
        HashMap<Integer, User> map = new HashMap<>();
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
                map.put(rs.getInt(1),usr);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    //Action Crush
    public static boolean actionCrush(int uid, int target){
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
                isFriend=true;
            }else {
                ps = conn.prepareStatement(addCrushSt);
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

    public static boolean actionUnCrush(int uid, int target){
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

    public static ArrayList<User> getTarsCrushUser(int uid) {
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

    public static void main(String[] args) {

        System.out.println(getTarsCrushUser(1).get(0).getAvatar());
    }
}
