import utils.ConnectionManager;

import java.lang.reflect.Field;
import java.sql.SQLException;

public class ObjectReflectionManager {
    private Object obj;//added so we can pass our objects to this

    public ObjectReflectionManager(Object obj) {//added so we can construct this with our object inside
        this.obj = obj;
    }

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
        }
*/
    }


/*
    public void jasonTestingCrap(){//there's a bunch of useful reflection examples in here
                                    //be sure to install mvn again so this will work in your client
        System.out.println(this.obj.getClass());// this outputs "class pojos.Game"
        //we can use .getClass to find out what kind of object we want
        try {
            System.out.println(obj.getClass().getDeclaredField("genre"));//this prints out "private java.lang.String pojos.Game.genre" doesn't matter if it's empty or not
            Field things = obj.getClass().getDeclaredField("genre");//replace the "genre" with whatever field we want to get the value of
            things.setAccessible(true);
            System.out.println(things.get(obj));//this prints out the value assigned to whatever field we just got, in this case our game's genre "awesome"

        } catch (NoSuchFieldException | IllegalAccessException e) {//intellij made me put the try catch here
            e.printStackTrace();
        }
    }
*/

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
