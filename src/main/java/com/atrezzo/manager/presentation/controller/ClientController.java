package com.atrezzo.manager.presentation.controller;

import com.atrezzo.manager.application.dto.ClientDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClientController {

    ResponseEntity<ClientDTO> createClient(ClientDTO clientDTO);

    ResponseEntity<List<ClientDTO>> findAllClients();

    ResponseEntity<ClientDTO> findClientById(Long id);

    ResponseEntity<ClientDTO> findClientByCuit(String cuitNumber);

    ResponseEntity<ClientDTO> findClientByLegalName(String legalName);

    ResponseEntity<ClientDTO> findClientByCompanyName(String companyName);

    ResponseEntity<ClientDTO> updateClient(ClientDTO clientDTO) throws JsonProcessingException;

    ResponseEntity<?> deleteClientById(Long id);

    ResponseEntity<?> updateProfilePicture(Long id, MultipartFile file);

    ResponseEntity<?> getProfilePicture(Long id);
}
