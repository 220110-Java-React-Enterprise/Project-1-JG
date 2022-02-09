package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exceptions.ConnectionException;

public class ConnectionManager {
    private static Connection connection;


    /**
     * Empty private constructor.
     */
    private ConnectionManager() {}


    /**
     * Get the connection manager if it exists.
     * @return the Connection object
     * @throws ConnectionException when the 
     */
    public static Connection getConnection() throws ConnectionException {
        if (connection == null) {
            throw new ConnectionException("You must connect to the database first!");
        }

        return connection;
    }


    /**
     * Database connection logic.
     * @param connectionString string for connecting to database
     * @return connection object created
     * @throws SQLException when the database fails to connect
     */
    public static void connect(String connectionString) throws SQLException {
        try {
            // make sure the driver is loaded or something
            Class.forName("org.mariadb.jdbc.Driver");
            
            // make connection given connectionString
            connection = DriverManager.getConnection(connectionString);

        } catch (SQLException e) {
            throw new SQLException("Database connection unsuccessful! Either the database does not exist, or the connection failed.");
        } catch (ClassNotFoundException e) {
            FileLogger.getFileLogger().log(e);
        }
    }
}
