import scriptors.SQLScriptor;
import utils.ConnectionManager;

import java.lang.reflect.Field;
import java.sql.SQLException;

import exceptions.MalformedTableException;

public class ObjectReflectionManager {
    private Object obj;//added so we can pass our objects to this

    public ObjectReflectionManager(Object obj) {//added so we can construct this with our object inside
        this.obj = obj;
    }

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

        // try to create a new table
        try {
            System.out.println(SQLScriptor.buildCreateTableStatement(this.obj));
        } catch (MalformedTableException e) {
            //TODO do something with file logger
            System.out.println(e.getMessage());
        }
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
            System.out.println(e.getMessage());
        }
    }
}
