package com.atrezzo.manager.presentation.controller.impl;

import com.atrezzo.manager.application.dto.ClientDTO;
import com.atrezzo.manager.application.service.ClientService;
import com.atrezzo.manager.application.service.FileStorageService;
import com.atrezzo.manager.infrastructure.exceptions.NoClientsFoundException;
import com.atrezzo.manager.presentation.controller.ClientController;
import com.atrezzo.manager.presentation.exception.CustomIllegalArgumentException;
import com.atrezzo.manager.presentation.exception.NoClassFoundException;
import com.atrezzo.manager.presentation.responses.AtrezzoResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
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
@RequestMapping(value = "/api/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientControllerImpl implements ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private FileStorageService fileStorageService;

    private ModelMapper modelMapper = new ModelMapper();

    private final Logger logger= LoggerFactory.getLogger(ClientControllerImpl.class);

    @Override
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(clientService.createClient(clientDTO), HttpStatus.CREATED);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientDTO>> findAllClients()  throws NoClassFoundException, CustomIllegalArgumentException{
        return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "client/{id}")
    public ResponseEntity<ClientDTO> findClientById(@PathVariable Long id)  throws NoClassFoundException, CustomIllegalArgumentException{
        return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
    }

    @Override
    @GetMapping("/client/cuit/{cuitNumber}")
    public ResponseEntity<ClientDTO> findClientByCuit(@PathVariable String cuitNumber) throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(clientService.findClientByCuit(cuitNumber), HttpStatus.OK);
    }

    @Override
    @GetMapping("/client/legalName/{legalName}")
    public ResponseEntity<ClientDTO> findClientByLegalName(String legalName) throws NoClassFoundException, CustomIllegalArgumentException{
        return new ResponseEntity<>(clientService.findClientByLegalName(legalName), HttpStatus.OK);
    }

    @Override
    @GetMapping("/client/companyName/{companyName}")
    public ResponseEntity<ClientDTO> findClientByCompanyName(String companyName) {
        return new ResponseEntity<>(clientService.findClientByCompanyName(companyName), HttpStatus.OK);
    }

    @Override
    @PutMapping(value = "/client")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO clientDTO) throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(clientService.updateClient(clientDTO), HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteClientById(Long id) throws NoClassFoundException, CustomIllegalArgumentException {
        clientService.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/client/{id}/profilePicture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProfilePicture(@PathVariable Long id, @RequestPart("file") MultipartFile file) {
        try {
            ClientDTO client = clientService.findById(id);
            if (client == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
            }

            String profilePicturePath = fileStorageService.storeFile(file, "client/profile_pictures", client.getId());
            client.setProfilePicture(profilePicturePath);

            clientService.updateClient(client);

            return ResponseEntity.ok().body("Profile picture saved with success to " + client.getCompanyName() + "picture name " + client.getProfilePicture());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the profile picture");
        }
    }

    @GetMapping("/client/{id}/profilePicture")
    public ResponseEntity<?> getProfilePicture(@PathVariable Long id) {
        try {
            ClientDTO client = clientService.findById(id);
            if (client == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
            }

            String profilePicturePath = client.getProfilePicture();
            Resource profilePicture = fileStorageService.loadFileAsResource(profilePicturePath, "client/profile_pictures");

            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(profilePicture);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while retrieving the profile picture");
        }
    }

}
