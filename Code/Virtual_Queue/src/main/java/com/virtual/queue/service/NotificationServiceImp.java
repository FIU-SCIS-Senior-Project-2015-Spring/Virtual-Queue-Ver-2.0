package com.virtual.queue.service;

import java.util.List;

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

}
