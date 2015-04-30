package com.test.spring.mockups;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.virtual.queue.beans.User;
import com.virtual.queue.dao.UserDao;

public class UserDaoImpMock implements UserDao {

	@Override
	public User getUser(String username, String passwd) {
		User testUser = new User();
		testUser.setUserid(1L);
		testUser.setFirstName("Ian");
		testUser.setLastName("Smith");

		return testUser;
	}

	@Override
	public String getCurrentlyAuthenticatedUserName() {

		return "username";
	}

	@Override
	public User getUserByToken(String token) {

		User testUser = new User();
		testUser.setUserid(1L);
		testUser.setFirstName("Ian");
		testUser.setLastName("Smith");
		testUser.setToken(token);

		return testUser;
	}

	@Override
	public String storeToken(Long userId) {
		 if(userId ==null ||userId ==0)return null;
		return "token";
	}

	@Override
	public List<User> getAll() {
		List<User> list = new ArrayList<User>();

		User testUser = new User();
		testUser.setUserid(1L);
		testUser.setFirstName("Ian");
		testUser.setLastName("Smith");
		list.add(testUser);

		User testUser1 = new User();
		testUser.setUserid(1L);
		testUser.setFirstName("Juan");
		testUser.setLastName("Caca");
		list.add(testUser1);

		return list;

	}

	@Override
	public void addUser(User user) throws SQLException {
		System.out.println("User added");

	}

	@Override
	public void updateUser(User user) {
		System.out.println("User Updated");

	}

	@Override
	public void deleteUserById(Long id) {

		System.out.println("User deleted");

	}

	@Override
	public User getUserByUserName(String userName) {
		if (userName == null)
			return null;
		User user = new User();
		user.setFirstName("FirstName");
		return user;
	}

	@Override
	public Boolean resetPassword(String userName, String securityAnswer,
			String securityQuestion, String newPassword) throws Exception {

		if (userName == null || securityAnswer == null || newPassword == null)
			return null;

		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public User authenticateUser(String userName, String securityQuestion,
			String securityAnwser) {
		if (userName == null || securityQuestion == null
				|| securityAnwser == null)
			return null;
		User user = new User();
		user.setFirstName("FirstName");
		return user;
	}

	@Override
	public User getUserById(long userId) {
		if (userId > 0)
			return null;
		User user = new User();
		user.setFirstName("FirstName");
		return user;
	}

	@Override
	public boolean removeUserFromQueue(long userId, long rideId) {

		return true;
	}

	@Override
	public User getUserToModify(long userId) {
		if (userId > 0)
			return null;
		User user = new User();
		user.setFirstName("FirstName");
		return user;
	}

	@Override
	public Boolean editUserById(String newFirstName, String newLastName,
			String newEmail, String password, String newUserName,
			String securityAnswer, String securityQuestion, String newCell,
			String newAge, String newCode) throws Exception {
		if (password == null)
			return false;
		return true;
	}

	@Override
	public boolean AddRole(long userId, String roleType) throws SQLException {

		if (userId == 0)
			return false;

		return true;
	}

	@Override
	public boolean enabledDisabledUser(long userId, String flag) {
		if (userId == 0 || flag == null)
			return false;

		return true;
	}

}
