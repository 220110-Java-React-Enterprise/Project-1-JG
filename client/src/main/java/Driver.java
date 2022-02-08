import pojos.Game;
import utils.DatabaseConnector;

public class Driver {
    public static void main(String[] args) {
        // initialize the ORM
        ObjectReflectionManager orm = new ObjectReflectionManager();

        // make a Game for testing
        Game darkSouls2ScholarOfTheFirstSin = new Game("Dark Souls 2", true, 15.99, "PS3", "Awesome", "From Software", "Bandai Namco", 2014);

        // set the game to be reflected upon by ORM
        orm.setReflectionObject(darkSouls2ScholarOfTheFirstSin);

        orm.objectSorter();//this calls whatever the approiate code is to put in database




        // trying to connect to database
        orm.startConnection(DatabaseConnector.getConnectionString());
    }
}
