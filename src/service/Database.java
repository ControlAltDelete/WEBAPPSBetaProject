package service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import src.Model.Request;
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
			  User user = new User();
			  user.setEmail(resultSet.getString("email"));
			  user.setUsertype(resultSet.getString("usertype"));
			  results.add(user);
		  }
		  
		  connect.close();
		}
		
		catch(SQLException e)
		{
		
		}
			
		return results;
	  }
	  
	  public ArrayList<User> getEditEmail(String query,String query2) throws ClassNotFoundException
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
			  results.add(new User(resultSet.getString(1), resultSet.getString(2), null));
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
			  results.add(new User(resultSet.getString(1), resultSet.getString(2),null));
		  }
		  
		  connect.close();
		}
		
		catch(SQLException e)
		{
		
		}
			
		return results;
	  }
	  
	  public int getRequest(String title,String description,String tag,String path,String email) throws ClassNotFoundException
	  {

		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Date utilDate = cal.getTime();
		java.sql.Date sqlDate = new Date(utilDate.getTime());  
		  
		String queryy = "insert into request (title,description,tag,url,date,userID,selected) values ('" 
						+ title + "','" + description + "','" + tag + "','" + path + "','" + sqlDate
						+ "'," + "(SELECT userID from user WHERE email='" + email + "'),'no');";
		//code for accessing db
		System.out.println(queryy);
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
	  
	  public ArrayList<Request> getAdminUpdate() throws ClassNotFoundException
	  {
			
		ArrayList<Request> results = new ArrayList<Request>();
		String queryy = "select * from request where selected = 'yes'";
		//code for accessing db
		
		try
		{
		  
			connect = DriverManager.getConnection(connection, user, pass);
			ResultSet resultSet = stat.executeQuery(queryy);
		  
		  while(resultSet.next())
		  {
			  Request request = new Request();
			  request.setTitle(resultSet.getString("title"));
			  request.setDescription(resultSet.getString("description"));
			  request.setTag(resultSet.getString("tag"));
			  request.setDate(resultSet.getDate("date"));
			  request.setId(resultSet.getInt("requestID"));
			  results.add(request);
		  }
		  
		  connect.close();
		}
		
		catch(SQLException e)
		{
		
		}
			
		return results;
	  }
	  
	  public ArrayList<Request> getRecentUpdate() throws ClassNotFoundException
	  {
			
		ArrayList<Request> results = new ArrayList<Request>();
		String queryy = "select * from request";
		//code for accessing db
		
		try
		{
		  
			connect = DriverManager.getConnection(connection, user, pass);
			ResultSet resultSet = stat.executeQuery(queryy);
		  
		  while(resultSet.next())
		  {
			  Request request = new Request();
			  request.setTitle(resultSet.getString("title"));
			  request.setDescription(resultSet.getString("description"));
			  request.setTag(resultSet.getString("tag"));
			  request.setDate(resultSet.getDate("date"));
			  request.setId(resultSet.getInt("requestID"));
			  results.add(request);
		  }
		  
		  connect.close();
		}
		
		catch(SQLException e)
		{
		
		}
			
		return results;
	  }
	  
	  public int EditEmail(String query, String Newemail) throws ClassNotFoundException
	  {
		
		String update = "UPDATE user SET email='"+ Newemail +"' WHERE email='"+ query +"'";
		try
		{
		  
		  connect = DriverManager.getConnection(connection, user, pass);
		  stat.executeUpdate(update);
		  
		  
		  connect.close();
		}
		
		catch(SQLException e)
		{
			System.out.println(e);
		}
			
		return 1;
	  }
	  
	  
	  public int EditName(String query, String Newname) throws ClassNotFoundException
	  {
		
		String update = "UPDATE user SET nickName='"+ Newname +"' WHERE nickName='"+ query +"'";
		try
		{
		  
		  connect = DriverManager.getConnection(connection, user, pass);
		  stat.executeUpdate(update);
		  
		  
		  connect.close();
		}
		
		catch(SQLException e)
		{
			System.out.println(e);
		}
			
		return 1;
	  }
	  
	  public int EditPassword(String query, String NewPassword) throws ClassNotFoundException
	  {
		
		String update = "UPDATE user SET password='"+ NewPassword +"' WHERE password='"+ query +"'";
		try
		{
		  
		  connect = DriverManager.getConnection(connection, user, pass);
		  stat.executeUpdate(update);
		  
		  
		  connect.close();
		}
		
		catch(SQLException e)
		{
			System.out.println(e);
		}
			
		return 1;
	  }
	  
	  public String getPhoto(int id) throws ClassNotFoundException, IOException
	  {
		 String queryy = "select * from request where requestID = '" + id + "'";
		 
		 Blob img = null;
		 byte[] imgData = new byte[1024];
		 ImageManipulation manipulation = new ImageManipulation();
		 String blobb = null;
		 
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();

		 try
		 {
			 connect = DriverManager.getConnection(connection, user, pass);
			 ResultSet resultSet = stat.executeQuery(queryy); 
			 
			 while (resultSet.next ())
			 { 
			   System.out.println(resultSet.getString("title"));
			   img = resultSet.getBlob("url");
			    	//blobb = imgData;
			 }
			 
			 InputStream in = img.getBinaryStream();
			 
			 int n = 0;
			 
			 while ((n = in.read(imgData)) >= 0)
			 {
			   baos.write(imgData, 0, n);
			 }
			    
			 in.close();
			 byte[] bytes = baos.toByteArray();
			 blobb = new String(bytes);
			 connect.close(); 
			 
		 }catch(SQLException e)
		 {
			 System.out.println(e);
		 }
		 
		    
		    return blobb;
	  }
	  
	  public ArrayList<Request> getSearch(String tag)
	  {
		  String query = "select * from request where tag = '" + tag + "'";
		  ArrayList<Request> result = new ArrayList<Request>();
		  
		  try
		  {
				 connect = DriverManager.getConnection(connection, user, pass);
				 ResultSet resultSet = stat.executeQuery(query);
				 while(resultSet.next())
				 {
					  Request request = new Request();
					  request.setId(resultSet.getInt("requestID"));
					  request.setTitle(resultSet.getString("title"));
					  request.setDescription(resultSet.getString("description"));
					  request.setTag(resultSet.getString("tag"));
					  request.setDate(resultSet.getDate("date"));
					  result.add(request);
				 }
		  }catch(SQLException e)
		  {
			  System.out.println(e);
		  }
		  
		  return result;
	  }
	  
	  public ArrayList<Request> getList()
	  {
		  String query = "select * from request";
		  ArrayList<Request> result = new ArrayList<Request>();
		  
		  try
		  {
				 connect = DriverManager.getConnection(connection, user, pass);
				 ResultSet resultSet = stat.executeQuery(query);
				 while(resultSet.next())
				 {
					  Request request = new Request();
					  request.setId(resultSet.getInt("requestID"));
					  request.setTitle(resultSet.getString("title"));
					  request.setDescription(resultSet.getString("description"));
					  request.setTag(resultSet.getString("tag"));
					  request.setDate(resultSet.getDate("date"));
					  request.setSelected(resultSet.getString("selected"));
					  result.add(request);
				 }
		  }catch(SQLException e)
		  {
			  System.out.println(e);
		  }
		  
		  return result;
	  }
}
