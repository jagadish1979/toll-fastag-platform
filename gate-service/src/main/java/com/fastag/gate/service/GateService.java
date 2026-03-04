package com.fastag.gate.service;

import org.springframework.stereotype.Service;

import com.fastag.common.dto.GateRequest;
import com.fastag.gate.client.RestGateHardwareClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GateService {

    private RestGateHardwareClient hardwareClient;

    public boolean openGate(String laneId, String vehicleNumber) {

        GateRequest request = GateRequest.builder()
                .laneId(laneId)
                .vehicleNumber(vehicleNumber)
                .action("OPEN")
                .build();

        return hardwareClient.sendCommand(request);
    }

    public boolean closeGate(String laneId) {

        GateRequest request = GateRequest.builder()
                .laneId(laneId)
                .action("CLOSE")
                .build();

        return hardwareClient.sendCommand(request);
    }


}
