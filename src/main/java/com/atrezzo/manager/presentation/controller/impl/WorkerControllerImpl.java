package com.atrezzo.manager.presentation.controller.impl;

import com.atrezzo.manager.application.dto.WorkerDTO;
import com.atrezzo.manager.application.service.FileStorageService;
import com.atrezzo.manager.application.service.WorkerService;
import com.atrezzo.manager.presentation.controller.WorkerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/workers")
public class WorkerControllerImpl implements WorkerController {


    @Autowired
    private WorkerService workerService;

    @Autowired
    private FileStorageService fileStorageService;

    private final Logger log = LoggerFactory.getLogger(WorkerControllerImpl.class);


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

    @PostMapping(value = "/worker/{id}/profilePicture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProfilePicture(@PathVariable Long id, @RequestPart("file") MultipartFile file) {
        try {
            WorkerDTO worker = workerService.findWorkerById(id);
            if (worker == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Worker not found");
            }

            String profilePicturePath = fileStorageService.storeFile(file, "worker/profile_pictures", worker.getId());
            worker.setProfilePicture(profilePicturePath);

            workerService.updateWorker(worker.getId(), worker);

            return ResponseEntity.ok().body("Profile picture saved with success to " + worker.getFirstName() + "picture name " + worker.getProfilePicture());
        } catch (Exception e) {
            log.error("Ocurrio un error " + e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the profile picture");
        }
    }

    @GetMapping("/worker/{id}/profilePicture")
    public ResponseEntity<?> getProfilePicture(@PathVariable Long id) {
        try {
            WorkerDTO worker = workerService.findWorkerById(id);
            if (worker == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Worker not found");
            }

            String profilePicturePath = worker.getProfilePicture();
            Resource profilePicture = fileStorageService.loadFileAsResource(profilePicturePath, "worker/profile_pictures");
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(profilePicture);
        } catch (Exception e) {
            log.error("Ocurrio un error " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while retrieving the profile picture");
        }
    }
}
