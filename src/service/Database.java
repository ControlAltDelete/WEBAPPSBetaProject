package service;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import src.Model.User;

public class Database {
	  private String user = "root";
	  private String pass = "root";
	  
	  String connection = "jdbc:mysql://localhost:3306/photosurgery";
	  Connection connect;
	  Statement stat;
	  
	  public void initialize() throws ClassNotFoundException{
		  try
		  {
			  Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection(connection, user, pass);
			  stat = connect.createStatement();  
		  }catch(SQLException e)
		  {
			  
		  }
	  }
	  
	  public ArrayList<User> getLogin(String query,String query2) throws ClassNotFoundException
	  {
			
		ArrayList<User> results = new ArrayList<User>();
		String queryy = "select * from user where email ='" + query + "' and password = '" + query2 + "'";
		//code for accessing db
		
		try
		{
		  
			connect = DriverManager.getConnection(connection, user, pass);
		  ResultSet resultSet = stat.executeQuery(queryy);
		  
		  while(resultSet.next())
		  {
			  results.add(new User(resultSet.getString(1), resultSet.getString(2)));
		  }
		  
		  connect.close();
		}
		
		catch(SQLException e)
		{
		
		}
			
		return results;
	  }
	  
	  public int getRegistration(String email,String nickname,String password) throws ClassNotFoundException
	  {

		String queryy = "insert into user (email,password,nickname,usertype) values ('" 
						+ email + "','" + password + "','" + nickname + "','user');";
		//code for accessing db
		
		try
		{
			connect = DriverManager.getConnection(connection, user, pass);
		  stat.executeUpdate(queryy);
		  
		  connect.close();
		}
		
		catch(SQLException e)
		{
			System.out.println(e);
		}
			
		return 1;
	  }
	  
	  public ArrayList<User> getDuplicate(String email) throws ClassNotFoundException
	  {
		
		ArrayList<User> results = new ArrayList<User>();
		String queryy = "select * from user where email = '" + email + "'";
		//code for accessing db
		
		try
		{
			connect = DriverManager.getConnection(connection, user, pass);
		  
		  ResultSet resultSet = stat.executeQuery(queryy);
		  
		  while(resultSet.next())
		  {
			  results.add(new User(resultSet.getString(1), resultSet.getString(2)));
		  }
		  
		  connect.close();
		}
		
		catch(SQLException e)
		{
		
		}
			
		return results;
	  }
	  
	  public int getRequest(String title,String description,String tag,String email) throws ClassNotFoundException
	  {

		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Date utilDate = cal.getTime();
		java.sql.Date sqlDate = new Date(utilDate.getTime());  
		  
		String queryy = "insert into request (title,description,tag,date,userID) values ('" 
						+ title + "','" + description + "','" + tag + "','" + sqlDate
						+ "'," + "(SELECT userID from user WHERE email='" + email + "'));";
		//code for accessing db
		
		try
		{
			connect = DriverManager.getConnection(connection, user, pass);
			stat.executeUpdate(queryy);
		  
			connect.close();
		}
		
		catch(SQLException e)
		{
			System.out.println(e);
		}
			
		return 1;
	  }
	  
}
