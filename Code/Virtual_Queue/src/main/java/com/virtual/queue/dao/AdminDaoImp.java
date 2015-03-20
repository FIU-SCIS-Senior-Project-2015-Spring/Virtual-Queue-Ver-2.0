package com.virtual.queue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.virtual.queue.beans.User;

@Repository
@Transactional
public class AdminDaoImp extends BaseDao implements AdminDao {
	
	//Changing SQL Query to the current database
//	private static final String GET_ALL_USERS = "SELECT u.user_id, u.first_name, u.last_name, u.enabled, u.user_name "
//			+ " FROM VirtualQueueDB.VenueRegisteredUser u, VirtualQueueDB.UserRole ur, VirtualQueueDB.Role r  "
//			+ " WHERE u.user_id = ur.user_id AND r.role_id = ur.role_id AND  r.role_type = 'USER' ";
	private static final String GET_ALL_USERS = " Select * From vqdatabase.patron p, vqdatabase.visitor v WHERE p.patron_id = v.patron_id AND p.role_type = 'User'";
	
	
	@Override
	public LinkedList<User> getAllUsers() {
		
		LinkedList<User> infoList = new LinkedList<User>();
		User user = null;
		
		Connection con=getConnection();
		try {
			 
			PreparedStatement statement = con.prepareStatement(
					GET_ALL_USERS);

			
			ResultSet result = statement.executeQuery();
			System.out.println("Admin simulate ride method");

			while (result.next()) {

				user = new User();
				user.setUserid(result.getLong("venue_id"));
				user.setFirstName(result.getString("name_first"));
				user.setLastName(result.getString("name_last"));
				//This is not implmented in the current database design
				//will keep it enabled right now
				user.setEnabled("enabled");
				user.setUserName(result.getString("username"));
				infoList.add(user);
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

		return infoList;
	}

}
