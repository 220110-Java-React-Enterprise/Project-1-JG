import scriptors.SQLScriptor;
import utils.ConnectionManager;

import java.lang.reflect.Field;
import java.sql.SQLException;

import exceptions.MalformedTableException;

public class ObjectReflectionManager {
    // object to use for processing
    private Object obj;

    /**
     * Empty default constructor.
     */
    public ObjectReflectionManager() {}


    public void objectSorter(){
        String objectBeingSentIn = this.obj.getClass().getCanonicalName();//gets what object class this is

        System.out.println("Name of object: " + objectBeingSentIn);

        Field[] idk = obj.getClass().getDeclaredFields();
        for(int i=0;i<idk.length;i++){
            idk[i].setAccessible(true);
            System.out.println(idk[i]);
        }

<<<<<<< HEAD










/*
        switch(objectBeingSentIn){
            case "class pojos.Accessory":
                System.out.println("You sent me an accessory");//for testing remove later
                    //code to put Accessory in
                break;
            case "class pojos.Console":
                System.out.println("You sent me a console");//for testing remove later
                    //code to put Accessory in
                break;
            case "class pojos.Controller":
                System.out.println("You sent me a controller");//for testing remove later
                    //code to put Controller in
                break;
            case "class pojos.Game":
                System.out.println("You sent me a game");//for testing remove later
                    //code to put Game in
                break;
            default:
                System.out.println("I don't know what you sent me");//for testing remove later
                break;
=======
        // try to create a new table
        try {
            System.out.println(SQLScriptor.buildCreateTableStatement(this.obj));
        } catch (MalformedTableException e) {
            //TODO do something with file logger
            System.out.println(e.getMessage());
>>>>>>> 8abef884c2b9509de773dcbcb8cd56d997fb96e9
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
            System.out.println(e.getMessage());
        }
    }
}
