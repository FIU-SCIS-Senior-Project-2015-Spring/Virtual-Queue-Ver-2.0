package com.virtual.queue.utility;

import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired; 
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.MySQLConnection;

public class DBUtil {

	public static class DBUtility {

//		private static Connection connection = null;
//		private static String URL = "jdbc:mysql://127.0.0.1:3306/VirtualQueueDB";
//		private static String USER = "root";
//		private static String PASSWORD = "ok";
//		private static String DRIVER = "com.mysql.jdbc.Driver";
		private static Connection connection = null;
		private static String URL = "jdbc:mysql://localhost:3306/vqdatabase";
		private static String USER = "root";
		private static String PASSWORD = "naruto123";
		private static String DRIVER = "com.mysql.jdbc.Driver";

		@Autowired
		private static DataSource dataSource;

		public static void printConnection() {

			System.out.println(dataSource.getDriver());
			System.out.println(dataSource.getJdbcUrl());
			System.out.println(dataSource.getPassword());
			System.out.println(dataSource.getUserName());
		}

		public static Connection getConnection() {

			Connection connection=null;
			 
			
			try {

				Class.forName(DRIVER);
<<<<<<< HEAD
				System.out.println("WTF!!!!#!@$!@#$!#@$!#@");
=======
				System.out.println("did this change apply?");
>>>>>>> #90Task
				connection = (MySQLConnection) DriverManager.getConnection(URL,
						USER, PASSWORD);

			} catch (ClassNotFoundException e) {

				e.printStackTrace();

			} catch (SQLException e) {

				e.printStackTrace();
			}

			return connection;

		}

	}

}
