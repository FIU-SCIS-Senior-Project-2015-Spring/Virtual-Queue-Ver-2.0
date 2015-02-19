package com.virtual.queue.utility;

@SuppressWarnings("unused")
public interface DataSource {

	public String getJdbcUrl();

	public void setJdbcUrl(String jdbcUrl);

	public String getUserName();

	public void setUserName(String userName);

	public String getPassword();

	public void setPassword(String password);

	public String getDriver();

	public void setDriver(String driver);

}
