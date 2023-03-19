package jdbcTests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.*;


public class Dynamic_List {

    String dbURL ="jdbc:oracle:thin:@3.238.220.2:1521:XE";
    String dbUsername="hr";
    String dbPassword="hr";


@Test
        public void tes1() throws SQLException {
    Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT first_name,last_name,salary,job_id from employees where rownum <6");

    //move to first row
    resultSet.next();
    //in order to get column names we need resultseetmetadata
    ResultSetMetaData rsmd = resultSet.getMetaData();

    //list of maps to keep all information
    List<Map<String, Object>> queryData= new ArrayList<>();

    //numbber of columns
    int colCount= rsmd.getColumnCount();

    //loop through each row
    while (resultSet.next()){
        Map<String,Object> row=new LinkedHashMap<>();

        //some code to fill the dynamically

        for (int i = 1; i <=colCount ; i++) {
            row.put(rsmd.getColumnName(i),resultSet.getObject(i));

        }
        //add ready map row to the list
        queryData.add(row);

    }

    //print each row inside the list
    for (Map<String, Object> row : queryData) {
        System.out.println(row.toString());
    }




    //close connections
    resultSet.close();
    statement.close();
    connection.close();
}
}
