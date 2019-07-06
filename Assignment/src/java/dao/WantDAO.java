package dao;

import dbconfig.DBConfig;
import model.User;
import utils.ImageSaver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WantDAO {

    private static final String want = "insert into WANT values(?,null,null,1)";
    private static final String updateWant = "update WANT set AgeBegin = ? , AgeEnd = ? ,Gender = ? where WID = ?";
    private static final String getUsr
            = "DECLARE @AB int, @AE int, @GENDER int\n"
            + "SELECT TOP 1 @AB=AgeBegin, @AE=AgeEnd, @GENDER=Gender FROM WANT WHERE WID = ?\n"
            + "SELECT UID,FullName,Age,Gender,Email,Avatar,Description FROM USERS WHERE Age >= @AB AND Age <= @AE AND @GENDER%Gender=0 AND \n"
            + "USERS.UID NOT IN(SELECT \n"
            + "CASE\n"
            + "    WHEN UserA != ? THEN UserA\n"
            + "    ELSE UserB\n"
            + "END AS UID\n"
            + "FROM FRIENDS\n"
            + "WHERE UserA = ? OR UserB = ?\n"
            + "UNION\n"
            + "SELECT \n"
            + "CASE\n"
            + "    WHEN UserA != ? THEN UserA\n"
            + "    ELSE UserB\n"
            + "END AS UID\n"
            + "FROM CRUSH\n"
            + "WHERE UserA = ? OR UserB = ?) ";

    public static ArrayList<User> getUsrsMatchWant(int uid) {
        ArrayList<User> list = new ArrayList<>();
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(getUsr);
            ps.setInt(1, uid);
            ps.setInt(2, uid);
            ps.setInt(3, uid);
            ps.setInt(4, uid);
            ps.setInt(5, uid);
            ps.setInt(6, uid);
            ps.setInt(7, uid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User usr = new User();
                usr.setUid(rs.getInt(1));
                usr.setFullName(rs.getString(2));
                usr.setAge(rs.getInt(3));
                usr.setGender(UserDAO.getStrGender(rs.getInt(4)));
                usr.setEmail(rs.getString(5));
                usr.setAvatar(ImageSaver.imagePath + rs.getString(6));
                usr.setDescription(rs.getString(7));
                list.add(usr);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean wantPeople(int uid) {
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(want);
            ps.setInt(1, uid);
            int result = ps.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateWant(int ageBegin, int ageEnd, int wanGender, int uid) {
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(updateWant);
            ps.setInt(1, ageBegin);
            ps.setInt(2, ageEnd);
            ps.setInt(3, wanGender);
            ps.setInt(4, uid);
            int result = ps.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        wantPeople(1);
    }
}
