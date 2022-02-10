package repos;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import annotations.Column;
import enums.SQLType;
import exceptions.ConnectionException;
import exceptions.MalformedTableException;
import scriptors.SQLScriptor;
import utils.ConnectionManager;

/**
 * This is what the client uses to to do the CRUD operations.
 *   Utilizes the SQLScriptor to build SQL strings for PreparedStatements to be executed within this class.
 */
public class Repository {
    /**
     * Start the connection with the database when given a connectionString.
     * @param connectionString string for connecting to database
     * @throws SQLException if the database connection is unsuccessful
     */
    public void startConnection(String connectionString) throws SQLException {
        // try to connect to the database given a connectionString
        ConnectionManager.connect(connectionString);
    }


    /**
     * Get the connection through the ORM's ConnectionManager.
     * @return if successful, returns an initialized Connection object
     * @throws ConnectionException if the connection is not yet established
     */
    public Connection getConnection() throws ConnectionException {
        return ConnectionManager.getConnection();
    }


    /**
     * Add an object to the database.
     * @param obj object to add to database
     * @throws SQLException if the database connection is unsuccessful
     * @throws ConnectionException if the connection is not yet established
     * @throws MalformedTableException if the annotations were not done properly
     * @throws IllegalAccessException accessing something that shouldn't be accessed
     */
    public void create(Object obj) throws SQLException, ConnectionException, MalformedTableException, IllegalAccessException {
        // start the prepared statement
        PreparedStatement pstmt = ConnectionManager.getConnection().
            prepareStatement(SQLScriptor.buildInsertStatement(obj), Statement.RETURN_GENERATED_KEYS);
        
        // retrieve all fields and iterate through them
        Field[] fields = obj.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            // temporarily set fields as accessible
            fields[i].setAccessible(true);

            // do not need to check for present of Column as associated SQLScriptor method checks
            SQLType type = fields[i].getAnnotation(Column.class).type();

            // add to prepared statement based on type
            switch (type) {
                // VARCHAR -> String
                case VARCHAR:
                    pstmt.setString(i + 1, (String)fields[i].get(obj));
                    break;
                
                // INT -> Integer
                case INT:
                    pstmt.setInt(i + 1, (Integer)fields[i].get(obj));
                    break;
                
                // NUMERIC -> Double
                case NUMERIC:
                    pstmt.setDouble(i + 1, (Double)fields[i].get(obj));
                    break;
            
                // INT -> Integer
                case BOOL:
                    pstmt.setBoolean(i + 1, (Boolean)fields[i].get(obj));
                    break;
        
                // INT -> Integer
                case BIGINT:
                    pstmt.setLong(i + 1, (Long)fields[i].get(obj));
                    break;
            }

            // unset fields as accessible
            fields[i].setAccessible(false);
        }

        // execute the built prepared statement
        pstmt.execute();
    }


    /**
     * Reads all objects from the database.
     * @param obj type of object to read
     * @throws SQLException if the database connection is unsuccessful
     * @throws ConnectionException if the connection is not yet established
     * @throws MalformedTableException if the annotations were not done properly
     * @throws IllegalAccessException accessing something that shouldn't be accessed
     */
    public ArrayList<String> read(Object obj) throws SQLException, ConnectionException, MalformedTableException, IllegalAccessException {
        // start the prepared statement
        PreparedStatement pstmt = ConnectionManager.getConnection().
            prepareStatement(SQLScriptor.buildSelectStatement(obj), Statement.RETURN_GENERATED_KEYS);

        //gets the output of the SQL query
        ResultSet rs = pstmt.executeQuery();
        //puts them into a field array
        Field[] fields = rs.getClass().getDeclaredFields();

        //makes an arraylist that will read from the database
        ArrayList<String> rows = new ArrayList<>();
        while(rs.next()){
                for(int i=1;i<fields.length-24;i++){
                    rows.add(rs.getObject(i).toString());
                }
        }
        return rows;
    }//end read


    /**
     * Updates an object in the database.
     * @param obj what object to update
     * @throws SQLException if the database connection is unsuccessful
     * @throws ConnectionException if the connection is not yet established
     * @throws MalformedTableException if the annotations were not done properly
     * @throws IllegalAccessException accessing something that shouldn't be accessed
     */
    //TODO reflective UPDATE statement
    public void update(Object obj) throws SQLException, ConnectionException, MalformedTableException, IllegalAccessException {
        // start the prepared statement
        PreparedStatement pstmt = ConnectionManager.getConnection().
            prepareStatement(SQLScriptor.buildUpdateStatement(obj), Statement.RETURN_GENERATED_KEYS);
    }


    /**
     * Deletes an object from the database.
     * @param obj what object to delete
     * @throws SQLException if the database connection is unsuccessful
     * @throws ConnectionException if the connection is not yet established
     * @throws MalformedTableException if the annotations were not done properly
     * @throws IllegalAccessException accessing something that shouldn't be accessed
     */
    //TODO reflective DELETE statement
    public void delete(Object obj) throws SQLException, ConnectionException, MalformedTableException, IllegalAccessException {
        // start the prepared statement
        PreparedStatement pstmt = ConnectionManager.getConnection().
            prepareStatement(SQLScriptor.buildDeleteStatement(obj), Statement.RETURN_GENERATED_KEYS);
    }
}
