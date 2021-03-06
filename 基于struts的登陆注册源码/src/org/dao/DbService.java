package org.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.model.User;

public class DbService {
	
	//添加注册用户
	public void addUser(User user){
		Connection connection=DbConnection.getConnection();
		PreparedStatement ps=null;
		String sql="insert into simple_tb (username,password) values(?,?)";
		try{
			ps=connection.prepareStatement(sql);
			System.out.println("\n======="+user.getUsername());
		    System.out.println("\n======="+user.getPassword());
			ps.setString(1, user.getUsername());//将数据写入数据库
			ps.setString(2, user.getPassword());
			ps.executeUpdate();
			//Executes the SQL statement in this PreparedStatement object;
		}catch (SQLException e) {
			// TODO: handle exception 
			e.printStackTrace();
		}
		
	}
	
	//查找用户是否存在
	public User hasUser(String username,String password){
		User user=new User();
		Connection connection=DbConnection.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from simple_tb where username=? and password=?";
		try {
			ps=connection.prepareStatement(sql); 
			ps.setString(1, username);
			//key-value中的key对应着sql语句中的第几个“？”
			ps.setString(2, password);
			rs=ps.executeQuery();
			/*Executes the SQL query in this PreparedStatement object and
			 *  returns the ResultSet object generated by the query.*/

			if(rs.next()){	//从第一行数据开始查找
				System.out.println("\n======="+rs.getString(1));
			    System.out.println("\n======="+rs.getString(2));
				user.setUsername(rs.getString("username"));//将查找到的数据返回
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnection.closeConnection(rs, ps, connection);
		}
		return user;
	}
	
	//查找用户名是否已经被注册
	public Boolean hasSameUser(String username){
		String name=null;
		Connection connection=DbConnection.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from simple_tb where username=?";
		try {
			ps=connection.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			if(rs.next()){
				name=rs.getString("username");
				if(name.equals(username))
					return true;
				else
					return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbConnection.closeConnection(rs, ps, connection);
		}
		return false;
	}

}
