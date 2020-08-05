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
	private  Connection con;
	private Statement state;
	private  ResultSet result;
	public Driver driverRef;
	
	//This method is used to establish the sql connection
	public  Connection getDBConnection() throws Throwable
	{
		driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","password123");	
		return con;
	}
	
	//This methods closes the sql connection
	public  void closeConnection() throws SQLException
	{
		con.close();
	}
	
	//This method is used for select query 
	public  ResultSet selecQueryExecution(String query) throws SQLException
	{
		state=con.createStatement();
		return state.executeQuery(query);
	}
	
	//This method is used for update the query
	public  int updateQuery(String query) throws SQLException
	{
		state=con.createStatement();
		return state.executeUpdate(query);
	}

}
