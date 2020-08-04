package com.autodeshcrm.genericutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

/**
 * 
 * @author Shravya 
 *
 */
public class DBUtils {
	private static Connection con;
	private static Statement state;
	private static ResultSet result;
	
	//This method is used to establish the sql connection
	public static Connection getDBConnection() throws Throwable
	{
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","password123");	
		return con;
	}
	
	//This methos closes the sql connection
	public static void closeConnection(Connection con) throws SQLException
	{
		con.close();
	}
	
	//This method is used for select query 
	public static ResultSet selecQueryExecution(String query) throws SQLException
	{
		state=con.createStatement();
		return state.executeQuery(query);
	}
	
	//This method is used for update the query
	public static int updateQuery(String query) throws SQLException
	{
		state=con.createStatement();
		return state.executeUpdate(query);
	}

}
