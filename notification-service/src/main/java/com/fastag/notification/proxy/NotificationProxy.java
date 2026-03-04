package com.fastag.notification.proxy;

import org.springframework.stereotype.Component;

import com.fastag.notification.factory.NotificationFactory;
import com.fastag.notification.service.NotificationService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class NotificationProxy {

	private final NotificationFactory factory;
	
	public void sendNotification(String type, String recipient, String message) {

       validate(recipient, message);
       log(type, message);

       NotificationService service = factory.getNotificationService(type);
       service.sendNotification(recipient, message);
       
	}

	private void validate(String recipient, String message) {
		
		if (recipient == null || recipient.isEmpty()) {
            throw new IllegalArgumentException("Recipient cannot be empty");
        }
		
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be empty");
        }
        
	}
	
	 private void log(String type, String recipient) {
        System.out.println("Notification request => Type: "
                + type + ", Recipient: " + recipient);
    }
}
