/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import dbconfig.DBConfig;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author duong
 */

public class BanDAO {

    private static final String updateUserStatus = "update USERS set Status = ? where UID = ?";
    private static final String banUser = "insert into ADMIN_BAN values(?,?,?)";
    private static final String unbanUser = "delete from ADMIN_BAN where ID = ?";
    private static final String getBanInfo = "select DURATION from ADMIN_BAN where ID = ?";
    

    public static boolean searchUserBan(int UID) {
        String query = "select * from ADMIN_BAN where ID = ?";
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, UID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean getUserBannedExpired(int UID){
        try (Connection conn = DBConfig.getConnection()){
            PreparedStatement ps = conn.prepareStatement(getBanInfo);
            ps.setInt(1, UID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int duration = rs.getInt("DURATION")*(24 * 60 * 60 * 1000);
                Date startDate = rs.getDate("START_TIME");
                long gap = System.currentTimeMillis() - startDate.getTime();
                if(duration-gap>0) return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean banUser(int UID, String startTime, int duration) {
        try (Connection conn = DBConfig.getConnection()) {
            boolean search = searchUserBan(UID);
            PreparedStatement ps1 = conn.prepareStatement(updateUserStatus);
            PreparedStatement ps2 = conn.prepareStatement(banUser);
            ps1.setInt(1, 3);
            ps1.setInt(2, UID);
            ps2.setInt(1, UID);
            ps2.setString(2, startTime);
            ps2.setInt(3, duration);
            int Status = ps1.executeUpdate();
            int InsertBan = ps2.executeUpdate();
            if (Status > 0 && InsertBan > 0 && search==false) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
     public static boolean unbanUser(int UID) {
        try (Connection conn = DBConfig.getConnection()) {
            PreparedStatement ps1 = conn.prepareStatement(updateUserStatus);
            PreparedStatement ps2 = conn.prepareStatement(unbanUser);
            ps1.setInt(1, 0);
            ps1.setInt(2, UID);
            ps2.setInt(1, UID);
            int result = ps1.executeUpdate()*ps2.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
