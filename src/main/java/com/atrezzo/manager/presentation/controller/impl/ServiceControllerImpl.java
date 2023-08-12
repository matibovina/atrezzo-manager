package com.atrezzo.manager.presentation.controller.impl;


import com.atrezzo.manager.application.dto.ServiceDTO;
import com.atrezzo.manager.application.dto.WorkerDTO;
import com.atrezzo.manager.application.service.ServiceService;
import com.atrezzo.manager.domain.model.enums.ServiceCategory;
import com.atrezzo.manager.domain.model.enums.ServiceType;
import com.atrezzo.manager.presentation.controller.ServiceController;
import com.atrezzo.manager.presentation.exception.CustomIllegalArgumentException;
import com.atrezzo.manager.presentation.exception.NoClassFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/services", produces = MediaType.APPLICATION_JSON_VALUE)
public class ServiceControllerImpl implements ServiceController {

    private final ServiceService service;


    @Override
    @PostMapping
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceDTO serviceDTO) throws NoClassFoundException, CustomIllegalArgumentException {
         return new ResponseEntity<>(service.createService(serviceDTO), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("service/{id}")
    public ResponseEntity<ServiceDTO> findServiceById(@PathVariable("id") Long serviceId) throws NoClassFoundException, CustomIllegalArgumentException  {
        return new ResponseEntity<>(service.getServiceById(serviceId), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ServiceDTO>> getAllServices() throws NoClassFoundException, CustomIllegalArgumentException  {
        return new ResponseEntity<>(service.getServices(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/workers/id/{id}")
    public ResponseEntity<List<WorkerDTO>> findWorkersByServiceId(@PathVariable("id") Long serviceId) throws NoClassFoundException, CustomIllegalArgumentException  {
        return new ResponseEntity<>(service.getWorkersByService(serviceId), HttpStatus.OK);

    }

    @Override
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ServiceDTO>> findServicesByCategory(@PathVariable ServiceCategory category) throws NoClassFoundException, CustomIllegalArgumentException  {
        return new ResponseEntity<>(service.getServicesByCategory(category), HttpStatus.OK);
    }

    @Override
    @GetMapping("/category/{category}/type/{type}")
    public ResponseEntity<List<ServiceDTO>> findServicesByCategoryAndType(@PathVariable ServiceCategory category,@PathVariable ServiceType type) throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(service.getServicesByCategoryAndType(category, type), HttpStatus.OK);

    }

    @Override
    @GetMapping("/type/{type}")
    public ResponseEntity<List<ServiceDTO>> findServicesByType(@PathVariable ServiceType type) throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(service.getServicesByType(type), HttpStatus.OK);

    }

    @Override
    @PutMapping()
    public ResponseEntity<ServiceDTO> updateService(@RequestBody ServiceDTO serviceDTO) throws NoClassFoundException, CustomIllegalArgumentException  {
        return new ResponseEntity<>(service.updateService(serviceDTO), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("service/{id}")
    public ResponseEntity<?> deleteService(@PathVariable("id") Long serviceId) throws NoClassFoundException, CustomIllegalArgumentException  {
        service.deleteService(serviceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
