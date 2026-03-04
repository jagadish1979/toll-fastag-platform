package com.fastag.ingestion.service;

import org.springframework.stereotype.Service;

import com.fastag.common.dto.TagScanEvent;
import com.fastag.common.service.KafkaService;


@Service
public class TagService {

	private KafkaService kafkaService;
	
	public TagService(KafkaService kafkaService) {
		this.kafkaService = kafkaService;
	}

	public String scanTag(TagScanEvent event) {
		kafkaService.publishMessage("tag-scan-topic", event);
		return "Event Published";
	}

}
