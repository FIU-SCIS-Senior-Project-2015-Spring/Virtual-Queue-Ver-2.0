package com.virtual.queue.service;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtual.queue.beans.NotificationInfo;
import com.virtual.queue.beans.UserQueueInfo;
import com.virtual.queue.dao.QueueDao;
import com.virtual.queue.dao.QueueDaoImp;
import com.virtual.queue.handler.EmailNotificationHandlerImp;
import com.virtual.queue.handler.NotificationHandler;

@Service
public class NotificationServiceImp implements NotificationService {
	public static String NOTIFICATION_MSG = "Your ride is due in : ";

	@Autowired
	QueueDao queueDao;
 

	public NotificationServiceImp() {
		 
	}

	@Override
	public List<UserQueueInfo> pullNotInfo(long rideId) {
		QueueDao dao = new QueueDaoImp();
		return dao.pullInfo(rideId);
	}

	@Override
	public void notifyUser(long rideId) throws Exception {

		List<UserQueueInfo> list = this.pullNotInfo(rideId);

		NotificationHandler handler = new EmailNotificationHandlerImp();

		if (!list.isEmpty()) {
			// throw new Exception("Empty user list");

			NotificationInfo Notinfo = null;
			double timeMin = 0;
			for (UserQueueInfo info : list) {

				Notinfo = new NotificationInfo();
				Notinfo.setEmail(info.getEmail());
				Notinfo.setName(info.getName());
				timeMin = info.getInterval() / 60.0;
				Notinfo.setMessage("Your " +info.getRideName()+" ride is due in :"
						+ timeMin
						+ "minute/s, Please, try to be on time and Enjoy your Ride :) ");

				handler.notifiyUser(Notinfo);

				System.out.println("Send notification message to user  :"
						+ info.getEmail());
			}

			System.out.println("finished sending  email message to user list");
		}
	}

	@Override
	public void notifyAllUsers() throws Exception {

		List<UserQueueInfo> list = this.pullAllNotInfo();

		NotificationHandler handler = new EmailNotificationHandlerImp();

		if (list.isEmpty())
			throw new Exception("Empty user list");

		NotificationInfo Notinfo = null;

		for (UserQueueInfo info : list) {

			Notinfo = new NotificationInfo();
			Notinfo.setEmail(info.getEmail());
			Notinfo.setName(info.getName());
			Notinfo.setMessage(NOTIFICATION_MSG
					+ "1 minute, Please, try to be on time and Enjoy your Ride :) ");

			handler.notifiyUser(Notinfo);

			System.out.println("Send notification message to user  :"
					+ info.getEmail());
		}

		System.out.println("finished sending  email message to user list");

	}

	@Override
	public List<UserQueueInfo> pullAllNotInfo() {
		// DI is not working.
		QueueDao dao = new QueueDaoImp();
		return dao.pullAllInfo();
	}
	
	@Override
	public void sendSingleNotificationDequeued(String visitorName, String visitorEmail, String rideName, int timeToNotify) throws Exception{
		NotificationHandler handler = new EmailNotificationHandlerImp();
		NotificationInfo notInfo = new NotificationInfo();
		// set data
		notInfo.setEmail(visitorEmail);
		notInfo.setName(visitorName);
		//notInfo.setEmail(user.getEmail());
		
		notInfo.setMessage("Notification! Hello " +visitorName+ " in about " +timeToNotify+ " minutes the ride" +rideName+" will be ready for you! Please get to the entrance of the ride. Thank you!");
		// notify user
		handler.notifiyUser(notInfo);
		
		
//	      String to = visitorEmail;//change accordingly
//
//	      // Sender's email ID needs to be mentioned
//	      String from = "virtualqueue2014@yahoo.com";//change accordingly
//	      final String username = "virtualqueue2014@yahoo.com";//change accordingly
//	      final String password = "seniorproject";//change accordingly
//
//	      // Assuming you are sending email through relay.jangosmtp.net
//	      String host = "smtp.mail.yahoo.com";
//
//	      Properties props = new Properties();
//	      props.put("mail.smtp.auth", "true");
//	      props.put("mail.smtp.starttls.enable", "true");
//	      props.put("mail.smtp.host", host);
//	      props.put("mail.smtp.port", "587");
//
//	      // Get the Session object.
//	      Session session = Session.getInstance(props,
//	      new javax.mail.Authenticator() {
//	         protected PasswordAuthentication getPasswordAuthentication() {
//	            return new PasswordAuthentication(username, password);
//	         }
//	      });
//
//	      try {
//	         // Create a default MimeMessage object.
//	         Message message = new MimeMessage(session);
//
//	         // Set From: header field of the header.
//	         message.setFrom(new InternetAddress(from));
//
//	         // Set To: header field of the header.
//	         message.setRecipients(Message.RecipientType.TO,
//	         InternetAddress.parse(to));
//
//	         // Set Subject: header field
//	         message.setSubject("User notification for your ride");
//
//	         // Now set the actual message
//	         message.setText("Notification! Hello " +visitorName+ " in about " +timeToNotify+ " minutes the ride" 
//	         +rideName+" will be ready for you! Please get to the entrance of the ride. Thank you!");
//
//	         // Send message
//	         Transport.send(message);
//
//	         System.out.println("Sent message successfully....");
//
//	      } catch (MessagingException e) {
//	            throw new RuntimeException(e);
//	      }
//		
	}

}
