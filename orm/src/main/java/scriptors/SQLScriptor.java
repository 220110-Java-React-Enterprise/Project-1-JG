package scriptors;

public abstract class SQLScriptor {
    public static String buildSelectIdQueryStatement() {
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
}