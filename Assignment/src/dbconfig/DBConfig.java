/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

    public static Connection getConnection() {
        String driver = "net.sourceforge.jtds.jdbc.Driver";
        String url = "jdbc:jtds:sqlserver://DESKTOP-42TIDQG:1433/CRUSH";
        String DBUsername = "javaweb123";
        String DBPassword = "1234";
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, DBUsername, DBPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
