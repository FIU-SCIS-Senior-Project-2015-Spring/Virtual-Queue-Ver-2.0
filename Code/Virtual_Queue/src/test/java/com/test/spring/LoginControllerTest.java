package com.test.spring;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.test.spring.mockups.LoginServiceImpMock;
import com.virtual.queue.controller.LoginController;
import com.virtual.queue.request.LoginRequest;
import com.virtual.queue.service.LoginService;

public class LoginControllerTest {
  LoginController  controller;
  HttpServletRequest request;
  LoginService service;
 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	


	 service= new LoginServiceImpMock();
	 
	 controller=new LoginController(service);
	 
	 request= new HttpServletRequest() {
		// HttpSession session= new HttpSession();
		@Override
		public void setCharacterEncoding(String env)
				throws UnsupportedEncodingException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void setAttribute(String name, Object o) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void removeAttribute(String name) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean isSecure() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public int getServerPort() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public String getServerName() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getScheme() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public RequestDispatcher getRequestDispatcher(String path) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getRemotePort() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public String getRemoteHost() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getRemoteAddr() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getRealPath(String path) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public BufferedReader getReader() throws IOException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getProtocol() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String[] getParameterValues(String name) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Enumeration getParameterNames() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Map getParameterMap() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getParameter(String name) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Enumeration getLocales() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Locale getLocale() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getLocalPort() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public String getLocalName() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getLocalAddr() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public ServletInputStream getInputStream() throws IOException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getContentType() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getContentLength() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public String getCharacterEncoding() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Enumeration getAttributeNames() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Object getAttribute(String name) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean isUserInRole(String role) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean isRequestedSessionIdValid() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean isRequestedSessionIdFromUrl() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean isRequestedSessionIdFromURL() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean isRequestedSessionIdFromCookie() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public Principal getUserPrincipal() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public HttpSession getSession(boolean create) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public HttpSession getSession() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getServletPath() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getRequestedSessionId() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public StringBuffer getRequestURL() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getRequestURI() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getRemoteUser() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getQueryString() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getPathTranslated() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getPathInfo() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getMethod() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getIntHeader(String name) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public Enumeration getHeaders(String name) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Enumeration getHeaderNames() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getHeader(String name) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public long getDateHeader(String name) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public Cookie[] getCookies() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getContextPath() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getAuthType() {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSignIn() {
		
		LoginRequest login= new LoginRequest();
		login.setCode("code");
		login.setUserName("username");
		login.setPassword("password");
		
		
		HttpServletResponse response = null; 
		ResponseEntity<String> result=controller.signIn(login, request, response);
		 //assertEquals(HttpStatus.OK,result.getStatusCode());
		 assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,result.getStatusCode());
		 
	
	
	}
	
	@Test
	public void testSignInNull() {
		
		LoginRequest login= new LoginRequest();
		login.setCode("code");
		login.setUserName(null);
		login.setPassword(null);
		
		HttpServletResponse response = null; 
		ResponseEntity<String> result=controller.signIn(login, request, response);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,result.getStatusCode());
		 
		 
	
	
	}

	@Test
	public void testSignOut() {
		
		ResponseEntity<String> result   =controller.signOut("test", request);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,result.getStatusCode());
	
	}
	@Test
	public void testSignOutNull() {
		ResponseEntity<String> result   =controller.signOut(null, request);
		assertEquals(HttpStatus.BAD_REQUEST,result.getStatusCode());
	
	
	}
}
