package integracion.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC {
	//Source: https://docs.oracle.com/javase/tutorial/jdbc/overview/index.html
	public void connectToAndQueryDatabase(String username, String password) throws SQLException {
		
		//Connects to a DB driver and logs into the DB
	    Connection con = DriverManager.getConnection(
	                         "jdbc:myDriver:myDatabase",
	                         username,
	                         password);

	    //Instantiates a Statement object that carries your SQL language query to the DB
	    Statement stmt = con.createStatement();
	    //Instantiates a ResultSet object that retrieves the results of your query
	    ResultSet rs = stmt.executeQuery("SELECT a, b, c FROM Table1");

	    //Simple while loop that retrieves and displays the results. 
	    while (rs.next()) {
	        System.out.println(rs.getInt("a"));
	        System.out.println(rs.getString("b"));
	        System.out.println(rs.getFloat("c"));
	    }
	}
}