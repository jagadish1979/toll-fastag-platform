package com.fastag.hardware.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fastag.common.dto.GateRequest;

@RestController
@RequestMapping("/hardware")
public class HardwareController {

	@PostMapping("/gate/control")
    public void open(@RequestBody GateRequest request) {

       if("OPEN".equalsIgnoreCase(request.getAction())){
    	   System.out.print("Gate Opened For The Vehicle Number [ " + request.getVehicleNumber() + " ] In The Lane ["+request.getLaneId()+"]");
       }
       else if("CLOSE".equalsIgnoreCase(request.getAction())){
    	   System.out.print("Gate Closed For The Vehicle Number [ " + request.getVehicleNumber() + " ] In The Lane ["+request.getLaneId()+"]");
       }
       else {
    	   System.err.print("Unknown Action !");
       }
    }
}
