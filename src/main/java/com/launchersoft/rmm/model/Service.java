package com.launchersoft.rmm.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Launchersoft
 * Service entity
 */
@Entity
@Table(name = "service")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "serviceName", length = 50, nullable = false)
    private String serviceName;
    @Column(name = "serviceDescription", nullable = false)
    private String serviceDescription;
    @Column(name = "cost", nullable = false)
    private BigDecimal cost;
}
