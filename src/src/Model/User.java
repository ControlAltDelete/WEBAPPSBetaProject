package src.Model;

public class User {
	private String username;
	private String password;
	private String usertype;
	private String nickname;
	private String email;
	
	public User()
	{
		username = null;
		password = null;
		usertype = null;
		nickname = null;
		email = null;
	}
	
	public User(String username,String password,String usertype)
	{
		this.username = username;
		this.password = password;
		this.usertype = usertype;
	}
	
	public User(String username,String password, String usertype, String nickname, String email)
	{
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.nickname = nickname;
		this.email = email;
	}
	
	public String getUserName()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getUsertype()
	{
		return usertype;
	}
	
	public void setUsertype(String usertype)
	{
		this.usertype = usertype;
	}
	
	public String getNickname()
	{
		return nickname;
	}
	
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
}
