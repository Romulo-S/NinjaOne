package com.launchersoft.rmm.model;

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
 * Device entity
 */
@Entity
@Table(name = "device")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "systemName", length = 50, nullable = false)
    private String systemName;
    @Column(name = "type", length = 50, nullable = false)
    private String type;

}
