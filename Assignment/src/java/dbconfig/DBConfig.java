/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Do Duong
 */
public class DBConfig {

    public static Connection getConnection() {
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=CRUSH";
        String DBUsername = "sa";
        String DBPassword = "duong";
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
