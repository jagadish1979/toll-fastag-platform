package com.fastag.common.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=false)
public class PaymentStatusEvent extends KafkaEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	private String vehicleNumber;
	private double amount;
	private String status;
	private String notificationType;
	private String recepient;
	private double balanceAmount;


}
