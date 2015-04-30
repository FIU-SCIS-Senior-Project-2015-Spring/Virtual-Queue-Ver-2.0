package com.virtual.queue.beans;

import java.io.Serializable;

public class User extends AbstractUser implements Serializable {

	private static final long serialVersionUID = 7503398206106993501L;

	private Long _userid;
	private String _firstName;
	private String _lastName;
	private String _userName;
	private String _code;
	private String _password;
	private String _email;
	private String _securityAnswer;
	private String _phoneNumber;
	private Role _userRole;
	private String _securityQuestion;
	private String _age;
	private String _height;
	private String _weight;
	private String _enabled;

	public User() {
	}

	public User(Long userId, String firstName, String lastName, String userName, String code,
			String password, String email, String securityAnswer,
			String securityQuestion, String phoneNumber, Role role, String age,
			String height, String weight, String enabled) {

		_userid = userId;
		_code = code;
		_firstName = firstName;
		_lastName = lastName;
		_userName = userName;
		_password = password;
		_email = email;
		_securityAnswer = securityAnswer;
		_securityQuestion = securityQuestion;
		_phoneNumber = phoneNumber;
		_age = age;
		_height = height;
		_weight = weight;
		_enabled = enabled;
		setUserRole(role);

	}

	public String getCode() {
		return _code;
	}

	public void setCode(String code) {
		this._code = code;
	}

	private String securityQuestion;

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		this._userName = userName;
	}

	

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		this._password = password;
	}

	public String getSecurityAnswer() {
		return _securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this._securityAnswer = securityAnswer;
	}

	public String getPhoneNumber() {
		return _phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this._phoneNumber = phoneNumber;
	}

	public Long getUserid() {

		return _userid;

	}

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUserid(Long userid) {

		this._userid = userid;

	}

	public String getFirstName() {

		return _firstName;

	}

	public void setFirstName(String firstName) {

		this._firstName = firstName;

	}

	public String getLastName() {

		return _lastName;

	}

	public void setLastName(String lastName) {

		this._lastName = lastName;

	}

	public String getEmail() {

		return _email;

	}

	public void setEmail(String email) {

		this._email = email;

	}

	@Override
	public String toString() {

		return "User [firstName=" + _firstName

		+ ", lastName=" + _lastName + ", _email="

		+ _email + ", _password=" + _password + ", _securityAnswer="
				+ _securityAnswer + ", _phoneNumber=" + _phoneNumber
				+ ", securityQuestion=" + securityQuestion + "]";

	}

	public static User getDemoUser() {

		User demoUser = new User();

		demoUser.setEmail("test@gmail.com");
		demoUser.setFirstName("DemoName");
		demoUser.setLastName("DemoLastName");
		demoUser.setUserid(1111L);
		demoUser.setToken("123456");
		demoUser.setUserName("test@test.com");
		return demoUser;

	}

	@Override
	public Boolean isNill() {

		return false;
	}

	public String get_securityQuestion() {
		return _securityQuestion;
	}

	public void set_securityQuestion(String _securityQuestion) {
		this._securityQuestion = _securityQuestion;
	}

	public Role getUserRole() {
		return _userRole;
	}

	public void setUserRole(Role _userRole) {
		this._userRole = _userRole;
	}

	public String getAge() {
		return _age;
	}

	public void setAge(String _age) {
		this._age = _age;
	}

	public String getHeight() {
		return _height;
	}

	public void setHeight(String _height) {
		this._height = _height;
	}

	public String getWeight() {
		return _weight;
	}

	public void setWeight(String _weight) {
		this._weight = _weight;
	}

	public String getEnabled() {
		return _enabled;
	}

	public void setEnabled(String _enabled) {
		this._enabled = _enabled;
	}


	

}
