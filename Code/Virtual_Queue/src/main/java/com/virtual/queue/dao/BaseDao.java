package com.virtual.queue.dao;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.virtual.queue.utility.DBUtil.DBUtility;

public abstract class BaseDao {

	 

	public Connection getConnection() {

		return DBUtility.getConnection();

		 

	}

	public void closeConnection(Connection connection) {

		if (connection != null)
			try {
				if (!connection.isClosed())
					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	
}
