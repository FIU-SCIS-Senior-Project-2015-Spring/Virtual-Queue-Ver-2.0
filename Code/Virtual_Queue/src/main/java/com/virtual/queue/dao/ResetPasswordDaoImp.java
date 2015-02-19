package com.virtual.queue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.virtual.queue.exception.ResetPasswordException;

@Repository
@Transactional
public class ResetPasswordDaoImp extends BaseDao implements ResetPasswordDao {
	// private static String RESET_PASSWORD =
	// "UPDATE VirtualQueueDB.VenueRegisteredUser SET user_password = ? WHERE user_id = ? ";
	private static String GET_USER = "Select u.user_id , u.security_question , u.security_answer , u.user_password from VirtualQueueDB.VenueRegisteredUser u WHERE u.user_name = ? AND u.security_question = ? AND u.security_answer = ? ";

	@Override
	public void resetPassword(String userName, String securityAnswer,
			String securityQuestion, String newPassword) throws Exception {
		PreparedStatement updateemp = null;
		PreparedStatement resetting = null;
		Statement stm = null;
		String secQuestion = null;
		String secAnswer = null;
		String oldPassword = null;
		Long userId = 0L;
		Connection connection = getConnection();
	

		try {

			resetting = connection.prepareStatement(GET_USER);
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
				throw new ResetPasswordException("Error");

			// check for valid information
			if (!securityQuestion.equalsIgnoreCase(secQuestion)
					|| !securityAnswer.equalsIgnoreCase(secAnswer)
					|| userId < 1)
				throw new ResetPasswordException("Error");

			// check for same password
			if (!"".equals(newPassword) && oldPassword.equals(newPassword))
				throw new ResetPasswordException("Error");
			connection.setAutoCommit(false);

			StringBuilder stbld = new StringBuilder();
			stbld.append("UPDATE VirtualQueueDB.VenueRegisteredUser SET user_password ='");
			stbld.append(newPassword);
			stbld.append("' WHERE user_id = ");
			stbld.append(userId);

			stm = connection.createStatement();
			stm.execute(stbld.toString());
			connection.commit();

		} catch (SQLException e) {

			e.printStackTrace();
			throw new ResetPasswordException(e.getMessage());

		} finally {

			if (stm != null)
				resetting.close();
			if (updateemp != null)
				updateemp.close();
			connection.setAutoCommit(true);

			if (connection != null && !connection.isClosed())
				connection.close();

		}

	}

}
