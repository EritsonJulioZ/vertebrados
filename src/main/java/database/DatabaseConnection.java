package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    static String databaseName = "vertebrados";
    static String databaseUser = "root";
    static String databasePassword = "Cun2024*";

    static String url = "jdbc:mysql://localhost:3306/" + databaseName;

    public static Connection getConnection() {
        Connection databaseLink = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try{
                databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return databaseLink;
    }
}
