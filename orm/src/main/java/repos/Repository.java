package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.ConnectionException;
import exceptions.MalformedTableException;
import scriptors.SQLScriptor;
import utils.ConnectionManager;
import utils.FileLogger;

/**
 * This is what the client uses to to do the CRUD operations.
 *   Utilizes the SQLScriptor to build SQL strings for PreparedStatements to be executed within this class.
 */
public class Repository {
    /**
     * Start the connection with the database when given a connectionString.
     * @param connectionString string for connecting to database
     */
    public void startConnection(String connectionString) {
        // see if the database is already connected
        try {
            ConnectionManager.connect(connectionString);
        } catch (SQLException e) {
            FileLogger.getFileLogger().log(e);
        }
    }


    /**
     * Get the connection through the ORM's ConnectionManager.
     * @return if successful, returns an initialized Connection object;
     *         otherwise, returns a null Connection object
     */
    public Connection getConnection() {
        Connection conn = null;

        try {
            conn = ConnectionManager.getConnection();
        } catch (ConnectionException e) {
            FileLogger.getFileLogger().log(e);
        }

        return conn;
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
    public void read(Object obj) throws SQLException, ConnectionException, MalformedTableException  {
        // start the prepared statement
        PreparedStatement pstmt = ConnectionManager.getConnection().
            prepareStatement(SQLScriptor.buildSelectStatement(obj), Statement.RETURN_GENERATED_KEYS);
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
