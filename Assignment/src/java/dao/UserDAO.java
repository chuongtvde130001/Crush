/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dbconfig.DBConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;
import util.MD5;

/**
 *
 * @author Do Duong
 */
public class UserDAO {
    // Đăng kí User
    public static boolean register(String username,String email,String password){
        String sql = "insert into USERS(UserName,PassWord,Email) values(?,?,?)";
        try (Connection conn =  DBConfig.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, MD5.getMd5(password));
            ps.setString(3, email);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
// Kiểm tra thông tin đăng nhập
    
    public static User checkLogin(String username, String password) {
        return getProfile(username, password);
    }
// Lấy toàn bộ thông tin User sau khi đã đăng nhập

    public static User getProfile(String username, String password) {
        User u = null;
        String sql = "select * from USERS where UserName = ? and PassWord = ?";
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, MD5.getMd5(password));
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
        String sql = "select * from USERS where UserName = ?";
        try (Connection conn = DBConfig.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
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
