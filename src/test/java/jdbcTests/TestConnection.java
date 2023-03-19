package jdbcTests;

import java.sql.*;

public class TestConnection {

    public static void main(String[] args) throws SQLException {
        String dbURL ="jdbc:oracle:thin:@3.238.220.2:1521:XE";
        String dbUsername="hr";
        String dbPassword="hr";

        Connection connection = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from regions");

        //next() ----> move pointer to first row
        resultSet.next();

        // --> getting information with column name
        System.out.println(resultSet.getString("region_name"));

        //getting information with column index(starts1)
        System.out.println(resultSet.getString(2));

        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));
        }

        //close connections
        resultSet.close();
        statement.close();
        connection.close();



    }
}
