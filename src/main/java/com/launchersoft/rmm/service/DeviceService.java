package com.launchersoft.rmm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.launchersoft.rmm.dto.DeviceDTO;
import com.launchersoft.rmm.exception.ResourceNotFoundException;
import com.launchersoft.rmm.model.Device;
import com.launchersoft.rmm.repository.DeviceRepository;

/**
 * @author Launchersoft
 * Service layer for Device
 */
@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    /**
     * Get a device by id.
     */
    public Device getDeviceById(long id) {
        return deviceRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Device not exist with id: " + id));
    }

    /**
     * Delete a device.
     * @param device
     * @return
     */
    public void delete(Device device) {
        deviceRepository.delete(device);
    }

    /**
     * Save a device.
     */
    public Device save(Device device) {
        return deviceRepository.save(device);
    }

    /**
     * Update a device.
     */
    public DeviceDTO update(DeviceDTO device) {
        Device deviceToUpdate = Device.builder()
            .id(device.getId())
            .systemName(device.getSystemName())
            .type(device.getType())
            .build();
         deviceRepository.save(deviceToUpdate);

         return device;
    }

    /**
     * Check if a device already exists.
     * @param deviceDTO
     * @return true if exists, false otherwise
     */

    public boolean checkIfExists(DeviceDTO deviceDTO) {
        return deviceRepository.existsBySystemNameAndType(deviceDTO.getSystemName(), deviceDTO.getType());
    }
}
