package com.virtual.queue.handler;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.virtual.queue.beans.NotificationInfo;
import com.virtual.queue.exception.NotificationException;

 

public class EmailNotificationHandlerImp implements NotificationHandler {
    //TODO: this list needs to be set on a property file.
	private static final String EMAIL_PORT="465";
	private static final String SENT_FROM="virtualqueue2014@yahoo.com";
	private static final String EMAIL_ACCT_NAME="virtualqueue2014@yahoo.com";
	private static final String EMAIL_ACCT_PASSWORD="seniorproject";
	private static final String EMAIL_SUBJECT="User notification for your ride";
	
	@Override
	public void notifiyUser(NotificationInfo info) throws NotificationException{
		{  
			//validate user input
			if(info ==null || !info.isValid())throw new NotificationException("invalid info object");
			
			try{
 
		        Properties props = new Properties();
		        props.put("mail.smtp.host", "smtp.mail.yahoo.com"); 
		        props.put("mail.smtp.auth", "true");
		        props.put("mail.debug", "true"); 
		        props.put("mail.smtp.starttls.enable", "true");
		        props.put("mail.smtp.port", EMAIL_PORT);
		        props.put("mail.smtp.socketFactory.port", EMAIL_PORT);
		        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		        props.put("mail.smtp.socketFactory.fallback", "false");

		        Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

		            protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication(EMAIL_ACCT_NAME, EMAIL_ACCT_PASSWORD);
		            }
		        });

		        mailSession.setDebug(true); // Enable the debug mode

		        Message msg = new MimeMessage( mailSession );
 
		        msg.setFrom( new InternetAddress( SENT_FROM) );
		        msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse(info.getEmail()) );
		        msg.setSentDate( new Date());
		        msg.setSubject( EMAIL_SUBJECT );

		        //--[ Create the body of the mail
		        msg.setText( info.getMessage() );

		        //--[ Ask the Transport class to send our mail message
		        Transport.send( msg );

		    }catch(Exception e){
		    	//TODO:replace this for log4j
		    	System.out.println( e.getMessage() );
		    	throw new NotificationException(e.getMessage());
		        
		    }
			
			
			 
	}

 
	}

 
 


}
