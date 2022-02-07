//import org.junit.Test;


//import org.junit.jupiter.api.Test;
import pojos.Game;
import utils.DatabaseConnector;

public class Driver {
    public static void main(String[] args) {
        Game darkSouls2ScholarOfTheFirstSin = new Game("Dark Souls 2", true, 15.99, "PS3", "Awesome", "From Software", "Bandai Namco", 2014);
        ObjectReflectionManager orm = new ObjectReflectionManager(darkSouls2ScholarOfTheFirstSin);
        orm.objectSorter();//this calls whatever the approiate code is to put in database
        //end testing code




        // trying to connect to database
        orm.startConnection(DatabaseConnector.getConnectionString());
    }

    //@Test
    public static void jasonTestingCrap(){
        Game darkSouls2ScholarOfTheFirstSin = new Game("Dark Souls 2", true, 15.99, "PS3", "Awesome", "From Software", "Bandai Namco", 2014);
        ObjectReflectionManager orm = new ObjectReflectionManager(darkSouls2ScholarOfTheFirstSin);
        orm.objectSorter();//this calls whatever the approiate code is to put in database
    }
}
