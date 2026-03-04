package com.fastag.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gate-service", url = "http://localhost:8085")
public interface GateClient {

    @PostMapping("/gate/open")
    boolean openGate(@RequestParam String laneId,
            @RequestParam String vehicleNumber);
}
