package com.fastag.payment.service;

import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fastag.common.dto.PaymentStatusEvent;
import com.fastag.common.entity.Idempotency;
import com.fastag.common.entity.Tag;
import com.fastag.common.entity.Transaction;
import com.fastag.common.service.VehicleTagService;
import com.fastag.payment.client.GateClient;
import com.fastag.payment.repository.IdempotencyRepository;
import com.fastag.payment.repository.TransactionRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentService {

	private TransactionRepository txnRepo;
	private IdempotencyRepository idRepo;
	private VehicleTagService tagService;
    private KafkaTemplate<String, PaymentStatusEvent> kafkaTemplate;
    private GateClient gateClient;
    
	@Transactional
	public boolean preAuthorize(String tagId, String laneId, double amount, String requestId) {
		  if (idRepo.existsById(requestId)) {
	            return true;
	        }

		  boolean allowed = false;
		  Tag tag = tagService.getVehicleTag(tagId);
		  
		  // balance check
		  if (tag.getBalance() >= amount) {
		        tag.setBalance(tag.getBalance() - amount);
		        allowed = true;
		        tagService.saveToRedisAndDB(tag);
		    }

	        if (!allowed) return false;

	        Transaction txn = new Transaction();
	        txn.setTxnId(UUID.randomUUID().toString());
	        txn.setTagId(tagId);
	        txn.setAmount(amount);
	        txn.setStatus("SUCCESS");

	        txnRepo.save(txn);

	        idRepo.save(new Idempotency(requestId, "DONE"));

	        PaymentStatusEvent outEvent = new PaymentStatusEvent(tag.getVehicleNumber(), amount, "SUCCESS", "SMS",
	    			"89012412345", tag.getBalance());
	        outEvent.setTagId(tagId);
	        
	        // notification call
	        kafkaTemplate.send("payment-status-topic", outEvent);
	        
	        // open gate call
	        gateClient.openGate(laneId, tag.getVehicleNumber());

	        
	        return true;
	}

}
