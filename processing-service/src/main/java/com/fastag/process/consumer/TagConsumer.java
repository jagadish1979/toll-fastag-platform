package com.fastag.process.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fastag.common.dto.TagScanEvent;
import com.fastag.process.service.ProcessingService;

@Component
public class TagConsumer {

	private ProcessingService service;

	public TagConsumer(ProcessingService service) {
		this.service = service;
	}

	@KafkaListener(
		    topics = "tag-scan-topic",
		    groupId = "processing-group",
		    containerFactory = "kafkaListenerContainerFactory"
		)
		public void consume(TagScanEvent event) {
		    System.out.println("Received tag: " + event.getTagId());
	        service.process(event);
		}
}
