package jdbcTests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listOfMapsExample {

    String dbURL ="jdbc:oracle:thin:@3.238.220.2:1521:XE";
    String dbUsername="hr";
    String dbPassword="hr";

    @Test
    public void test1(){
        Map<String,Object> row1=new HashMap<>();
        row1.put("first_name","Steven");
        row1.put("last_name","King");
        row1.put("salary",2400);
        row1.put("job_id","AD_PRES" );

        System.out.println(row1.toString());

        Map<String,Object> row2=new HashMap<>();
        row2.put("first_name","Neena");
        row2.put("last_name","Kochar");
        row2.put("salary",17000);
        row2.put("job_id","AD_VP" );
        System.out.println(row2.toString());

        //creating list for keeping all the rows maps
        List<Map<String,Object>> queryData = new ArrayList<>();

        //adding rows one by one to my list
        queryData.add(row1);
        queryData.add(row2);

        //get the steven last name directly from the list.
        System.out.println(queryData.get(0).get("last_name"));

    }

    @Test
    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT first_name,last_name,salary,job_id from employees where rownum <6");

        //move to first row
        resultSet.next();
        //in order to get column names we need resultseetmetadata
        ResultSetMetaData rsmd= resultSet.getMetaData();


        Map<String,Object> row1=new HashMap<>();
        row1.put(rsmd.getColumnName(1),resultSet.getString(1));
        row1.put(rsmd.getColumnName(2),resultSet.getString(2));
        row1.put(rsmd.getColumnName(3),resultSet.getString(3));
        row1.put(rsmd.getColumnName(4),resultSet.getString(4) );

        System.out.println(row1.toString());

        //to move next row
        resultSet.next();

        Map<String,Object> row2=new HashMap<>();
        row2.put(rsmd.getColumnName(1),resultSet.getString(1));
        row2.put(rsmd.getColumnName(2),resultSet.getString(2));
        row2.put(rsmd.getColumnName(3),resultSet.getString(3));
        row2.put(rsmd.getColumnName(4),resultSet.getString(4) );
        System.out.println(row2.toString());

        //creating list for keeping all the rows maps
        List<Map<String,Object>> queryData = new ArrayList<>();

        //adding rows one by one to my list
        queryData.add(row1);
        queryData.add(row2);





        //close connections
        resultSet.close();
        statement.close();
        connection.close();

    }
}
