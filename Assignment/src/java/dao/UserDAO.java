package dao;

import dbconfig.DBConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import utils.ImageSaver;
import utils.MD5;
import model.User;

public class UserDAO {
    
    private static final String getUsrSt = "select * from USERS where UserName = ? and PassWord = ?";
    private static final String getUsrSt1 = "select * from USERS where uid = ?";
    private static final String insUserSt = "insert into USERS(UserName,PassWord,Email,Status,UserRight) values(?,?,?,'2',0)";
    private static final String findUsername = "select UserName from USERS where Username = ?";
    private static final String findEmail = "select Email from USERS where Email=?";
    private static final String update = "update USERS set FullName = ? ,Age = ? , Gender = ? ,Avatar =? , Description = ? ,Status = 0 where uid = ?";
    
    public synchronized static String getStrGender(int i) {
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
    
    public synchronized static int getIntGender(String s) {
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
    
    public synchronized static boolean updateUserInfo(String fullName, int age, String gender, String avatarPath, String about, int uid, int ageBegin, int ageEnd, int wantGender) {
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
            ps.close();
            want = WantDAO.updateWant(ageBegin, ageEnd, wantGender, uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return want;
    }
    
    public synchronized static boolean checkUsersName(String UserName) {
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(findUsername);
            ps.setString(1, UserName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public synchronized static boolean checkEmail(String Email) {
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(findEmail);
            ps.setString(1, Email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // Đăng kí User

    public synchronized static User register(String username, String password, String email) {
        User u = null;
        try (Connection con = DBConfig.getConnection()) {
            PreparedStatement ps = con.prepareStatement(insUserSt, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.setString(2, MD5.getMd5(password));
            ps.setString(3, email);
            
            int result = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            boolean kq = WantDAO.wantPeople(rs.getInt(1));
            rs.close();
            
            if (result > 0 && kq == true) {
                u = new User(username, email);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    // Kiểm tra thông tin đăng nhập
    public synchronized static User checkLogin(String username, String password) {
        return getProfile(username, password);
    }

    // Lấy toàn bộ thông tin User sau khi đã đăng nhập
    public synchronized static User getProfile(String username, String password) {
        User u = null;
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(getUsrSt);
            ps.setString(1, username);
            ps.setString(2, MD5.getMd5(password));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new User(
                        rs.getString("UserName"),
                        rs.getString("FullName"),
                        getStrGender(rs.getInt("Gender")),
                        rs.getString("Email"),
                        ImageSaver.imagePath + rs.getString("Avatar"),
                        rs.getString("Description"),
                        rs.getInt("UID"),
                        rs.getInt("Age"),
                        rs.getInt("Status"),
                        rs.getInt("UserRight"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
    
    public synchronized static User getUser(int uid) {
        User u = null;
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(getUsrSt1);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new User();
                u.setUid(uid);
                u.setFullName(rs.getString(4));
                u.setAge(rs.getInt(5));
                u.setGender(getStrGender(rs.getInt(6)));
                u.setAvatar(ImageSaver.imagePath + rs.getString(8));
                u.setDescription(rs.getString(9));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
    
    public static HashMap<Integer, User> getUsersByFullName(String fullName) {
        HashMap<Integer, User> ds = new HashMap<Integer, User>();
        User u = null;
        try (Connection conn = DBConfig.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from USERS where FullName like N'%" + fullName + "%'");
            while (rs.next()) {
                u = new User(
                        rs.getString("UserName"),
                        rs.getString("FullName"),
                        getStrGender(rs.getInt("Gender")),
                        rs.getString("Email"),
                        ImageSaver.imagePath + rs.getString("Avatar"),
                        rs.getString("Description"),
                        rs.getInt("UID"),
                        rs.getInt("Age"),
                        rs.getInt("Status"),
                        rs.getInt("UserRight"));
                ds.put(rs.getInt("UID"), u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    public static HashMap<Integer, User> getUsersByUserName(String username) {
        HashMap<Integer, User> ds = new HashMap<Integer, User>();
        User u = null;
        try (Connection conn = DBConfig.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from USERS where UserName like N'%" + username + "%'");
            while (rs.next()) {
                u = new User(
                        rs.getString("UserName"),
                        rs.getString("FullName"),
                        getStrGender(rs.getInt("Gender")),
                        rs.getString("Email"),
                        ImageSaver.imagePath + rs.getString("Avatar"),
                        rs.getString("Description"),
                        rs.getInt("UID"),
                        rs.getInt("Age"),
                        rs.getInt("Status"),
                        rs.getInt("UserRight"));
                ds.put(rs.getInt("UID"), u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    public static HashMap<Integer, User> getUsersByEmail(String email) {
        HashMap<Integer, User> ds = new HashMap<Integer, User>();
        User u = null;
        try (Connection conn = DBConfig.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from USERS where Email like N'%" + email + "%'");
            while (rs.next()) {
                u = new User(
                        rs.getString("UserName"),
                        rs.getString("FullName"),
                        getStrGender(rs.getInt("Gender")),
                        rs.getString("Email"),
                        ImageSaver.imagePath + rs.getString("Avatar"),
                        rs.getString("Description"),
                        rs.getInt("UID"),
                        rs.getInt("Age"),
                        rs.getInt("Status"),
                        rs.getInt("UserRight"));
                ds.put(rs.getInt("UID"), u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
}
