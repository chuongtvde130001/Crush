package dao;

import dbconfig.DBConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Md5Encryptor;

import model.User;

public class UserDAO {
    private static final String getUsrSt = "select * from USERS where UserName = ? and PassWord = ?";
    private static final String insUserSt = "insert into USERS(UserName,PassWord,Email) values(?,?,?)";
    private static final String getAvtSt = "select * from USERS where UserName = ?";

    // Đăng kí User
    public static void register(User user) throws Exception {
        Connection con =  DBConfig.getConnection();
        PreparedStatement ps = con.prepareStatement(insUserSt);
        ps.setString(1, user.getUserName());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getEmail());
        ps.executeUpdate();
        ps.close();
        con.close();
    }
// Kiểm tra thông tin đăng nhập

    public static User checkLogin(String username, String password) {
        return getProfile(username, password);
    }
// Lấy toàn bộ thông tin User sau khi đã đăng nhập

    public static User getProfile(String username, String password) {
        User u = null;
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(getUsrSt);
            ps.setString(1, username);
            ps.setString(2, Md5Encryptor.getMd5(password));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new User(rs.getString("UserName"),
                        rs.getString("FullName"),
                        rs.getString("Gender"),
                        rs.getString("Email"),
                        rs.getInt("UID"),
                        rs.getInt("Age"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
    // Lấy Avatar User
    public static byte[] getUserAvatar(String username){
        try (Connection conn = DBConfig.getConnection()){
            PreparedStatement ps = conn.prepareStatement(getAvtSt);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getBytes("Avatar");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        System.out.println(checkLogin("tomcat123","1234").getEmail());
    }
}
