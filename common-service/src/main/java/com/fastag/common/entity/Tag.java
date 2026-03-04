package com.fastag.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "tags")
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    @Id
    private String tagId;
    
    private String vehicleNumber;
    
    private String vehicleType;
    
    private double balance;
    
    private String status;

	


    
}