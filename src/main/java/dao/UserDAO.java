package dao;

import db.MyConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class UserDAO {
	public static boolean isExists(String email) throws SQLException{
		Connection connection=MyConnection.getConnection();
		
		PreparedStatement ps=connection.prepareStatement("select email from users");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			String e =rs.getString(1);
			if(e.equals(email))
				return true;
		}
		return false;
	}
	public static int saveUser(User user) throws SQLException{
		
		Connection connection=MyConnection.getConnection();
		PreparedStatement ps=connection.prepareStatement("insert into users values(default,?,?,?)");
		ps.setString(1, user.getName());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getPassword());
		return ps.executeUpdate();
		
	}
	public static boolean checkPassword(String email,String password) throws SQLException{
		Connection connection=MyConnection.getConnection();
		PreparedStatement ps=connection.prepareStatement("select password from users where email=?");
		ps.setString(1, email);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			String e =rs.getString(1);
			if(e.equals(password))
				return true;
		}
		return false;	
	}
	
	public static void changePassword(String email,String password) throws SQLException{
		Connection connection=MyConnection.getConnection();
		PreparedStatement ps=connection.prepareStatement("update users set password=? where email=? ");
		ps.setString(1, password);
		ps.setString(2, email);
		ps.executeUpdate();		
	}
	
	public static String getUserName(String email) throws SQLException{
		
		Connection connection=MyConnection.getConnection();
		PreparedStatement ps=connection.prepareStatement("select name from users where email=?");
		ps.setString(1, email);
		ResultSet rs=ps.executeQuery();
		String name="";
		while(rs.next()) {
			name =rs.getString(1);
			
		}
		return name;
	
		
	}
}
