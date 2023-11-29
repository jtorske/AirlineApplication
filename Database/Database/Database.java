package Domains.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private Connection dbConnection;
    private final String URL = "jdbc:mysql://localhost:3306/AIRLINE";
    private final String USERNAME = "oop";
    private final String PASSWORD = "password";

    private boolean connectionAvailable() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
            // Send error to user
        } catch (Exception e) {
            // Something
            e.printStackTrace();
            return false;
        }
    }

    public Connection getConnection() throws SQLException {
        if (connectionAvailable()) {
            return dbConnection;
        } else {
            throw new SQLException();
        }

    }

    public static List<List<String>> dbExecute(String command) {
        Database db = new Database();
        Connection con;
        List<List<String>> rows = new ArrayList<>();

        try {
            con = db.getConnection();
            Statement statement = con.createStatement();
            ResultSet queryResult = statement.executeQuery(command);

            // Get column names
            int columnCount = queryResult.getMetaData().getColumnCount();
            List<String> columnNames = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(queryResult.getMetaData().getColumnName(i));
            }

            // List to store rows

            // Iterate through the result set
            while (queryResult.next()) {
                // List to store values in the current row
                List<String> rowValues = new ArrayList<>();

                // Retrieve values for each column in the current row
                for (String columnName : columnNames) {
                    String columnValue = queryResult.getString(columnName);
                    rowValues.add(columnValue);
                }

                // Add the row to the list of rows
                rows.add(rowValues);
            }
            con.close();
            // System.out.print(rows);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not get connections");
            System.exit(1);
        }

        return rows;
    }

    /*
     * This function is for inserting into the database
     * 
     * @param tableName is the name of the table
     * 
     * @param values is a list of values to be inserted
     */
    public static void dbInsert(String tableName, List<String> values) {
        Database db = new Database();
        Connection con;

        try {
            con = db.getConnection();
            String command = "INSERT INTO " + tableName + " VALUES (";
            for (int i = 0; i < values.size(); i++) {
                command += "?,";
            }
            command = command.substring(0, command.length() - 1);
            command += ");";

            PreparedStatement statement = con.prepareStatement(command);
            for (int i = 0; i < values.size(); i++) {
                statement.setString(i + 1, values.get(i));
            }
            statement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not get connections");
            System.exit(1);
        }
    }

    // Make a run function for running sql commands
    /*
     * This is for testing purposes
     */
    public static void main(String[] args) {

        // This is an example
        List<List<String>> result = dbExecute("select username from admin");
        System.out.print(result);
    }
}
