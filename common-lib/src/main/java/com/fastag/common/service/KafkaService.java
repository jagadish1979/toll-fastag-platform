package com.fastag.common.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fastag.common.config.KafkaProducerConfig;
import com.fastag.common.dto.KafkaEvent;

@Service
public class KafkaService {

	private KafkaTemplate<String, KafkaEvent> kafkaTemplate;

	private KafkaProducerConfig config;
	
	public KafkaService(KafkaProducerConfig config) {
		this.kafkaTemplate = config.kafkaTemplate();
	}

	public String publishMessage(String topicName, KafkaEvent event) {
		kafkaTemplate.send(topicName, event.getTagId(), event);
		return "Event Published";
	}
}
