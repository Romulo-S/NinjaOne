package com.launchersoft.rmm.dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Launchersoft
 * DTO for Service entity
 */
@Data
@Builder
@AllArgsConstructor
public class ServiceDTO {

    private long id;

    private String serviceName;

    private String serviceDescription;

    private BigDecimal cost;
}
