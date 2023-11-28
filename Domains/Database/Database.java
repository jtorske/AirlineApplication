package Domains.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private Connection dbConnection;
    private final String URL = "jdbc:mysql://localhost/AIRLINE";
    private final String USERNAME = "oop";
    private final String PASSWORD = "password";

    private boolean connectionAvailable() {
        try {
            dbConnection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
            // Send error to user
        }
    }

    public Connection getConnection() throws SQLException{
        if (connectionAvailable()){
            return dbConnection;
        }
        else {
            throw new SQLException();
        }

    }

}