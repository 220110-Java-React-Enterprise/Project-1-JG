//import org.junit.Test;


import pojos.Game;

public class Driver {
    public static void main(String[] args) {
        Game darkSouls2ScholarOfTheFirstSin = new Game("Dark Souls 2", true, 15.99, "PS3", "Awesome", "From Software", "Bandai Namco", 2014);
        ObjectReflectionManager orm = new ObjectReflectionManager(darkSouls2ScholarOfTheFirstSin);
        orm.objectSorter();//this calls whatever the approiate code is to put in database

    }

    //@Test
    public static void jasonTestingCrap(){
        Game darkSouls2ScholarOfTheFirstSin = new Game("Dark Souls 2", true, 15.99, "PS3", "Awesome", "From Software", "Bandai Namco", 2014);
        ObjectReflectionManager orm = new ObjectReflectionManager(darkSouls2ScholarOfTheFirstSin);
        orm.objectSorter();//this calls whatever the approiate code is to put in database
    }
}
