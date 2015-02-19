package com.virtual.queue.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
import com.virtual.queue.beans.Role;
import com.virtual.queue.beans.User;
import com.virtual.queue.beans.UserQueueInfo;
import com.virtual.queue.exception.ResetPasswordException;

@Repository
@Transactional
public class UserDaoImp extends BaseDao implements UserDao {

	private static String ADD_USERS = "INSERT INTO VirtualQueueDB.VenueRegisteredUser (first_name,last_name,email,user_password, "
			+ "user_name,security_question, security_answer, phone_number, age, code_id, enabled,notification_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	private static String ALL_USERS = "select * from user limit 100";

	private static String GET_USER = "Select u.user_id , u.security_question , u.security_answer , u.user_password from VirtualQueueDB.VenueRegisteredUser u WHERE u.user_name = ? AND u.security_question = ? AND u.security_answer = ? ";

	private static String RESET_PASSWORD = "UPDATE VirtualQueueDB.VenueRegisteredUser SET user_password = ? WHERE user_id = ? ";

	private static String GET_USER_BY_ID = "Select * from VirtualQueueDB.VenueRegisteredUser where user_id=?";

	private static final String DELETE_USER_FROM_QUEUE = "DELETE FROM VirtualQueueDB.UserQueue WHERE user_id= ? and queue_id=(Select myqueue_id From Ride where ride_id= ? )";
	// add code and get code id.
	private static final String ADD_CODE = "INSERT INTO VirtualQueueDB.Code (code_number,generated_dt) VALUES (?,?)";
	private static final String GET_CODE = "SELECT code_id from VirtualQueueDB.Code where code_number = ? ";
	private static final String ADD_ROLE = "INSERT INTO VirtualQueueDB.UserRole (user_id,role_id) VALUES (?,?)";
	private static final String GET_ROLE_BY_TYPE = "Select role_id from VirtualQueueDB.Role where role_type=? ";
	private static final String GET_USER_BY_USER_NAME = " Select * from VirtualQueueDB.VenueRegisteredUser where user_name=?";
	private static final String GET_ROLE_BY_ID = " Select * from VirtualQueueDB.Role where role_id=?";
	private static final String GET_ROLE_BY_USERID = " Select * from VirtualQueueDB.Role where role_id=(Select user_id from VirtualQueueDB.UserRole where user_id = ? )";
	private static String EDIT_USER = "UPDATE VirtualQueueDB.VenueRegisteredUser SET first_name = ?,last_name = ?,email = ?,user_password = ? , "
			+ " user_name = ?,security_question = ?, security_answer = ?, phone_number = ?, age  = ? WHERE user_name = ? ";
	private static String ENABLED_DISABLED_USER = "UPDATE VirtualQueueDB.VenueRegisteredUser SET enabled = ? WHERE user_id = ?";

	@Override
	public User getUser(String username, String passwd) {

		// TODO:validate user input.
		return User.getDemoUser();
	}

	@Override
	public String getCurrentlyAuthenticatedUserName() {

		return User.getDemoUser().getUserName();
	}

	@Override
	public User getUserByToken(String token) {
		return User.getDemoUser();
	}

	@Override
	public String storeToken(Long userId) {
		return User.getDemoUser().getToken();
	}

