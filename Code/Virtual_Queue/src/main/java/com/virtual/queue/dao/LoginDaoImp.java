package com.virtual.queue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.virtual.queue.beans.Role;
import com.virtual.queue.beans.User;
// USED to login section of the website
@Repository
public class LoginDaoImp extends BaseDao implements LoginDao {

//	private static String GET_USER_SESSION = "SELECT u.user_name, u.user_password, "
//			+ "u.user_id, c.code_number, r.enabled, r.role_type, r.role_desc "
//			+ "FROM VirtualQueueDB.VenueRegisteredUser u, VirtualQueueDB.Code c, VirtualQueueDB.UserRole ur, "
//			+ "VirtualQueueDB.Role r  WHERE  u.code_id = c.code_id AND u.user_id = ur.user_id "
//			+ "AND r.role_id = ur.role_id AND u.user_name = ? AND u.user_password = ? "
//			+ "AND  c.code_number= ?";
	
	private static String GET_USER_SESSION = "SELECT * FROM vqdatabase.patron p, vqdatabase.visitor v WHERE p.patron_id = v.patron_id AND"
			+ " p.username = ? AND p.password = ? AND p.code_number = ?";
	private static String GET_ADMIN_SESSION = " Select * From vqdatabase.patron p, vqdatabase.visitor v WHERE p.patron_id = v.patron_id AND  p.username = ? AND p.password = ? AND p.role_type = 'Admin' ";
//	private static String GET_ADMIN_SESSION = "SELECT u.user_name, u.user_password, u.user_id, r.enabled, r.role_type, r.role_desc "
//			+ "FROM VirtualQueueDB.VenueRegisteredUser u, VirtualQueueDB.UserRole ur, VirtualQueueDB.Role r  "
//			+ "WHERE  u.user_id = ur.user_id AND r.role_id = ur.role_id AND u.user_name = ? "
//			+ "AND u.user_password = ? AND r.role_type = 'ADMIN' ";

	@Override
	public boolean signOut(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Long validLogin(String user, String password, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User signIn(String userName, String password, String code) {
       Connection con=getConnection();
		User user = new User();
		try {

			PreparedStatement statement = con.prepareStatement(
					GET_USER_SESSION);
			statement.setString(1, userName);
			statement.setString(2, password);
			statement.setString(3, code);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				user.setUserName(result.getString("username"));
				user.setUserid(result.getLong("visitor_id"));
				user.setPassword(result.getString("password"));
				Role role = new Role();
				role.setRoleType(result.getString("role_type"));
				role.setRoleDesc(result.getString("role_desc"));
				user.setUserRole(role);
			
			}
			
			result.close();
			
			statement.close();
		} catch (SQLException e) {
			// TODO need to add log4j output
			e.printStackTrace();

		} catch (Exception ex) {

			// TODO need to add log4j output
			ex.printStackTrace();

		}finally{
			
			try {
				if(con!=null && !con.isClosed()){
					con.close(); 
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		 

		return user;

	}
	// Self explanatory method name
	// user to access the admin information to see if the 
	//admin username is available in the database
	@Override
	public User adminSignIn(String userName, String password) {
		User user = new User();
		
		Connection con=getConnection();
		try {
			PreparedStatement statement = con.prepareStatement(
					GET_ADMIN_SESSION);
			statement.setString(1, userName);
			statement.setString(2, password);
			

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				user.setUserName(result.getString("username"));
				user.setUserid(result.getLong("visitor_id"));
				user.setPassword(result.getString("password"));
				Role role = new Role();
				role.setRoleType(result.getString("role_type"));
				role.setRoleDesc(result.getString("role_desc"));
				user.setUserRole(role);
			
			}
			
			result.close(); 
			statement.close();
		} catch (SQLException e) {
			// TODO need to add log4j output
			e.printStackTrace();

		} catch (Exception ex) {

			// TODO need to add log4j output
			ex.printStackTrace();

		}finally{
			try {
				if(con!=null && !con.isClosed()){
					con.close();
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		 

		return user;

	}

}
