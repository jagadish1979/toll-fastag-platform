package com.fastag.payment.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastag.payment.service.PaymentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController {

    private PaymentService service;

	@PostMapping("/preauth")
    public boolean preAuth(@RequestParam String tagId, @RequestParam String laneId, @RequestParam double amount, String requestId) {
        return service.preAuthorize(tagId, laneId, amount, requestId);
    }
    
    
}
