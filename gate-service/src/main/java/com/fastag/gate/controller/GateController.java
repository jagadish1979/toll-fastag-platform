package com.fastag.gate.controller;
import com.fastag.gate.service.GateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gate")
@RequiredArgsConstructor
public class GateController {

    private final GateService gateService;

    @PostMapping("/open")
    public String open(@RequestParam String laneId,
                       @RequestParam String vehicleNumber) {

        boolean result = gateService.openGate(laneId, vehicleNumber);
        return result ? "Gate Opened" : "Failed";
    }
    
    @PostMapping("/close")
    public String close(@RequestParam String laneId) {

        boolean result = gateService.closeGate(laneId);
        return result ? "Gate Closed" : "Failed";
    }
}