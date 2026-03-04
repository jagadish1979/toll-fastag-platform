package com.fastag.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GateRequest {

	private String laneId;
	private String vehicleNumber;
	private String action; 
	
}
