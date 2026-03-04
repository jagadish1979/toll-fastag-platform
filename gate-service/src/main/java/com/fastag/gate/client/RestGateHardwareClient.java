package com.fastag.gate.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fastag.common.dto.GateRequest;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RestGateHardwareClient {

    private RestTemplate restTemplate;

    private static final String HARDWARE_URL = "http://localhost:9090/hardware/gate/control";

    public boolean sendCommand(GateRequest request) {

        try {
            String response = restTemplate.postForObject(HARDWARE_URL, request, String.class);
            return "SUCCESS".equalsIgnoreCase(response);

        } catch (Exception e) {
            System.err.println("❌ Gate hardware call failed: " + e.getMessage());
            return false;
        }
    }
}