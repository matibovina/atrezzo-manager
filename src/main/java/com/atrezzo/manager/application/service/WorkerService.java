package com.atrezzo.manager.application.service;

import com.atrezzo.manager.application.dto.WorkerDTO;

import java.util.List;

public interface WorkerService {

        List<WorkerDTO> findAllWorkers();

        WorkerDTO findWorkerById(Long id);

        WorkerDTO findWorkerByEmail(String email);

        WorkerDTO findWorkerByUsername(String username);

        WorkerDTO createWorker(WorkerDTO workerDTO);

        WorkerDTO updateWorker(Long id, WorkerDTO workerDTO);

        void deleteWorker(Long id);
    }

