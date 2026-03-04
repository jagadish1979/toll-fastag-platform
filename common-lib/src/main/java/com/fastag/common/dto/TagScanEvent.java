package com.fastag.common.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(callSuper=false)
public class TagScanEvent extends KafkaEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private String laneId;

	private long timeStamp;
	
}
