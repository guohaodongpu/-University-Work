package com.ghd.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class TheDao {
	public static Connection getConnection(){
		// ���ݿ�����
		Connection conn = null;
		try {
			// �������ݿ�������ע�ᵽ����������
			Class.forName("com.mysql.jdbc.Driver");
			// ���ݿ������ַ���
			String url = "jdbc:mysql://localhost:3306/ghd";
			// ���ݿ��û���
			String username = "root";
			// ���ݿ�����
			String password = "123456";
			// ����Connection����
			conn = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// �������ݿ�����
		return conn;		
}

	 public static void close(ResultSet rs,PreparedStatement psta,Connection con)throws Exception{
		
		        if(rs!=null){  
	                 //�رս����  
	                rs.close();  
	        }  
	        if(psta!=null){  
	                 //�رղ������  
	                 psta.close();  
	        }  
	        if(con!=null){  
	                //�ر�����  
	                con.close();  
	        }  
	    }  
 
	  
}
