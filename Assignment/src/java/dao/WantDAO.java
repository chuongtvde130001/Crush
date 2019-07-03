package dao;

import dbconfig.DBConfig;
import model.User;
import utils.ImageSaver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WantDAO {

    private static final String getUsr =
            "DECLARE @AB int, @AE int, @GENDER int\n" +
            "SELECT TOP 1 @AB=AgeBegin, @AE=AgeEnd, @GENDER=Gender FROM WANT WHERE WID = ?\n" +
            "SELECT UID,FullName,Age,Gender,Avatar FROM USERS WHERE Age >= @AB AND Age <= @AE AND @GENDER%Gender=0\n";

    public static ArrayList<User> getUsrsMatchWant(int uid) {
        ArrayList<User> list = new ArrayList<>();
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(getUsr);
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
        for (User i:getUsrsMatchWant(1)){
            System.out.println(i.getUid()+" "+i.getFullName()+" "+i.getAge()+" "+i.getGender()+" "+i.getAvatar());
        }
    }
}
