package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class dynamic_list {
    String dbUrl="jdbc:oracle:thin:@3.237.204.198:1521:XE";
    //change IP address: "jdbc:oracle:thin:@YOURIPCOMESHERE:1521:XE"
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test2() throws SQLException {


        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement =connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT first_name, last_name, salary, job_id FROM employees where rownum<6");

        //in order to get column names we need resultsetmetadata
        //rsmd helps to get the column count and get the column name
        //resultset helps to get real data (column value)
        ResultSetMetaData rsmd = resultSet.getMetaData();

        //list of maps to keep all information
        List<Map<String,Object>> queryData = new ArrayList<>();

        //number of columns
        int colCount = rsmd.getColumnCount();

        //loop through each row
        while(resultSet.next()){   //her row için döner horizontal
            Map<String,Object> row= new LinkedHashMap<>();

            //some code to fill the dynamically
            //her döngüde map e eklemek için key ve value oluşur. kaç column varsa mesela 4 tane 4 e kadar ekleyecek. 1 den başlatmayı unutma i yi.
            for (int i = 1; i <= colCount; i++) {   //her column için döner vertical
                row.put(rsmd.getColumnName(i),resultSet.getObject(i));

            }




            //add ready map row to the list
            //empty liste oluşan mapler ekleniyor(her for loopta bir map oluşuyor(her 1 row için 1 map), her while loopta da her satır için yeni for başlıyor)
            queryData.add(row);

        }

        for (Map<String, Object> row : queryData) {

            System.out.println(row.toString());
        }







        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }
}

/*
4 tools that we have to create dynamic code
1- Column value -> resultSet.getObject(i)
2- Column name -> rsmd.getColumnName(i)
    i -> index starts from 1
3- NumberOfColumns -> rsmd.getColumnCount();
4- NumberOfRows -> while(resultSet.next()){}
 */