	@Override
	public List<User> getAll() {
		
		List<User> users = new ArrayList<User>();
		Connection con = getConnection();

		try { 
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(ALL_USERS);
			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getLong("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("user_name"));
				user.setSecurityQuestion(rs.getString("security_question"));
				user.setAge(rs.getString("age"));
				user.setCode(rs.getString("code"));
				user.setEnabled("1");
				users.add(user);
			}
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		
		return users;

	}

	public long getCodeIdByCode(String code) throws SQLException {

		long id = 0;

		PreparedStatement updateemp = null; 
		Connection con = getConnection();
		ResultSet rs = null;
		try {

			updateemp = con.prepareStatement(GET_CODE);

			updateemp.setString(1, code);

			rs = updateemp.executeQuery();

			if (rs.next()) {

				id = rs.getLong("code_id");

			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			if (rs != null)
				rs.close();
			if (updateemp != null)
				updateemp.close();

			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		return id;

	}

	private void insertCode(String code) throws SQLException {

		PreparedStatement updateemp = null;

		Connection conn = getConnection();

		try {
			conn.setAutoCommit(false);
			updateemp = conn.prepareStatement(ADD_CODE);
			updateemp.setString(1, code);
			updateemp.setDate(2, new java.sql.Date(new Date().getTime()));
			updateemp.executeUpdate();
			conn.commit();
		} catch (SQLException e) {

			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			throw e;
		} finally {

			if (updateemp != null) {
				try {
					updateemp.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void addUser(User user) throws SQLException {

		// first transaction insert code on code table.
		// this method will open and close a connection with jdbc
		// transaction
		insertCode(user.getCode()); // insert code on code table
		long codeId = getCodeIdByCode(user.getCode());
		// Abort operation with not code found.
		if (codeId == 0)
			throw new SQLException("code id not inserted properly");

		// starts add user operations.
		PreparedStatement updateemp = null;
		PreparedStatement addRole = null;

		Connection myConn = getConnection();

		try {
			myConn.setAutoCommit(false);

			// second transaction add user to user table.
			updateemp = myConn.prepareStatement(ADD_USERS);
			updateemp.setString(1, user.getFirstName());
			updateemp.setString(2, user.getLastName());
			updateemp.setString(3, user.getEmail());
			updateemp.setString(4, user.getPassword());
			updateemp.setString(5, user.getEmail());
			updateemp.setString(6, user.getSecurityQuestion());
			updateemp.setString(7, user.getSecurityAnswer());
			updateemp.setString(8, user.getPhoneNumber());
			updateemp.setString(9, user.getAge());
			// insertCode(user.getCode()); // insert code on code table
			updateemp.setLong(10, codeId);
			updateemp.setString(11, "1");
			updateemp.setInt(12, 1);
			updateemp.executeUpdate();
			myConn.commit();
			
			long userId = getUserByUserName(user.getEmail()).getUserid();

			// third transaction add role for this user to user role table.
			addRole = myConn.prepareStatement(ADD_ROLE);
			addRole.setLong(1, userId);
			addRole.setLong(2, getRoleIdByType("USER"));
			addRole.executeUpdate();

			// commit transaction
			myConn.commit();

		} catch (SQLException e) {

			e.printStackTrace();
			myConn.rollback();
		} finally {
			// close prepared statements.
			if (updateemp != null) {
				try {
					updateemp.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// close prepared statements.
			if (addRole != null) {
				try {
					addRole.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// close my connection
			if (myConn != null && !myConn.isClosed()) {

				myConn.close();
			}
		}

	}

	@Override
	public void updateUser(User user) {
		System.out.println("User was suscefully updated!");

	}

	@Override
	public void deleteUserById(Long id) {
		System.out.println("User was suscefully deleted!");

	}

	@Override
	public User getUserByUserName(String userName) {
		PreparedStatement statement = null;

		User user = new User();
		Connection con = getConnection();
		try {
			statement = con.prepareStatement(GET_USER_BY_USER_NAME);

			statement.setString(1, userName);

			ResultSet result = statement.executeQuery();

			if (result.next()) {

				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("user_password"));
				user.setUserName(result.getString("user_name"));
				user.setSecurityQuestion(result.getString("security_question"));
				user.setSecurityAnswer(result.getString("security_answer"));
				user.setPhoneNumber(result.getString("phone_number"));
				user.setAge(result.getString("age"));
				user.setUserid(result.getLong("user_id"));
				user.setCode(result.getString("code_id"));
				Role role = getRoleByUserId(result.getLong("user_id"));
				user.setUserRole(role);
			}
			if (result != null)
				result.close();

		} catch (SQLException e) {
			// TODO need to add log4j output
			e.printStackTrace();

		} catch (Exception ex) {

			// TODO need to add log4j output
			ex.printStackTrace();

		} finally {

			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			try {
				if (con != null && !con.isClosed()) {

					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return user;

	}

	@Override
	public Boolean resetPassword(String userName, String securityAnswer,
			String securityQuestion, String newPassword) throws Exception {

		PreparedStatement updateemp = null;
		PreparedStatement resetting = null;
		String secQuestion = null;
		String secAnswer = null;
		String oldPassword = null;
		Long userId = 0L;
		Connection con = getConnection();
		try {

			getConnection();

			resetting = con.prepareStatement(GET_USER);
			resetting.setString(1, userName);
			resetting.setString(2, securityQuestion);
			resetting.setString(3, securityAnswer);
			ResultSet rs = resetting.executeQuery();

			if (rs.next()) {

				userId = rs.getLong("user_id");
				secQuestion = rs.getString("security_question");
				secAnswer = rs.getString("security_answer");
				oldPassword = rs.getString("user_password");

			}
			// Close result set.
			if (rs != null && !rs.isClosed())
				rs.close();

			// validate for null and empty values
			if (secQuestion == null || "".equals(secQuestion)
					|| secAnswer == null || "".equals(secAnswer)
					|| oldPassword == null)
				return false;

			// check for valid information
			if (!securityQuestion.equalsIgnoreCase(secQuestion)
					|| !securityAnswer.equalsIgnoreCase(secAnswer)
					|| userId < 1)
				return false;

			// check for same password
			if (!"".equals(newPassword) && oldPassword.equals(newPassword))
				return false;

			updateemp = con.prepareStatement(RESET_PASSWORD);
			updateemp.setLong(1, userId);
			updateemp.setString(2, newPassword);
			updateemp.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			// throw new ResetPasswordException(e.getMessage());

		} finally {

			if (resetting != null)
				resetting.close();
			if (updateemp != null)
				updateemp.close();

			try {
				if (con != null && !con.isClosed()) {

					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;

	}

	@Override
	public Boolean editUserById(String newFirstName, String newLastName,
			String newEmail, String password, String newUserName,
			String securityAnswer, String securityQuestion, String newCell,
			String newAge, String newCode) throws Exception {

		PreparedStatement updateemp = null;
		Connection con = getConnection();
		try {

			updateemp = con.prepareStatement(EDIT_USER);
			// ODO this needs to be change to use user_id(PK) to perform updates
			updateemp.setString(1, newFirstName);
			updateemp.setString(2, newLastName);
			updateemp.setString(3, newEmail);
			updateemp.setString(4, password);
			updateemp.setString(5, newEmail);
			updateemp.setString(6, securityAnswer);
			updateemp.setString(7, securityQuestion);
			updateemp.setString(8, newCell);
			updateemp.setString(9, newAge);
			updateemp.setString(10, newEmail);

			int execute = updateemp.executeUpdate();

			if (execute > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {

			e.printStackTrace();
			// throw new ResetPasswordException(e.getMessage());

		} finally {

			if (updateemp != null)
				updateemp.close();

			try {
				if (con != null && !con.isClosed()) {

					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;

	}

	@Override
	public User authenticateUser(String userName, String securityQuestion,
			String securityAnwser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(long userId) {

		User user = new User();

		Connection con = getConnection();

		try {
			PreparedStatement statement = con.prepareStatement(GET_USER_BY_ID);

			statement.setLong(1, userId);

			ResultSet result = statement.executeQuery();

			if (result.next()) {

				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("user_password"));
				user.setUserName(result.getString("user_name"));
				user.setSecurityQuestion(result.getString("security_question"));
				user.setSecurityAnswer(result.getString("security_answer"));
				user.setPhoneNumber(result.getString("phone_number"));
				user.setAge(result.getString("age"));
			}

			result.close();
			statement.close();
		} catch (SQLException e) {
			// TODO need to add log4j output
			e.printStackTrace();

		} catch (Exception ex) {

			// TODO need to add log4j output
			ex.printStackTrace();

		} finally {

			try {
				if (con != null && !con.isClosed()) {

					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return user;
	}

	@Override
	public boolean removeUserFromQueue(long userId, long rideId) {
		PreparedStatement updateemp = null;
		Connection con = getConnection();

		try { 

			updateemp = con.prepareStatement(DELETE_USER_FROM_QUEUE);
			updateemp.setLong(1, userId);
			updateemp.setLong(2, rideId);
			updateemp.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			// throw new ResetPasswordException(e.getMessage());
			// TODO:needs to handle errors and return to caller with a message.
			return false;

		} finally {

			if (updateemp != null)
				try {
					updateemp.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			try {
				if (con != null && !con.isClosed()) {

					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;

	}

	@Override
	public User getUserToModify(long userId) {
		User user = new User();
		Connection con = getConnection();

		try {
			PreparedStatement statement = con.prepareStatement(GET_USER_BY_ID);

			statement.setLong(1, userId);

			ResultSet result = statement.executeQuery();

			if (result.next()) {

				// TODO:fill out user object
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("user_password"));
				user.setUserName(result.getString("user_name"));
				user.setSecurityQuestion(result.getString("security_question"));
				user.setSecurityAnswer(result.getString("security_answer"));
				user.setPhoneNumber(result.getString("phone_number"));
				user.setAge(result.getString("age"));

			}

			result.close();
			statement.close();
		} catch (SQLException e) {
			// TODO need to add log4j output
			e.printStackTrace();

		} catch (Exception ex) {

			// TODO need to add log4j output
			ex.printStackTrace();

		} finally {
			try {
				if (con != null && !con.isClosed()) {

					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return user;
	}

	private long getRoleIdByType(String type) {
		long roleId = 0;
		Connection con = getConnection();

		try {
			PreparedStatement statement = con
					.prepareStatement(GET_ROLE_BY_TYPE);

			statement.setString(1, type);

			ResultSet result = statement.executeQuery();

			if (result.next()) {

				roleId = result.getLong("role_id");

			}

			result.close();
			statement.close();

		} catch (SQLException e) {
			// TODO need to add log4j output
			e.printStackTrace();

		} catch (Exception ex) {

			// TODO need to add log4j output
			ex.printStackTrace();

		} finally {

			try {
				if (con != null && !con.isClosed()) {

					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return roleId;

	}

	@Override
	public boolean AddRole(long userId, String roleType) throws SQLException {

		PreparedStatement updateemp = null;
		boolean result = false;
		Connection con = getConnection();
		try {

			updateemp = con.prepareStatement(ADD_ROLE);

			updateemp.setLong(1, userId);
			updateemp.setLong(2, getRoleIdByType(roleType));
			int count = updateemp.executeUpdate();
			if (count > 0)
				result = true;

		} catch (SQLException e) {

			e.printStackTrace();
			throw e;
		} finally {

			if (updateemp != null) {
				try {
					updateemp.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				if (con != null && !con.isClosed()) {

					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;

	}

	@SuppressWarnings("unused")
	private Role getRoleById(long roleId) {

		Role role = new Role();

		Connection con = getConnection();

		try {
			PreparedStatement statement = con.prepareStatement(GET_ROLE_BY_ID);

			statement.setLong(1, roleId);

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				role.setRoleId(result.getString("role_id"));
				role.setRoleType(result.getString("role_type"));
				role.setRoleDesc(result.getString("role_desc"));
				role.setEnable(result.getBoolean("enabled"));
			}

			result.close();
			statement.close();

		} catch (SQLException e) {
			// TODO need to add log4j output
			e.printStackTrace();

		} catch (Exception ex) {

			// TODO need to add log4j output
			ex.printStackTrace();

		} finally {

			try {
				if (con != null && !con.isClosed()) {

					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return role;

	}

	private Role getRoleByUserId(long userId) {

		Role role = new Role();
		PreparedStatement statement = null;
		Connection con = getConnection();
		try {
			statement = con.prepareStatement(GET_ROLE_BY_USERID);

			statement.setLong(1, userId);

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				role.setRoleId(result.getString("role_id"));
				role.setRoleType(result.getString("role_type"));
				role.setRoleDesc(result.getString("role_desc"));
				role.setEnable(result.getBoolean("enabled"));
			}
			if (result != null)
				result.close();

		} catch (SQLException e) {
			// TODO need to add log4j output
			e.printStackTrace();

		} catch (Exception ex) {

			// TODO need to add log4j output
			ex.printStackTrace();

		} finally {
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			try {
				if (con != null && !con.isClosed()) {

					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return role;

	}

	@Override
	public boolean enabledDisabledUser(long userId, String flag) {

		String myFlag = "0";
		PreparedStatement updateemp = null;
		Connection conn = null;
		try {

			conn = getConnection();
			conn.setAutoCommit(false);
			if ("true".equalsIgnoreCase(flag))
				myFlag = "1";

			updateemp = conn.prepareStatement(ENABLED_DISABLED_USER);
			// ODO this needs to be change to use user_id(PK) to perform updates
			updateemp.setLong(2, userId);
			updateemp.setString(1, myFlag);

			int execute = updateemp.executeUpdate();
			conn.commit();

			if (execute > 0) {
				return true;
			}

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			// throw new ResetPasswordException(e.getMessage());

		} finally {

			if (updateemp != null)
				try {
					updateemp.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			try {
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}
}
