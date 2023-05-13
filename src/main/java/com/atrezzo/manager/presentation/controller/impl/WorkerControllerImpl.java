package com.atrezzo.manager.presentation.controller.impl;

import com.atrezzo.manager.application.dto.WorkerDTO;
import com.atrezzo.manager.application.service.WorkerService;
import com.atrezzo.manager.presentation.controller.WorkerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkerControllerImpl implements WorkerController {


    @Autowired
    private WorkerService workerService;


    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkerDTO> createWorker(WorkerDTO workerDTO) {
        try {
            WorkerDTO savedWorker = workerService.createWorker(workerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedWorker);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WorkerDTO>> findAll() {
        try {
            List<WorkerDTO> workers = workerService.findAllWorkers();
            return ResponseEntity.ok(workers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkerDTO> findById(Long id) {
        try {
            WorkerDTO worker = workerService.findWorkerById(id);
            return ResponseEntity.ok(worker);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    @GetMapping(value = "/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkerDTO> findByUserUsername(String username) {
        try {
            WorkerDTO worker = workerService.findWorkerByUsername(username);
            return ResponseEntity.ok(worker);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    @GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkerDTO> findByEmail(String email) {
        try {
            WorkerDTO worker = workerService.findWorkerByEmail(email);
            return ResponseEntity.ok(worker);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkerDTO> updateWorker(Long id, WorkerDTO workerDTO) {
        try {
            WorkerDTO updatedWorker = workerService.updateWorker(id, workerDTO);
            return ResponseEntity.ok(updatedWorker);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteWorker(Long id) {
        try {
            workerService.deleteWorker(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while trying to delete worker");
        }
    }
}
