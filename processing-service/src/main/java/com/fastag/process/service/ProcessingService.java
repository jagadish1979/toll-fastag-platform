package com.fastag.process.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fastag.common.dto.TagScanEvent;
import com.fastag.common.entity.Tag;
import com.fastag.common.service.VehicleTagService;
import com.fastag.process.client.PaymentClient;
import com.fastag.process.ennum.VehicleType;

@Service
public class ProcessingService {

	private VehicleTagService tagService;
	private TollService tollService;
	private PaymentClient paymentClient;

	public ProcessingService(VehicleTagService tagService, TollService tollService, PaymentClient paymentClient) {
		this.tagService = tagService;
		this.tollService = tollService;
		this.paymentClient = paymentClient;
	}


	public void process(TagScanEvent event) {

		Tag tag = tagService.getVehicleTag(event.getTagId());

		if (!"ACTIVE".equals(tag.getStatus())) {
			System.out.println("Invalid Tag !");
			return;
		}

		String requestId = UUID.randomUUID().toString();

		VehicleType type = VehicleType.from(tag.getVehicleType());

		double amount = tollService.calculateTollAmount(type);

		System.out.println("Tag===> "+tag.toString());
        boolean success = paymentClient.preAuthorize(
                tag.getTagId(),
                event.getLaneId(),
                amount,
                requestId
        );
        
        if (success) {
            System.out.println("Proceed to Gate !");
        } else {
            System.out.println("Tag Error. Required Manual Intervention !");
        }

	}
}
