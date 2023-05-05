package com.atrezzo.manager.presentation.controller.impl;

import com.atrezzo.manager.application.dto.ClientDTO;
import com.atrezzo.manager.application.service.ClientService;
import com.atrezzo.manager.domain.model.Client;
import com.atrezzo.manager.infrastructure.exceptions.NoClientsFoundException;
import com.atrezzo.manager.presentation.controller.ClientController;
import com.atrezzo.manager.presentation.responses.AtrezzoResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/clients")
public class ClientControllerImpl implements ClientController {

    @Autowired
    private ClientService clientService;

    private ModelMapper modelMapper = new ModelMapper();

    private final Logger logger= LoggerFactory.getLogger(ClientControllerImpl.class);

    @Override
    @PostMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createClient(@RequestBody ClientDTO clientDTO) {


        try {
            if (clientDTO == null || clientDTO.getCuitNumber() == null) {
                AtrezzoResponse response = new AtrezzoResponse(HttpStatus.CREATED.value(), "Client or client cuit cannot be null.", null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            Client client = modelMapper.map(clientDTO, Client.class);

            ClientDTO createdClient;
            Client saveClient;
            saveClient = clientService.createClient(client);
            createdClient = modelMapper.map(saveClient, ClientDTO.class);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error ocurred while saving the client");
        }
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllClients() {
        try {
            List<Client> clients = clientService.findAll();
            List<ClientDTO> clientsDTO = clients.stream().map(
                    client-> modelMapper.map(client, ClientDTO.class)
            ).collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(clientsDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error has occurred while trying to get all clients");
        }
    }

    @Override
    @GetMapping(value = "client/{id}")
    public ResponseEntity<?> findClientById(@PathVariable Long id) {
        try {
            Client client = clientService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(client, ClientDTO.class));
        } catch (NoClientsFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client doesn't exist.");
        }
    }

    @Override
    @GetMapping(value = "/client/cuit/{cuitNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findClientByCuit(@PathVariable String cuitNumber) {
        try {
            Client client = clientService.findClientByCuit(cuitNumber);
            return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(client, ClientDTO.class));
        } catch (NoClientsFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client doesn't exist.");
        }
    }

    @Override
    @GetMapping(value = "/client/legalName/{legalName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findClientByLegalName(String legalName) {
        try {
            Client client = clientService.findClientByLegalName(legalName);
            return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(client, ClientDTO.class));
        } catch (NoClientsFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client doesn't exist.");
        }
    }

    @Override
    @GetMapping(value = "/client/companyName/{companyName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findClientByCompanyName(String companyName) {
        try {
            Client client = clientService.findClientByCompanyName(companyName);
            return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(client, ClientDTO.class));
        } catch (NoClientsFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client doesn't exist.");
        }
    }

    @Override
    @PutMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateClient(@RequestBody ClientDTO clientDTO) throws JsonProcessingException {

        try {
            Client client = clientService.updateClient(modelMapper.map(clientDTO, Client.class));
            ClientDTO updatedClient = modelMapper.map(client, ClientDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedClient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
    }

    @Override
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteClientById(Long id) {
        try {
            clientService.deleteClientById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Client with id " + id + " was successfully deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client doesn´t exists.");
        }
    }
}
