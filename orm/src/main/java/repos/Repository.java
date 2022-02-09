package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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
     * @throws SQLException when the
     */
    public void startConnection(String connectionString) throws SQLException {
        // try to connect to the database given a connectionString
        ConnectionManager.connect(connectionString);
    }


    /**
     * Get the connection through the ORM's ConnectionManager.
     * @return if successful, returns an initialized Connection object;
     *         otherwise, returns a null Connection object
     */
    public Connection getConnection() throws ConnectionException {
        return ConnectionManager.getConnection();
    }


    //TODO reflective CREATE statement
    //TODO this should call buildCreateTableStatement() if the table does not already exist in the database
    //!     notably we may just not do dynamic tables for timesake
    public void create(Object obj) throws SQLException, ConnectionException, MalformedTableException  {
        // start the prepared statement
        PreparedStatement pstmt = ConnectionManager.getConnection().
            prepareStatement(SQLScriptor.buildInsertStatement(obj), Statement.RETURN_GENERATED_KEYS);
    }


    //TODO reflective READ statement
    public List<Object> read(Object obj) throws SQLException, ConnectionException, MalformedTableException  {
        // start the prepared statement
        PreparedStatement pstmt = ConnectionManager.getConnection().
            prepareStatement(SQLScriptor.buildSelectStatement(obj), Statement.RETURN_GENERATED_KEYS);
        
        return null;
    }


    //TODO reflective UPDATE statement
    public void update(Object obj) throws SQLException, ConnectionException, MalformedTableException  {
        // start the prepared statement
        PreparedStatement pstmt = ConnectionManager.getConnection().
            prepareStatement(SQLScriptor.buildDeleteStatement(obj), Statement.RETURN_GENERATED_KEYS);
    }


    //TODO reflective DELETE statement
    public void delete(Object obj) throws SQLException, ConnectionException, MalformedTableException {
        // start the prepared statement
        PreparedStatement pstmt = ConnectionManager.getConnection().
            prepareStatement(SQLScriptor.buildDeleteStatement(obj), Statement.RETURN_GENERATED_KEYS);
    }
}
