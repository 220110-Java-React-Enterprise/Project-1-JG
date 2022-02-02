package utils;

import java.io.FileReader;
import java.io.IOException;
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
            // make a Properties object
            Properties props = new Properties();
            
            // open FileReader & read in properties
            FileReader fr = new FileReader("src/main/resources/jdbc.properties");
            props.load(fr);

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
