package com.atrezzo.manager.presentation.controller;

import com.atrezzo.manager.application.dto.ClientDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

public interface ClientController {

    ResponseEntity<?> createClient(ClientDTO clientDTO);

    ResponseEntity<?> findAllClients();

    ResponseEntity<?> findClientById(Long id);

    ResponseEntity<?> findClientByCuit(String cuitNumber);

    ResponseEntity<?> findClientByLegalName(String legalName);

    ResponseEntity<?> findClientByCompanyName(String companyName);

    ResponseEntity<?> updateClient(ClientDTO clientDTO) throws JsonProcessingException;

    ResponseEntity<?> deleteClientById(Long id);

    ResponseEntity<?> updateProfilePicture(Long id, MultipartFile file);

    ResponseEntity<?> getProfilePicture(Long id);
}
