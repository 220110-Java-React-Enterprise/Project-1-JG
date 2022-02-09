import scriptors.SQLScriptor;
import utils.ConnectionManager;
import utils.FileLogger;

import java.lang.reflect.Field;
import java.sql.SQLException;

import exceptions.MalformedTableException;

public class ObjectReflectionManager {
    // object to use for processing
    private Object obj;

    /**
     * Empty default constructor.
     */
    public ObjectReflectionManager() {}//should the logic code go here?


    public void objectSorter(){
        String objectBeingSentIn = this.obj.getClass().getCanonicalName();//gets what object class this is

        System.out.println("Name of object: " + objectBeingSentIn);

        Field[] idk = obj.getClass().getDeclaredFields();
        for(int i=0;i<idk.length;i++){
            idk[i].setAccessible(true);
            System.out.println(idk[i]);
        }

        try {
            System.out.println(SQLScriptor.buildCreateTableStatement(this.obj));
        } catch (MalformedTableException e) {
            FileLogger.getFileLogger().log(e);
        }
    }

    /**
     * Sets the object that we currently want to reflect on.
     * @param obj object to reflect on
     */
    public void setReflectionObject(Object obj) {
        this.obj = obj;

    }


    /**
     * Start the connection with the database when given a connectionString.
     * @param connectionString string for connecting to database
     */
    public void startConnection(String connectionString) {
        // see if the database is already connected
        try {
            ConnectionManager.connect(connectionString);
        } catch (SQLException e) {
            FileLogger.getFileLogger().log(e);
        }
    }
}
