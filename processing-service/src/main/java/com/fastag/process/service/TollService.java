package com.fastag.process.service;

import org.springframework.stereotype.Service;

import com.fastag.process.ennum.VehicleType;

@Service
public class TollService {

	public double calculateTollAmount(VehicleType vehicleType) {
		return vehicleType.getTollAmount();
	}
	
}