package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection connection;


    /**
     * Empty private constructor.
     */
    private ConnectionManager() {}


    /**
     * Get the connection manager if it exists.
     * @return the Connection object
     */
    public static Connection getConnection() throws IllegalStateException {
        if (connection == null) {
            throw new IllegalStateException("You must connect to the database first!");
        }

        return connection;
    }


    /**
     * Database connection logic.
     * @return connection object created
     */
    public static void connect(String connectionString) {
        try {
            // make connection given connectionString
            connection = DriverManager.getConnection(connectionString);

        } catch (SQLException  e) {
            System.out.println("Database connection unsuccessful!" + 
            "Either the database does not exist, or the connection failed.");
        }
    }
}
