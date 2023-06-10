package com.atrezzo.manager.presentation.controller.impl;

import com.atrezzo.manager.application.dto.WorkerDTO;
import com.atrezzo.manager.application.service.FileStorageService;
import com.atrezzo.manager.application.service.WorkerService;
import com.atrezzo.manager.presentation.controller.WorkerController;
import com.atrezzo.manager.presentation.exception.CustomIllegalArgumentException;
import com.atrezzo.manager.presentation.exception.NoClassFoundException;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping(value = "api/workers", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkerControllerImpl implements WorkerController {

    private final WorkerService workerService;

    @Autowired
    private FileStorageService fileStorageService;

    private final Logger log = LoggerFactory.getLogger(WorkerControllerImpl.class);


    @Override
    @PostMapping
    public ResponseEntity<WorkerDTO> createWorker(WorkerDTO workerDTO) throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(workerService.createWorker(workerDTO), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<WorkerDTO>> findAll() throws NoClassFoundException, CustomIllegalArgumentException{
        return new ResponseEntity<>(workerService.findAllWorkers(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<WorkerDTO> findById(Long id) throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(workerService.findWorkerById(id), HttpStatus.OK);
    }

    @Override
    @GetMapping("/username/{username}")
    public ResponseEntity<WorkerDTO> findByUserUsername(String username) throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(workerService.findWorkerByUsername(username), HttpStatus.OK);
    }

    @Override
    @GetMapping("/email/{email}")
    public ResponseEntity<WorkerDTO> findByEmail(String email) throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(workerService.findWorkerByEmail(email), HttpStatus.OK);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<WorkerDTO> updateWorker(Long id, WorkerDTO workerDTO)
    throws NoClassFoundException, CustomIllegalArgumentException{
        return new ResponseEntity<>(workerService.updateWorker(id, workerDTO), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorker(Long id) throws NoClassFoundException, CustomIllegalArgumentException {
        workerService.deleteWorker(id);
        return new ResponseEntity<>(HttpStatus.OK);
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
