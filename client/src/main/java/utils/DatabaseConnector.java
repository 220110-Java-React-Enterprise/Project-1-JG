package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConnector {
    private static String connectionString;


    /**
     * Empty private constructor.
     */
    private DatabaseConnector() {}


    /**
     * Returns the connection String.
     * @return the connection string
     */
    //TODO different method as this can expose secure info if used incorrectly
    public static String getConnectionString() {
        if (connectionString == null) {
            generateConnectionString();
        }

        return connectionString;
    }


    /**
     * Generate the connection String based on properties.
     */
    private static void generateConnectionString() {
        /**
         * jdbc:mariadb://<hostname>:<port>/<dbName>?user=<username>&password=<password>
         *   replace <> with respective information
         */
        try {
            // properties file
            Properties props = new Properties();

            // retrive class loader from current thread
            ClassLoader loader = Thread.currentThread().getContextClassLoader();

            // input stream tied to file (from classpath)
            InputStream input = loader.getResourceAsStream("jdbc.properties");

            // load the properties
            props.load(input);

            // create the connectionString
            //TODO auto create database on orm side based on name here?
            connectionString = "jdbc:mariadb://" +
                props.getProperty("hostname") + ":" +
                props.getProperty("port") + "/" +
                props.getProperty("dbname") + "?user=" +
                props.getProperty("username") + "&password=" +
                props.getProperty("password");
            
        } catch (IOException e) {
            System.out.println("Unable to generate connection String!");
        }
    }
}
