package com.launchersoft.rmm.controller;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.launchersoft.rmm.dto.DeviceDTO;
import com.launchersoft.rmm.exception.ResourceAlreadyExistException;
import com.launchersoft.rmm.model.Device;
import com.launchersoft.rmm.service.DeviceService;

/**
 * @author Launchersoft
 * Controller layer for Device entity
 */
@RestController
@RequestMapping(path = "/api/v1/device", produces = APPLICATION_JSON_VALUE)
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    /**
     * Save a device.
     */
    @PostMapping
    public DeviceDTO saveDevice(@RequestBody DeviceDTO deviceDTO) {

        //Check if the device already exists
         if(deviceService.checkIfExists(deviceDTO)) {
             throw new ResourceAlreadyExistException("Device already exists");
         }

        Device device = Device.builder()
                .type(deviceDTO.getType())
                .systemName(deviceDTO.getSystemName())
                .build();
        Device deviceSaved = deviceService.save(device);

        deviceDTO.setId(deviceSaved.getId());
        return deviceDTO;
    }

    /**
     * Update a device.
     */
    @PutMapping
    public DeviceDTO updateDevice(@RequestBody DeviceDTO deviceDTO) {
        return deviceService.update(deviceDTO);
    }

    /**
     * Delete a device.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDevice(@PathVariable Long id) {
        //Gets the device by id and delete it
        Device device = deviceService.getDeviceById(id);

         deviceService.delete(device);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<DeviceDTO> getDevice(@PathVariable Long id) {
        //Gets the device by id
        Device device = deviceService.getDeviceById(id);

        DeviceDTO deviceDTO = DeviceDTO.builder()
                .id(device.getId())
                .type(device.getType())
                .systemName(device.getSystemName())
                .build();

        return new ResponseEntity<>(deviceDTO, HttpStatus.OK);
    }
}
