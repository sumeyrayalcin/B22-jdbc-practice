package jdbctests;

import org.junit.jupiter.api.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class DButilPractice {

    /*
    DBUtiles Methods:
- DBUtiles.createConnection() → create connection to db that you put
information inside the method.
- DBUtiles.destroy → closes the connection
- DBUtiles.getQueryRasultsMap(query) → returns list of maps, useful
when you are getting multiple rows of result
- DBUtiles.getRowMap(query) → returns maps of String of objects, useful
when we have only one result
     */

    @Test
    public void test1(){

        //create connection
        DBUtils.createConnection();

        String query = "SELECT first_name, last_name, salary, job_id FROM employees where rownum<6";

        List<Map<String, Object>> queryData = DBUtils.getQueryResultMap(query);
        //option+enter shortcut choose first after writing DBUtils.getQueryResultMap(query);

        //to print use foreach loop
        //print the result
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }

        //close the connection
        DBUtils.destroy();

    }



    @Test    //if you are dealing with only one row.(you dont need list, you need just map)
    public void test2(){
        //create connection
        DBUtils.createConnection();

        String query = "SELECT first_name, last_name, salary, job_id FROM employees where rownum<2";

        Map<String, Object> rowMap = DBUtils.getRowMap(query);

        System.out.println(rowMap.toString());

        //close the connection
        DBUtils.destroy();
    }



}