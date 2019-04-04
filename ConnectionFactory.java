package com.qiuyu.factory;

import java.sql.*;

public class ConnectionFactory {

	private static Connection conn;
	
	
	static {
		try {
			//加载驱动，没有驱动没有底层连接数据库的操作
			Class.forName("com.mysql.jdbc.Driver");
			//从驱动管理中心中获取一个connection对象
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfo","root","123456");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static int execDML(String sql) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			return stmt.executeUpdate(sql);
		} catch (SQLException e) {
			return -1;
		}
		
	}
	
	
	
	
	public static ResultSet execDQL(String sql) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			return null;
		}
		
	}
	
	
	
}
