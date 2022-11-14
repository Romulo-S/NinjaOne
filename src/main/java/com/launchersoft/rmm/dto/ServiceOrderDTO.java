package com.launchersoft.rmm.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * DTO for calculate services ordered by the client
 * @author launchersoft
 * @version 1.0
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
public class ServiceOrderDTO {

     private long idService;
     private int quantity;
     private BigDecimal totalCost;
}
