package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {
	public DBAccess(){
	}
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("��������δ�ҵ�!");
			e.printStackTrace();
		}
		String url="jdbc:mysql://localhost:3306/file?characterEncoding=gbk";
		String user="root";
		String password="123";
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("���ݿ����������������Ӵ����������Ƿ���ȷ!");
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Connection connection=getConnection();
	}

}
