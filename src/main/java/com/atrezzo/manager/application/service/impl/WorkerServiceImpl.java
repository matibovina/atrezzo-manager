package com.atrezzo.manager.application.service.impl;

import com.atrezzo.manager.application.dto.WorkerDTO;
import com.atrezzo.manager.application.service.WorkerService;
import com.atrezzo.manager.domain.repository.AddressRepository;
import com.atrezzo.manager.domain.repository.WorkerRepository;
import com.atrezzo.manager.domain.model.AddressEntity;
import com.atrezzo.manager.domain.model.WorkerEntity;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkerServiceImpl implements WorkerService {


    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    public List<WorkerDTO> findAllWorkers() {
        return workerRepository.findAll()
                .stream()
                .map(worker -> modelMapper.map(worker, WorkerDTO.class))
                .collect(Collectors.toList());
    }

    public WorkerDTO findWorkerById(Long id) {
        WorkerEntity worker = workerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Worker not found with id: " + id));
        return modelMapper.map(worker, WorkerDTO.class);
    }

    public WorkerDTO findWorkerByEmail(String email) {
        WorkerEntity worker = workerRepository.findByEmail(email);
        return modelMapper.map(worker, WorkerDTO.class);
    }

    public WorkerDTO findWorkerByUsername(String username) {
        WorkerEntity worker = workerRepository.findByUserUsername(username);
        return modelMapper.map(worker, WorkerDTO.class);
    }

    public WorkerDTO createWorker(WorkerDTO workerDTO) {

        WorkerEntity worker = modelMapper.map(workerDTO, WorkerEntity.class);

        if(workerDTO.getAddress() != null) {
            AddressEntity newAddress = worker.getAddress();
            addressRepository.save(newAddress);
            worker.setAddress(newAddress);
        }
        WorkerEntity savedWorker = workerRepository.save(worker);
        return modelMapper.map(savedWorker, WorkerDTO.class);
    }

    public WorkerDTO updateWorker(Long id, WorkerDTO workerDTO) {
        WorkerEntity existingWorker = workerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Worker not found with id: " + id));

        modelMapper.map(workerDTO, existingWorker);

        if(workerDTO.getAddress() != null) {
            AddressEntity updatedAddress = existingWorker.getAddress();
            addressRepository.save(updatedAddress);
        }

        WorkerEntity savedWorker = workerRepository.save(existingWorker);
        return modelMapper.map(savedWorker, WorkerDTO.class);
    }

    public void deleteWorker(Long id) {
        if (!workerRepository.existsById(id)) {
            throw new EntityNotFoundException("Worker not found with id: " + id);
        }
        workerRepository.deleteById(id);
    }


}
