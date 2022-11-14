package com.launchersoft.rmm.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.launchersoft.rmm.dto.PaymentOrderDTO;
import com.launchersoft.rmm.dto.ServiceDTO;
import com.launchersoft.rmm.dto.ServiceOrderDTO;
import com.launchersoft.rmm.exception.ResourceNotFoundException;
import com.launchersoft.rmm.repository.ServiceRepository;

/**
 * @author Launchersoft
 * Rmm service
 */
@Service
public class RmmService {

    @Autowired
    private ServiceRepository serviceRepository;

    /**
     * Get a service by id.
     */
    public com.launchersoft.rmm.model.Service getServiceById(long id) {
        return serviceRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Service not exist with id: " + id));
    }

    /**
     * Delete a service.
     * @param service
     * @return
     */
    public void delete(com.launchersoft.rmm.model.Service service) {
        serviceRepository.delete(service);
    }

    /**
     * Save a service.
     */
    public com.launchersoft.rmm.model.Service save(com.launchersoft.rmm.model.Service service) {
        return serviceRepository.save(service);
    }

    /**
     * Update a service.
     */
    public com.launchersoft.rmm.model.Service update(com.launchersoft.rmm.model.Service service) {
        serviceRepository.save(service);
        return service;
    }

    /**
     * Check if a service already exists.
     */
    private boolean existsByName(String name) {
        return serviceRepository.existsByServiceName(name);
    }

    public boolean checkIfExists(ServiceDTO serviceDTO) {
        return existsByName(serviceDTO.getServiceName());
    }

    public com.launchersoft.rmm.model.Service findById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    public PaymentOrderDTO calculate(List<ServiceOrderDTO> serviceOrderDTOS) {
        // Calculate total cost of each service
        BigDecimal totalCost = BigDecimal.ZERO;

        for (ServiceOrderDTO serviceOrderDTO : serviceOrderDTOS) {
            com.launchersoft.rmm.model.Service service = findById(serviceOrderDTO.getIdService());
            serviceOrderDTO.setTotalCost(service.getCost().multiply(new BigDecimal(serviceOrderDTO.getQuantity())));
            totalCost = totalCost.add(serviceOrderDTO.getTotalCost());
        }

        // Calculate total cost of all services
        PaymentOrderDTO paymentOrderDTO = new PaymentOrderDTO();
        paymentOrderDTO.setServicesRequired(serviceOrderDTOS);
        paymentOrderDTO.setTotalCost(totalCost);


        return paymentOrderDTO;
    }
}
