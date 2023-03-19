package jdbcTests;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {

    String dbURL ="jdbc:oracle:thin:@3.238.220.2:1521:XE";
    String dbUsername="hr";
    String dbPassword="hr";

    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from departments");

        //next() ----> move pointer to first row
        resultSet.next();

        //System.out.println(resultSet.getString(2));
        //display departments table in 10 - administration - 200 -1700 format
        //code for iterating for each row
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2)+" - "+
                    resultSet.getInt(3)+" - "+ resultSet.getInt(4));

        }
        System.out.println("===========================================");

        resultSet= statement.executeQuery("SELECT * from regions");
        while (resultSet.next()){
  System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));

        }

        //close connections
        resultSet.close();
        statement.close();
        connection.close();


    }

    @Test
    public void test2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * from employees");

        //MOVE TO LAST ROW
        resultSet.last();

        //how to find how many rows we have for the query


        //get the row count
        int rowCount=resultSet.getRow();
        System.out.println(rowCount);

        //to move before first row after we use last method
        resultSet.beforeFirst();
        //print all second column information
        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        //close connections
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void test3() throws SQLException {
        Connection connection = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * from employees");

        //get the database related data inside the dbMetedata object
        DatabaseMetaData dbMetadata= connection.getMetaData();

        System.out.println("dbMetadata.getUserName() = " + dbMetadata.getUserName());
        System.out.println("dbMetadata.getDatabaseProductName() = " + dbMetadata.getDatabaseProductName());
        System.out.println("dbMetadata.getDatabaseProductVersion() = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("dbMetadata.getDriverName() = " + dbMetadata.getDriverName());
        System.out.println("dbMetadata.getDriverVersion() = " + dbMetadata.getDriverVersion());

        //get the resultsetmetadata---=rsmd
        ResultSetMetaData rsMetadata= resultSet.getMetaData();

        //how many columns we have
        int colCount = rsMetadata.getColumnCount();
        System.out.println("colCount = " + colCount);
        System.out.println("rsMetadata.getColumnName(2) = " + rsMetadata.getColumnName(2));

        //print all the column names dynamically
        for (int i = 1; i <=colCount ; i++) {
            System.out.println(rsMetadata.getColumnName(i));
        }


        //close connections
        resultSet.close();
        statement.close();
        connection.close();
    }

}
