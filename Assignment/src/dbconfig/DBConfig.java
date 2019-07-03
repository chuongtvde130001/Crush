package dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    private static String driver = "net.sourceforge.jtds.jdbc.Driver";
    private static String url = "jdbc:jtds:sqlserver://localhost:1433/CRUSH";
    private static String DBUsername = "javaweb123";
    private static String DBPassword = "1234";

    public static Connection getConnection() {
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
