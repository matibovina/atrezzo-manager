package com.atrezzo.manager.presentation.controller;

import com.atrezzo.manager.application.dto.ClientDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface ClientController {

    ResponseEntity<?> createClient(ClientDTO clientDTO);

    ResponseEntity<?> findAllClients();

    ResponseEntity<?> findClientById(Long id);

    ResponseEntity<?> findClientByCuit(String cuitNumber);

    ResponseEntity<?> findClientByLegalName(String legalName);

    ResponseEntity<?> findClientByCompanyName(String companyName);

    ResponseEntity<?> updateClient(ClientDTO clientDTO) throws JsonProcessingException;

    ResponseEntity<?> deleteClientById(Long id);
}
