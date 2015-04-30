package com.virtual.queue.handler;

import java.util.HashMap;
import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Sms;
import com.virtual.queue.beans.NotificationInfo;
import com.virtual.queue.exception.NotificationException;

public class SMSNotificationHandlerImp implements NotificationHandler {

	/* Twilio REST API version */
	public static final String ACCOUNTSID = "XXXXXXXXXXXXXXXXX";
	public static final String AUTHTOKEN = "XXXXXXXXXXXXXXXXX";

	@Override
	public void notifiyUser(NotificationInfo info) {

		System.out.println("sending message to number " + info.getMessage());
		notify(ACCOUNTSID, AUTHTOKEN, info.getMessage(), info.getPhoneNumber(),
				info.getName());
	}

	private void notify(String accountId, String token, String message,
			String toNumber, String toName) {

		/* Instantiate a new Twilio Rest Client */
		TwilioRestClient client = new TwilioRestClient(accountId, token);

		// Get the account and call factory class
		Account acct = client.getAccount();
		SmsFactory smsFactory = acct.getSmsFactory();

		// build map of server admins
		Map<String, String> admins = new HashMap<String, String>();
		admins.put("4158675309", "Johnny");

		String fromNumber = "YYY-YYY-YYYY";

		// Iterate over all our server admins
		for (String toNumb : admins.keySet()) {

			// build map of post parameters
			Map<String, String> params = new HashMap<String, String>();
			params.put("From", fromNumber);
			params.put("To", toNumb);
			params.put("Body", "Bad news " + admins.get(toNumb)
					+ ", the server is down and it needs your help");

			try {
				// send an sms a call
				// ( This makes a POST request to the SMS/Messages resource)
				Sms sms = smsFactory.create(params);
				System.out.println("Success sending SMS: " + sms.getSid());
			} catch (TwilioRestException e) {
				e.printStackTrace();
			}
		}
	}

 

}
