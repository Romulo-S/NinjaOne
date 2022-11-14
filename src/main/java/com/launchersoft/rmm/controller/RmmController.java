package com.launchersoft.rmm.controller;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.launchersoft.rmm.dto.PaymentOrderDTO;
import com.launchersoft.rmm.dto.ServiceDTO;
import com.launchersoft.rmm.dto.ServiceOrderDTO;
import com.launchersoft.rmm.exception.ResourceAlreadyExistException;
import com.launchersoft.rmm.model.Service;
import com.launchersoft.rmm.service.RmmService;

/**
 * @author Launchersoft
 *  * Controller layer for Service entity
 */

@RestController
@RequestMapping(path = "/api/v1/service", produces = APPLICATION_JSON_VALUE)
public class RmmController {

    @Autowired
    private RmmService rmmService;

    /**
     * Save a service.
     * @param serviceDTO
     * @return ServiceDTO
     * @throws ResourceAlreadyExistException
     */
    @PostMapping
    public ServiceDTO saveService(@RequestBody ServiceDTO serviceDTO) {

        //Check if the service already exists
        if(rmmService.checkIfExists(serviceDTO)) {
            throw new ResourceAlreadyExistException("Service already exists");
        }

        Service service = Service.builder()
                .serviceName(serviceDTO.getServiceName())
                .serviceDescription(serviceDTO.getServiceDescription())
                .cost(serviceDTO.getCost())
                .build();

        Service serviceSaved = rmmService.save(service);
        serviceDTO.setId(serviceSaved.getId());

        return serviceDTO;
    }

    /**
     * Delete a service.
     * @param id
     * @return ServiceDTO
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteService(@PathVariable Long id) {
        //Gets the service by id and delete it
        Service service = rmmService.findById(id);

        rmmService.delete(service);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Calculate the cost of a service.
     */
    @GetMapping("/calculate")
    public PaymentOrderDTO calculateService(@RequestBody List<ServiceOrderDTO> serviceOrderDTOs) {
        return rmmService.calculate(serviceOrderDTOs);
    }

}
