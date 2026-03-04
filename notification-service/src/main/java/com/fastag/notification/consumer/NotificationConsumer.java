package com.fastag.notification.consumer;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fastag.common.dto.PaymentStatusEvent;
import com.fastag.notification.proxy.NotificationProxy;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificationConsumer {

	private final NotificationProxy notificationProxy;
	
    @KafkaListener(topics = "payment-status-topic", groupId = "notification-group")
    public void consume(PaymentStatusEvent event) {

    	String message = "Dear Customer, Your FASTag linked to Vehicle No. " + event.getVehicleNumber() + 
    			         " has been debited with INR " + event.getAmount() + 
    			         " on " + currentDateTime() + " for Toll Charges at ABC toll plaza. Available Balance: INR " + event.getBalanceAmount() + ".";

        notificationProxy.sendNotification(event.getNotificationType(), event.getRecepient(), message);
        
    }
    
    private String currentDateTime() {
    	String pattern = "dd-MM-yyyy HH:mm:ss";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date currentDate = new Date();
        String formattedDate = simpleDateFormat.format(currentDate);

        System.out.println("Formatted Date: " + formattedDate);
        
        return formattedDate;
    }
}
