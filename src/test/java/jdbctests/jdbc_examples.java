package jdbctests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {

    String dbUrl="jdbc:oracle:thin:@3.237.204.198:1521:XE";
    //change IP address: "jdbc:oracle:thin:@YOURIPCOMESHERE:1521:XE"
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement =connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");


        //move to first row
//        resultSet.next();

//        System.out.println(resultSet.getString(2));


        //display departments table in 10 - Administration - 200 - 1700 format

        //code for iterating for each row
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2)+" - "+
                    resultSet.getString(3)+" - "+resultSet.getInt(4));
        }

        System.out.println("------------------");

        resultSet = statement.executeQuery("SELECT * FROM regions");

        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));
        }

        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }

    @DisplayName("ResultSet Methods")
    @Test
    public void test2() throws SQLException {

        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement =connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");

        //ResultSet.TYPE_SCROLL_INSENSITIVE : Allow us to navigate up and down in query result. (Before this setup we can navigate only by one by)
        //ResultSet.CONCUR_READ_ONLY : read only, dont update the results


        //how to find how many rows we have for the query?

        //move to last row
        resultSet.last();

        //get the row count
        int rowCount = resultSet.getRow();
        System.out.println(rowCount);

        //en sonuncuda olduğumuz için başa dönmemiz gerek yoksa while loop false olur ve sonuç vermez
        //to move before first row after we use .last method
        resultSet.beforeFirst();

        //print all second column information
        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }






        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }


    @Test
    public void test3() throws SQLException {

        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement =connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");


        /*
        MetaData
        //data about data
        1-DatabaseMetaData  (not testing purposes. information about database, data, table to show)
        2-ResultSetMetaData
         */

        //1-DatabaseMetaData
        //get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("dbMetadata.getUserName() = " + dbMetadata.getUserName());
        System.out.println("dbMetadata.getDatabaseProductName() = " + dbMetadata.getDatabaseProductName());
        System.out.println("dbMetadata.getDatabaseProductVersion() = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("dbMetadata.getDriverName() = " + dbMetadata.getDriverName());
        System.out.println("dbMetadata.getDriverVersion() = " + dbMetadata.getDriverVersion());



        //2-ResultSetMetaData
        //get the resultsetmetadata  (rsmd)
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //how many columns we have?  (rows: horizontally - columns: vertically)
        int colCount = rsMetadata.getColumnCount();
        System.out.println(colCount);

        //getting column names
        System.out.println(rsMetadata.getColumnName(1));
        System.out.println(rsMetadata.getColumnName(2));

        System.out.println("----------");

        //rsMetadata.getColumnName(i) --> gets column name
        //rsMetadata.getColumnCount() -->total number of columns

        //print all the column names dynamically
        for (int i = 1; i <= colCount; i++) {
            System.out.println(rsMetadata.getColumnName(i));

        }


        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }


}