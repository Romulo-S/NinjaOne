package com.launchersoft.rmm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.launchersoft.rmm.model.Device;

/**
 * @author Launchersoft
 * Device repository
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device,Long> {
    boolean existsBySystemNameAndType(String systemName, String type);
}
