package dao;

import dbconfig.DBConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.ImageSaver;
import utils.MD5;

import model.User;

public class UserDAO {

    private static final String getUsrSt = "select * from USERS where UserName = ? and PassWord = ?";
    private static final String insUserSt = "insert into USERS(UserName,PassWord,Email,Status) values(?,?,?,'2')";
    private static final String getAvtSt = "select Avatar from USERS where uid = ?";
    private static final String findUsername = "select UserName from USERS where Username = ?";
    private static final String findEmail = "select Email from USERS where Email=?";
    private static final String update = "update USERS set FullName = ? ,Age = ? , Gender = ? ,Avatar =? , Description = ? ,Status = 0 where uid = ?";

    public static String getStrGender(int i) {
        switch (i) {
            case 2:
                return "Male";
            case 3:
                return "Female";
            case 5:
                return "Lgbt";
        }
        return "";
    }

    public static int getIntGender(String s) {
        switch (s) {
            case "Male":
                return 2;
            case "Female":
                return 3;
            case "Lgbt":
                return 5;
        }
        return -1;
    }

    public static boolean updateUserInfo(String fullName,
            int age, String gender, String avatarPath, String about, int uid, int ageBegin, int ageEnd, int wantGender) {
        boolean want = false;
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(update);
            ps.setString(1, fullName);
            ps.setInt(2, age);
            ps.setInt(3, getIntGender(gender));
            ps.setString(4, avatarPath);
            ps.setString(5, about);
            ps.setInt(6, uid);
            ps.execute();
            want = WantDAO.updateWant(ageBegin, ageEnd, wantGender, uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return want;
    }

    public static void main(String[] args) {
        System.out.println(updateUserInfo("xyz", 30, "Male", "a", "this is test", 1, 18, 30, 5));
    }

    public static boolean checkUsersName(String UserName) {

        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(findUsername);
            ps.setString(1, UserName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkEmail(String Email) {

        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(findEmail);
            ps.setString(1, Email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // Đăng kí User

    public static User register(String username, String password, String email) {
        User u = null;
        try (Connection con = DBConfig.getConnection()) {
            boolean kq = WantDAO.wantPeople(u.getUid());
            PreparedStatement ps = con.prepareStatement(insUserSt);
            ps.setString(1, username);
            ps.setString(2, MD5.getMd5(password));
            ps.setString(3, email);
            int result = ps.executeUpdate();
            if (result > 0 && kq == true) {
                u = new User(username, password, email);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
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
            ps.setString(2, MD5.getMd5(password));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new User(rs.getString("Password"),
                        rs.getString("UserName"),
                        rs.getString("FullName"),
                        getStrGender(rs.getInt("Gender")),
                        rs.getString("Email"),
                        ImageSaver.imagePath + rs.getString("Avatar"),
                        rs.getInt("UID"),
                        rs.getInt("Age"),
                        rs.getInt("Status"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    // Lấy Avatar User
    public static String getUserAvatar(int uid) {
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(getAvtSt);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return ImageSaver.imagePath + rs.getString("Avatar");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String crushPeople(String oldDes, String des, int crushId) {
        String sql = "update USERS set Description = ? where UID = ?";
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, oldDes + des + ",");
            ps.setInt(2, crushId);
            int result = ps.executeUpdate();
            if (result > 0) {
                return oldDes + des;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getDes(int crushId) {
        String sql = "select Description from USERS where UID = ?";
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, crushId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Description");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
