package jdbctests;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args)  throws SQLException {


        String dbUrl = "jdbc:oracle:thin:@18.209.157.245:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM  regions");

        //next() --> move pointer to first row

        resultSet.next();

        //getting information with column name
        System.out.println(resultSet.getString("region_name"));

        //giving information with column index(starts 1)
        System.out.println(resultSet.getString(2));

        // 1- europe
        // 2- Americas

        System.out.println(resultSet.getInt(1)+ " - " + resultSet.getString(2));

        //move to second row
        resultSet.next();
        System.out.println(resultSet.getInt(1)+ " - " + resultSet.getString(2));

        // move to third row
        resultSet.next();
        System.out.println(resultSet.getInt(1)+ " - " + resultSet.getString(2));


        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+ " - " + resultSet.getString(2));
        }


        //close connection
        resultSet.close();
        statement.close();
        connection.close();

        //18.209.157.245
    }
}
