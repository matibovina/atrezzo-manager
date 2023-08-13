package com.atrezzo.manager.application.service.impl;

import com.atrezzo.manager.application.dto.EventSessionDTO;
import com.atrezzo.manager.application.dto.ServiceDTO;
import com.atrezzo.manager.application.dto.WorkerDTO;
import com.atrezzo.manager.application.service.ServiceService;
import com.atrezzo.manager.domain.model.ServiceEntity;
import com.atrezzo.manager.domain.model.enums.ServiceCategory;
import com.atrezzo.manager.domain.model.enums.ServiceType;
import com.atrezzo.manager.domain.repository.ServiceRepository;
import com.atrezzo.manager.infrastructure.exceptions.NoClientsFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public ServiceDTO createService(ServiceDTO serviceDTO) {

        if(serviceDTO == null || serviceDTO.getTitle() == null) {
            throw new IllegalArgumentException("Service can't be null or undefined.");
        }
        ServiceEntity service = modelMapper.map(serviceDTO, ServiceEntity.class);
        try {
            serviceRepository.save(service);
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        return modelMapper.map(service, ServiceDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ServiceDTO> getServices() {
        return serviceRepository.findAll().stream().map(
                s -> modelMapper.map(s, ServiceDTO.class)
        ).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public ServiceDTO getServiceById(Long serviceId) {
        if(serviceId == null) {
            throw new IllegalArgumentException("Id can't be null");
        }
        ServiceEntity service = serviceRepository.findById(serviceId).orElseThrow(
                ()-> new NoClientsFoundException("Service with id " + serviceId + " doesn't exists.")
        );
        return modelMapper.map(service, ServiceDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<WorkerDTO> getWorkersByService(Long serviceId) {
        if(serviceId == null) {
            throw new IllegalArgumentException("Id can't be null");
        }
        return serviceRepository.findWorkersByServiceId(serviceId).stream().map(
                w -> modelMapper.map(w, WorkerDTO.class)
        ).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ServiceDTO> getServicesByCategory(ServiceCategory category) {
        if(category == null) {
            throw new IllegalArgumentException("Category can't be null");
        }

        return serviceRepository.findByCategory(category)
                .stream().map(serviceEntity -> modelMapper.map(serviceEntity, ServiceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceDTO> getServicesByCategoryAndType(ServiceCategory category, ServiceType type) {
        if(category == null || type == null) {
            throw new IllegalArgumentException("Category can't be null");
        }

        return serviceRepository.findServicesByCategoryAndType(category, type)
                .stream().map(serviceEntity -> modelMapper.map(serviceEntity, ServiceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceDTO> getServicesByType(ServiceType type) {
        if(type == null) {
            throw new IllegalArgumentException("Category can't be null");
        }

        return serviceRepository.findServicesByType(type)
                .stream().map(serviceEntity -> modelMapper.map(serviceEntity, ServiceDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<EventSessionDTO> getEventsByService(Long serviceId) {
        return null;
    }

    @Override
    @Transactional
    public ServiceDTO updateService(ServiceDTO serviceDTO) {
        if (serviceDTO == null || serviceDTO.getId() == null) {
            throw new IllegalArgumentException("Service or service id can't be null");
        }

        ServiceEntity updatedService = modelMapper.map(serviceDTO, ServiceEntity.class);
        serviceRepository.save(updatedService);

        return modelMapper.map(updatedService, ServiceDTO.class);
    }

    @Override
    @Transactional
    public void deleteService(Long serviceId) {
        if(serviceId == null) {
            throw new IllegalArgumentException("Id can't be null");
        }
        serviceRepository.deleteById(serviceId);
    }
}
