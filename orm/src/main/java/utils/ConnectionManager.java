package utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static Connection connection;


    /**
     * Empty private constructor.
     */
    private ConnectionManager() {}


    /**
     * Get the connection manager if it exists, otherwise make a new one.
     * @return the singleton instance of ConnectionManager object
     */
    public static Connection getConnection() {
        if(connection == null) {
            connection = connect();
        }

        return connection;
    }


    /**
     * Database connection logic.
     * @return connection object created
     */
    private static Connection connect() {
        /**
         * jdbc:mariadb://<hostname>:<port>/<dbName>?user=<username>&password=<password>
         *   replace <> with respective information
         */
        try {
            // make a Properties object
            Properties props = new Properties();

            // open FileReader & read in properties
            FileReader fr = new FileReader("src/main/resources/jdbc.properties");
            props.load(fr);

            // create the connectionString
            String connectionString = "jdbc:mariadb://" +
                props.getProperty("hostname") + ":" +
                props.getProperty("port") + "/" +
                props.getProperty("dbname") + "?user=" +
                props.getProperty("username") + "&password=" +
                props.getProperty("password");
            
            // make connection given connectionString
            connection = DriverManager.getConnection(connectionString);

        } catch (IOException | SQLException  e) {
            e.printStackTrace();
        }

        return connection;
    }
}
