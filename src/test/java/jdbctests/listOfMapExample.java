package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listOfMapExample {

    String dbUrl="jdbc:oracle:thin:@3.237.204.198:1521:XE";
    //change IP address: "jdbc:oracle:thin:@YOURIPCOMESHERE:1521:XE"
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

        //creating list for keeping all the rows maps
        List<Map<String,Object>> queryData = new ArrayList<>();

        Map<String,Object> row1 = new HashMap<>();

        row1.put("first_name","Steven");
        row1.put("last_name","King");
        row1.put("salary",24000);
        row1.put("job_id","AD_PRES");

        System.out.println(row1.toString());

        Map<String,Object> row2 = new HashMap<>();

        row2.put("first_name","Neena");
        row2.put("last_name","Kochhar");
        row2.put("salary",17000);
        row2.put("job_id","AD_VP");

        System.out.println(row2.toString());

        //adding rows one by one to my list
        queryData.add(row1);
        queryData.add(row2);


        //get the Steven lastname directly from the list
        System.out.println(queryData.get(0).get("last_name"));  //first row index 0

        //neena's salary?
        System.out.println(queryData.get(1).get("salary"));  //second row index 1

    }

}
