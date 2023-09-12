package com.atrezzo.manager.application.service;

import com.atrezzo.manager.application.dto.EventSessionDTO;
import com.atrezzo.manager.application.dto.ServiceDTO;
import com.atrezzo.manager.application.dto.ServiceWorkerPriceDTO;
import com.atrezzo.manager.application.dto.WorkerDTO;
import com.atrezzo.manager.domain.model.EventSessionEntity;
import com.atrezzo.manager.domain.model.ServiceWorkerPriceEntity;
import com.atrezzo.manager.domain.model.enums.ServiceCategory;
import com.atrezzo.manager.domain.model.enums.ServiceType;

import java.util.List;

public interface ServiceService {

    ServiceDTO createService(ServiceDTO serviceDTO);

    List<ServiceDTO> getServices();

    ServiceDTO getServiceById(Long serviceId);

    List<WorkerDTO> getWorkersByService (Long serviceId);

    List<ServiceDTO> getServicesByCategory(ServiceCategory category);

    List<ServiceDTO> getServicesByCategoryAndType(ServiceCategory category, ServiceType type);

    List<ServiceDTO> getServicesByType(ServiceType type);

    List<EventSessionDTO> getEventsByService(Long serviceId);

    ServiceDTO updateService (ServiceDTO serviceDTO);

    void deleteService (Long id);

}
