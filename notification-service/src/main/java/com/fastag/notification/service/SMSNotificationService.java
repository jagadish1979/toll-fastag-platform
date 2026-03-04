package com.fastag.notification.service;

import org.springframework.stereotype.Service;

@Service
public class SMSNotificationService implements NotificationService {

	@Override
	public void sendNotification(String recipient, String message) {
        System.out.println("Sending SMS to " + recipient + ": " + message);
	}

}
