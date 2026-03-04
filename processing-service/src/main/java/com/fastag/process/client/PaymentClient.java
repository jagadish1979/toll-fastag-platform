package com.fastag.process.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payment-service", url = "http://localhost:8083")
public interface PaymentClient {

    @PostMapping("/payment/preauth")
    boolean preAuthorize(@RequestParam String tagId,
    					 @RequestParam String laneId,
                         @RequestParam double amount,
                         @RequestParam String requestId);
}
