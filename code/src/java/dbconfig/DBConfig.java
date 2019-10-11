/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author duong
 */
public class DBConfig {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:5050;databaseName=Crush", "sa", "duong");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
