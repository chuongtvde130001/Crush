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
}
