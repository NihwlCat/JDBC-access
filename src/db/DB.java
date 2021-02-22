package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    private static Connection conn = null;

    public static Connection getConn(){
        if (conn == null){
            try {
                Properties prop = loadProperties();
                String url = prop.getProperty("dburl");
                conn = DriverManager.getConnection(url,prop);

            } catch (SQLException e){
                throw new DBException(e.getMessage());
            }
        }

        return conn;
    }

    public static Connection closeConn(){
        try {
            if (conn != null){
                conn.close();
            }
        } catch (SQLException e){
            throw new DBException(e.getMessage());
        }
        return conn;
    }

    private static Properties loadProperties(){
        try (FileInputStream fs = new FileInputStream("db.properties")){
            Properties prop = new Properties();
            prop.load(fs);
            return prop;
        } catch (IOException e){
            throw new DBException(e.getMessage());
        }
    }
}
