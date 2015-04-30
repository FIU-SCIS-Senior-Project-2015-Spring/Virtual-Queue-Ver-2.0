package com.virtual.queue.utility;

import org.springframework.beans.factory.annotation.Value;
import com.virtual.queue.utility.DataSource;

public class DataSourceImp  implements DataSource
{
	@Value("${jdbc.url}")
	private String jdbcUrl;
	@Value("${jdbc.user}")
	private String userName;
	@Value("${jdbc.password}")
	private String password;
	@Value("${jdbc.driver}")
	private String driver;
	@Override
	public String getJdbcUrl() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setJdbcUrl(String jdbcUrl) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setUserName(String userName) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getDriver() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setDriver(String driver) {
		// TODO Auto-generated method stub
		
	}



}
