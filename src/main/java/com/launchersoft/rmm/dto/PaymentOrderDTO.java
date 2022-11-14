package com.launchersoft.rmm.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Pay order required by the client
 * @author launchersoft
 * @version 1.0
 * @since 1.0
 * @see com.launchersoft.rmm.dto.ServiceOrderDTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOrderDTO {

    private List<ServiceOrderDTO> servicesRequired;
    private BigDecimal totalCost;
}
