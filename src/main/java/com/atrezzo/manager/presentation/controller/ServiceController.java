package com.atrezzo.manager.presentation.controller;

import com.atrezzo.manager.application.dto.ServiceDTO;
import com.atrezzo.manager.application.dto.WorkerDTO;
import com.atrezzo.manager.domain.model.enums.ServiceCategory;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceController {

    ResponseEntity<ServiceDTO> createService(ServiceDTO serviceDTO);

    ResponseEntity<ServiceDTO> findServiceById(Long serviceId);

    ResponseEntity<List<ServiceDTO>> getAllServices();

    ResponseEntity<List<WorkerDTO>> findWorkersByServiceId(Long serviceId);

    ResponseEntity<List<ServiceDTO>> findServicesByCategory(ServiceCategory category);

    ResponseEntity<ServiceDTO> updateService(ServiceDTO serviceDTO);

    ResponseEntity<?> deleteService(Long serviceId);

}
