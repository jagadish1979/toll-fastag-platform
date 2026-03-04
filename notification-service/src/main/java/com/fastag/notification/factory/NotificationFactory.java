package com.fastag.notification.factory;

import org.springframework.stereotype.Component;

import com.fastag.notification.service.EmailNotificationService;
import com.fastag.notification.service.NotificationService;
import com.fastag.notification.service.SMSNotificationService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class NotificationFactory {

	private final SMSNotificationService smsService;
	
	private final EmailNotificationService emailService;
	
	public NotificationService getNotificationService(String type) {
		switch(type) {
		case "SMS" : return smsService;
		case "EMAIL" : return emailService;
		default : throw new IllegalArgumentException("Unsupported notification type");
		}
	}
}
