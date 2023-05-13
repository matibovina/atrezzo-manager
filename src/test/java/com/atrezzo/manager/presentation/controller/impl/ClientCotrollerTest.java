package com.atrezzo.manager.presentation.controller.impl;


import com.atrezzo.manager.application.dto.AddressDTO;
import com.atrezzo.manager.application.dto.ClientDTO;
import com.atrezzo.manager.application.dto.UserDTO;
import com.atrezzo.manager.application.service.impl.ClientServiceImpl;
import com.atrezzo.manager.domain.model.Client;
import com.atrezzo.manager.domain.model.User;
import com.atrezzo.manager.infrastructure.persistence.ClientEntity;
import com.atrezzo.manager.presentation.responses.AtrezzoResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientCotrollerTest {


    @Mock
    ClientServiceImpl clientService;

    @InjectMocks
    ClientControllerImpl clientController;



    ClientDTO clientDTO;

    ClientEntity clientEntity;

    AddressDTO addressDTO;

    ModelMapper modelMapper = new ModelMapper();

    User userModel;

    ObjectMapper objectMapper = new ObjectMapper();
    String clientJson = objectMapper.writeValueAsString(clientDTO);

    MultipartFile profilePicture = new MockMultipartFile("profilePicture", "test.jpg", "image/jpeg", "test-image".getBytes());

    public ClientCotrollerTest() throws JsonProcessingException {
    }


    @BeforeEach
    void setup() {


        clientDTO = new ClientDTO();
        clientDTO.setId(1L);
        clientDTO.setEmail("ma@ma.com");
        clientDTO.setPhone("1345679841");
        clientDTO.setCuitNumber("20-33894856-6");

        addressDTO = new AddressDTO();
        addressDTO.setId(1L);
        addressDTO.setStreet("Marina");
        addressDTO.setStreetNumber("145");
        addressDTO.setCity("Barcelona");
        addressDTO.setPostalCode("08013");
        addressDTO.setCountry("Spain");

        userModel = new User();
        userModel.setId(1L);
        userModel.setUsername("abcd");
        userModel.setEmail("a@asd.com");
        userModel.setPassword("password1");
        userModel.setRoles(new ArrayList<>());



        clientDTO.setUser(modelMapper.map(userModel, UserDTO.class));
        clientDTO.setAddress(addressDTO);

    }


    //Create Client
    @Test
    @DisplayName("Create Client in Controller- Success")
    void createClientTest() throws JsonProcessingException {

        Client client = modelMapper.map(clientDTO, Client.class);

        when(clientService.createClient(any(Client.class))).thenReturn(client);

        ObjectMapper objectMapper = new ObjectMapper();
        String clientJson = objectMapper.writeValueAsString(clientDTO);

        MultipartFile profilePicture = new MockMultipartFile("profilePicture", "test.jpg", "image/jpeg", "test-image".getBytes());

        ResponseEntity<?> response = clientController.createClient(clientDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody() instanceof ClientDTO);
        assertEquals(clientDTO.getId(), ((ClientDTO) response.getBody()).getId());
        assertEquals(clientDTO.getAddress().getStreet(), ((ClientDTO) response.getBody()).getAddress().getStreet());
        assertEquals(clientDTO.getCuitNumber(), ((ClientDTO) response.getBody()).getCuitNumber());

        verify(clientService, times(1)).createClient(any(Client.class));

    }

    @Test
    @DisplayName("Create Client in Controller - Null ClientDTO")
    void createClientWithNullClientDTOTest() throws JsonProcessingException {
        ClientDTO clientDTO = null;

        ResponseEntity<?> response = clientController.createClient(clientDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof AtrezzoResponse);
        assertEquals("Client or client cuit cannot be null.", (((AtrezzoResponse) response.getBody()).getMessage()));
    }


    @Test
    @DisplayName("Create Client in Controller - ClientService throws exception")
    void createClientWhenClientServiceThrowsExceptionTest() throws JsonProcessingException {
        // Arrange
        Client clientModel = modelMapper.map(clientDTO, Client.class);
        String exceptionMessage = "Test exception message";

        when(clientService.createClient(any(Client.class))).thenThrow(IllegalArgumentException.class);

        ResponseEntity<?> response = clientController.createClient(clientDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    //Find all Clients
    @Test
    @DisplayName("Find all Clients - Success")
    void findAllClientsTest() {
        ClientDTO clientDTO1 = new ClientDTO();
        clientDTO1.setId(2L);
        clientDTO1.setCuitNumber("20-89485633-6");
        clientDTO1.setEmail("matias@matias.com");

        AddressDTO addressDTO1 = new AddressDTO();
        addressDTO1 = new AddressDTO();
        addressDTO1.setId(1L);
        addressDTO1.setStreet("Marina");
        addressDTO1.setStreetNumber("145");
        addressDTO1.setCity("Barcelona");
        addressDTO1.setPostalCode("08013");
        addressDTO1.setCountry("Spain");

        clientDTO1.setAddress(addressDTO1);

        when(clientService.findAll()).thenReturn(Arrays.asList(modelMapper.map(clientDTO, Client.class), modelMapper.map(clientDTO1, Client.class)));

        ResponseEntity<?> response = clientController.findAllClients();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientDTO.getCuitNumber(), ((List<ClientDTO>)response.getBody()).get(0).getCuitNumber());
        assertEquals(clientDTO1.getCuitNumber(), ((List<ClientDTO>)response.getBody()).get(1).getCuitNumber());
        assertEquals(clientDTO.getAddress().getStreet(), ((List<ClientDTO>)response.getBody()).get(0).getAddress().getStreet());
        assertEquals(clientDTO1.getAddress().getStreet(), ((List<ClientDTO>)response.getBody()).get(1).getAddress().getStreet());

        verify(clientService, times(1)).findAll();
    }


    //Find Client by Id
    @Test
    @DisplayName("Find by Id - Success")
    void finClientByIdTest() {
        Long id = 1L;
        when(clientService.findById(id)).thenReturn(modelMapper.map(clientDTO, Client.class));

        ResponseEntity<?> response = clientController.findClientById(id);

        assertEquals(clientDTO.getCuitNumber(), ((ClientDTO) response.getBody()).getCuitNumber());
        assertEquals(clientDTO.getEmail(), ((ClientDTO) response.getBody()).getEmail());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(clientService, times(1)).findById(id);
    }

    //Find Client by CuitNumber
    @Test
    @DisplayName("Find by Cuit - Success")
    void finClientByCuitTest() {
        String cuitNumber = "27-31221564-1";
        when(clientService.findClientByCuit(cuitNumber)).thenReturn(modelMapper.map(clientDTO, Client.class));

        ResponseEntity<?> response = clientController.findClientByCuit(cuitNumber);

        assertEquals(clientDTO.getCuitNumber(), ((ClientDTO) response.getBody()).getCuitNumber());
        assertEquals(clientDTO.getEmail(), ((ClientDTO) response.getBody()).getEmail());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(clientService, times(1)).findClientByCuit(cuitNumber);
    }
    //Find Client by legalName
    @Test
    @DisplayName("Find by Legal Name - Success")
    void findClientByLegalNameTest() {
        String legalName = "Siglo21";
        when(clientService.findClientByLegalName(legalName)).thenReturn(modelMapper.map(clientDTO, Client.class));

        ResponseEntity<?> response = clientController.findClientByLegalName(legalName);

        assertEquals(clientDTO.getCuitNumber(), ((ClientDTO) response.getBody()).getCuitNumber());
        assertEquals(clientDTO.getLegalName(), ((ClientDTO) response.getBody()).getLegalName());
        assertEquals(clientDTO.getEmail(), ((ClientDTO) response.getBody()).getEmail());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(clientService, times(1)).findClientByLegalName(legalName);
    }

    @Test
    @DisplayName("Find by Legal Name - Success")
    void finClientByLegalNameTest() {
        String CompanyName = "Siglo21";
        when(clientService.findClientByCompanyName(CompanyName)).thenReturn(modelMapper.map(clientDTO, Client.class));

        ResponseEntity<?> response = clientController.findClientByCompanyName(CompanyName);

        assertEquals(clientDTO.getCuitNumber(), ((ClientDTO) response.getBody()).getCuitNumber());
        assertEquals(clientDTO.getLegalName(), ((ClientDTO) response.getBody()).getLegalName());
        assertEquals(clientDTO.getCompanyName(), ((ClientDTO) response.getBody()).getCompanyName());
        assertEquals(clientDTO.getEmail(), ((ClientDTO) response.getBody()).getEmail());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(clientService, times(1)).findClientByCompanyName(CompanyName);
    }

    //Update Client

    @Test
    @DisplayName("Update client - Success")
    void updateClientTest() throws JsonProcessingException {

        AddressDTO addressDTO1 = new AddressDTO();
        addressDTO1.setCity("Madrid");
        addressDTO1.setStreet("Lepant");
        addressDTO1.setStreetNumber("1232");

        clientDTO.setAddress(addressDTO1);

        when(clientService.updateClient(any(Client.class))).thenReturn(modelMapper.map(clientDTO, Client.class));
        ObjectMapper objectMapper = new ObjectMapper();
        String clientJson = objectMapper.writeValueAsString(clientDTO);
        MultipartFile profilePicture = new MockMultipartFile("profilePicture", "test.jpg", "image/jpeg", "test-image".getBytes());

        ResponseEntity<?> response = clientController.updateClient(clientDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(clientDTO.getAddress().getStreet(), ((ClientDTO)response.getBody()).getAddress().getStreet());
        assertEquals(clientDTO.getCuitNumber(), ((ClientDTO)response.getBody()).getCuitNumber());

        verify(clientService, times(1)).updateClient(any(Client.class));
    }
    //Delete Client

    @Test
    @DisplayName("Delete client - Success")
    void deleteClientTest() {
        Long id = 1L;

        doNothing().when(clientService).deleteClientById(id);

        ResponseEntity<?> response = clientController.deleteClientById(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(clientService, times(1)).deleteClientById(id);



    }







}
