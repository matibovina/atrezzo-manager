package com.atrezzo.manager.presentation.controller;

import com.atrezzo.manager.application.dto.WorkerDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WorkerController {

    ResponseEntity<WorkerDTO> createWorker(@RequestBody WorkerDTO workerDTO);

    ResponseEntity<List<WorkerDTO>> findAll();

    ResponseEntity<WorkerDTO> findById(@PathVariable Long id);

    ResponseEntity<WorkerDTO> findByUserUsername(@PathVariable String username);

    ResponseEntity<WorkerDTO> findByEmail(@PathVariable String email);

    ResponseEntity<WorkerDTO> updateWorker(@PathVariable Long id, @RequestBody WorkerDTO workerDTO);

    ResponseEntity<?> deleteWorker(@PathVariable Long id);

    public ResponseEntity<?> updateProfilePicture(Long id, MultipartFile file);

    ResponseEntity<?> getProfilePicture(Long id);

}
