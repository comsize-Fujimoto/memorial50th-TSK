package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	//データベースアクセスの下準備
	
	private final static String URL = "jdbc:mysql://localhost:3306/task_db";
	private final static String USER = "root";
	private final static String PASSWORD = "mysql";
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

}
