package com.fastag.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fastag.notification.dto.NotificationRequest;
import com.fastag.notification.proxy.NotificationProxy;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor
public class NotificationController {

	private final NotificationProxy notificationProxy;
	
	@PostMapping("/sendNotification")
	public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request){
		notificationProxy.sendNotification(request.getType(), request.getRecipient(), request.getMessage());
        return ResponseEntity.ok("Notification sent successfully");

	}
	
}
