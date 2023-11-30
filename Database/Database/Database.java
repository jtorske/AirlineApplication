package Database.Database;

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

    /**
     * To get the column names of a query result
     * 
     * @param queryResult is the result of a query
     */
    private List<String> getColumnNames(ResultSet queryResult) {
        List<String> columnNames = new ArrayList<>();

        try {
            // Get column names
            int columnCount = queryResult.getMetaData().getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(queryResult.getMetaData().getColumnLabel(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not get column names");
            System.exit(1);
        }
        System.out.println(columnNames);
        return columnNames;
    }

    public static List<List<String>> dbExecute(String command) {
        Database db = new Database();
        Connection con;
        List<List<String>> rows = new ArrayList<>();

        try {
            con = db.getConnection();
            Statement statement = con.createStatement();
            ResultSet queryResult = statement.executeQuery(command);

            List<String> columnNames = db.getColumnNames(queryResult);

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

    /**
     * This function is for inserting into the database
     * 
     * @param tableName is the name of the table
     * 
     * @param values    is a list of values to be inserted
     * @return the automaticly updated primary key id of the inserted row
     */

    public static String dbInsert(String tableName, List<String> values) {
        Database db = new Database();
        Connection con;

        String idGenerated = "";
        try {
            con = db.getConnection();
            String command = "INSERT INTO " + tableName + " VALUES (";
            for (int i = 0; i < values.size(); i++) {
                command += "?,";
            }
            command = command.substring(0, command.length() - 1);
            command += ");";

            PreparedStatement statement = con.prepareStatement(command, PreparedStatement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < values.size(); i++) {
                statement.setString(i + 1, values.get(i));
            }

            System.out.println(statement.toString());
            statement.executeUpdate();

            // Checking for generated keys
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                idGenerated = rs.getString(1);
            } else {
                System.out.println("No rows in the result set");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not get connections");
            System.exit(1);
        }

        return idGenerated;
    }
    /**
     * This function is for removing items from the database
     * @param tableName is the name of the table
     * @param columnName is the name of the column
     * @param columnValue is the value of the column
     * 
     * Its basically in the form of 
     *  DELETE FROM tableName WHERE columnName = columnValue
     */
    public static void dbDelete(String tableName, String columnName, String columnValue) {
        Database db = new Database();
        Connection con;

        try {
            con = db.getConnection();

            // Construct the SQL DELETE statement
            String command = "DELETE FROM " + tableName + " WHERE " + columnName + " = ?";
            PreparedStatement statement = con.prepareStatement(command);

            // Set the parameter for the WHERE clause
            statement.setString(1, columnValue);

            System.out.println(statement.toString());
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println(rowsDeleted + " row(s) deleted.");
            } else {
                System.out.println("No rows deleted. No matching records found.");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not get connections");
            System.exit(1);
        }
    }
    /**
     * 
     * @param updateQuery string for that update transaction 
     * 
     * Example: 
     *  String query = "UPDATE table set col = 5 WHERE id = 4"
     */
    public static void dbUpdate(String updateQuery) {
        Database db = new Database();
        Connection con;

        try {
            con = db.getConnection();

            // Construct the SQL UPDATE statement
            PreparedStatement statement = con.prepareStatement(updateQuery);

            System.out.println(statement.toString());
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println(rowsUpdated + " row(s) updated.");
            } else {
                System.out.println("No rows updated. No matching records found.");
            }

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
        List<List<String>> result = dbExecute("select * from CrewMember");
        System.out.print(result);
    }
}
