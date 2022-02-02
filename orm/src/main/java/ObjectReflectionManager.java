import utils.ConnectionManager;

public class ObjectReflectionManager {
    /**
     * Empty default constructor.
     */
    public ObjectReflectionManager() {}


    /**
     * Start the connection with the database when given a connectionString.
     */
    public void startConnection(String connectionString) {
        // see if the database is already connected
        try {
            ConnectionManager.getConnection();
        } catch (IllegalStateException e) {
            System.out.println("Connection already started!");
            return;
        }

        // start the connection
        ConnectionManager.connect(connectionString);
    }
}
