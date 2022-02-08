package scriptors;

import java.lang.reflect.Field;

import annotations.Column;
import annotations.Table;
import exceptions.MalformedTableException;

public abstract class SQLScriptor {
    /**
     * Creates the SQL statement to create a table based on the @Table annotation, if the annotation is present.
     *   The table will not be created if it already exists.
     * @param obj object to reflect upon
     * @return SQL statement for creating a table
     * @throws MalformedTableException if there are missing annotations for the Table or Column(s)
     */
    public static String buildCreateTableStatement(Object obj) throws MalformedTableException {
        // check if the @Table annotation is NOT present
        if (!obj.getClass().isAnnotationPresent(Table.class)) {
            throw new MalformedTableException("Missing @Table annotation.");
        }

        // start the statement
        String result = "CREATE TABLE IF NOT EXISTS ";

        // retrieve the name of the table, specified in the @Table annotation on the object
        String tableName = obj.getClass().getAnnotation(Table.class).tableName();

        // add the table name to the result string
        result += tableName + " (";

        // retrieve the fields available to the class
        Field[] fields = obj.getClass().getDeclaredFields();

        // iterate through the fields
        for (int i = 0 ; i < fields.length ; i++) {
            //TODO need to check for @Column and add its stuff if appropriate (VARCHAR, length, etc.)

            //checks for @Column now, does not verify field type
            if (!fields[i].isAnnotationPresent(Column.class)){
                throw new MalformedTableException("Missing @Column annotation.");
            }



            // fields set to be accessible temporarily
            fields[i].setAccessible(true);

            // add each field to the result string, comma-separated
            if (i < fields.length - 1) {
                result += nameCleaner(fields[i].toString()) + ", ";
            }
            // finish with a )
            else {
                result += nameCleaner(fields[i].toString()) + ")";
            }

            // set fields back to inaccessible
            fields[i].setAccessible(false);
        }

        // return string ready for PreparedStatement
        return result;
    }


    /**
     * Creates the SQL statement to insert an object into the table.
     * @param obj object to reflect upon
     * @return SQL statement for inserting an object
     */
    public static String buildInsertStatement(Object obj) throws MalformedTableException {

        if (!obj.getClass().isAnnotationPresent(Table.class)) {
            throw new MalformedTableException("Missing @Table annotation.");
        }
        String result = "INSERT INTO ";
        String tableName = obj.getClass().getAnnotation(Table.class).tableName();
        result += tableName + " (";
        Field[] fields = obj.getClass().getDeclaredFields();

        for (int i = 0 ; i < fields.length ; i++) {
            if (!fields[i].isAnnotationPresent(Column.class)){
                throw new MalformedTableException("Missing @Column annotation.");
            }
            fields[i].setAccessible(true);
            if (i < fields.length - 1) {
                result += nameCleaner(fields[i].toString()) + ", ";
            }
            else {
                result += nameCleaner(fields[i].toString()) + ")";
            }
            fields[i].setAccessible(false);
        }
        result += " VALUES (";
        for (int i=0;i<fields.length;i++)
            if (i < fields.length - 1)
                result +="?,";
            else
                result += "?)";

        System.out.println(result);

        return result;
    }//end build insert


    /**
     * Creates the SQL statement to delete an object from the table.
     * @param obj object to reflect upon
     * @return SQL statement for deleting an object
     */
    public static String buildDeleteStatement(Object obj) throws MalformedTableException {
        if (!obj.getClass().isAnnotationPresent(Table.class)) {
            throw new MalformedTableException("Missing @Table annotation.");
        }
        String result = "DELETE FROM ";
        String tableName = obj.getClass().getAnnotation(Table.class).tableName();
        result += tableName + " WHERE id=";
        Field[] fields = obj.getClass().getDeclaredFields();
        result += fields[0].toString();

        System.out.println(result);

        return result;
    }


    /**
     * Helper function that retrieves the last name from a reflection'd string.
     * @param reflectedString reflected string from the reflection calls
     * @return name of the reflected thing that we care about
     */
    public static String nameCleaner(String reflectedString){
        // split on periods
        String arr[] = reflectedString.split("[.]");

        // only care about the last thing
        return arr[arr.length-1];
    }
}