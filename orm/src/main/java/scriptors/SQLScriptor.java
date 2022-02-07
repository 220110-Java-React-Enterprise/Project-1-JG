package scriptors;

import java.lang.reflect.Field;
import java.util.Locale;

public abstract class SQLScriptor {
    public static String buildSelectIdQueryStatement(Object obj) {
        // start from having some plain old POJO just for development
        // write the SQL scripts necessary to CRUD, for instance here's a basic Read script:
        //String sql = "SELECT * FROM table WHERE id = ?";
        // now ask how we could have our software build that same script without knowing anything about that POJO ahead of time
        String firstPart = "SELECT * FROM ";
        String tablename = "";                  // reflect on the object to get this
        String nextPart = " WHERE ";
        String filterColumn = "";               // reflect upon the object for this data as well
        String parameterList = " = ?";
        // now we have our script and just need to build it:
        String script = firstPart + tablename + nextPart + filterColumn + parameterList;
        /**
         * now we can create the preparedStatement and parameterize it.
         * 
         * In fact we don't even need to parameterize it here,
         * I prefer to hand the pstmt back in the return value of the script method, and go from there -Kyle
        */
        return script;
    }

    public static String createQueryStatement(Object obj){//can also pass array of strings or something to these methods
        String result = "CREATE TABLE ";
        String tableName = obj.getClass().getCanonicalName(); //this still includes the pojos. part of the name
        result += nameCleaner(tableName).toLowerCase() + "s (";
        Field[] fields = obj.getClass().getDeclaredFields();
        for(int i=0;i<fields.length;i++){
            fields[i].setAccessible(true);
            //run name cleaning method here

            if(i== fields.length-1)
                result += nameCleaner(fields[i].toString())+")";
                else
            result += nameCleaner(fields[i].toString()) + ", ";
        }

        System.out.println(result);//this outputs CREATE TABLE pojos.Game ( private java.lang.String pojos.Game.genre, private java.lang.String pojos.Game.developer, private java.lang.String pojos.Game.publisher, private java.lang.Integer pojos.Game.year,; need to fix the last comma
                                    //basically outputs the data we want but with all the junk attached. Make a method that cleans these up
                                    //does not utilize PreparedStatement() think of a way to use that
        return result;
    }

    public static String insertQueryStatement(Object obj){
        String result = "";
        //this will put our object into the correct table
        return result;
    }
    public static String deleteQueryStatement(Object obj){
        String result = "";
        //this deletes an object from a table
        return result;
    }
    public static String nameCleaner(String result){
        String arr[] = result.split("[.]");
        return arr[arr.length-1];
    }
}