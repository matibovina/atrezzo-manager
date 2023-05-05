package com.atrezzo.manager.application.service.impl;


import com.atrezzo.manager.domain.model.Address;
import com.atrezzo.manager.domain.model.Client;
import com.atrezzo.manager.domain.model.User;
import com.atrezzo.manager.domain.repository.AddressRepository;
import com.atrezzo.manager.domain.repository.ClientRepository;
import com.atrezzo.manager.infrastructure.persistence.AddressEntity;
import com.atrezzo.manager.infrastructure.persistence.ClientEntity;
import com.atrezzo.manager.infrastructure.persistence.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {


    @Mock
    ClientRepository clientRepository;
    @Mock
    AddressRepository addressRepository;

    @InjectMocks
    ClientServiceImpl clientService;

    Client clientModel;

    ClientEntity clientEntity;

    Address address;

    ModelMapper modelMapper = new ModelMapper();

    User userModel;

    UserEntity userEntity1;

    MultipartFile profilePicture;

    @BeforeEach
    void setup() {

        profilePicture = mock(MultipartFile.class);


        clientModel = new Client();
        clientModel.setId(1L);
        clientModel.setEmail("ma@ma.com");
        clientModel.setPhone("1345679841");
        clientModel.setCuitNumber("20-33894856-6");

        clientEntity = new ClientEntity();

        address = new Address();
        address.setId(1L);
        address.setStreet("Marina");
        address.setStreetNumber("145");
        address.setCity("Barcelona");
        address.setPostalCode("08013");
        address.setCountry("Spain");

        userModel = new User();
        userModel.setId(1L);
        userModel.setUsername("abcd");
        userModel.setEmail("a@asd.com");
        userModel.setPassword("password1");
        userModel.setRoles(new ArrayList<>());


        userEntity1 = new UserEntity();
        userEntity1.setId(1L);
        userEntity1.setUsername("abcd");
        userEntity1.setEmail("a@asd.com");
        userEntity1.setPassword("password1");
        userEntity1.setRoles(new ArrayList<>());

        clientModel.setUser(userModel);
        clientModel.setAddress(address);

    }


    @Test
    @DisplayName("Create Client - Success")
    void createClientTest() {
        clientEntity = modelMapper.map(clientModel, ClientEntity.class);

        when(clientRepository.save(any(ClientEntity.class))).thenReturn(modelMapper.map(clientModel, ClientEntity.class));

        Client response = clientService.createClient(clientModel);

        assertNotNull(response);
        assertEquals(clientModel.getId(), response.getId());

        assertEquals(clientEntity.getUser().getId(), response.getUser().getId());
        assertEquals(clientEntity.getUser().getUsername(), response.getUser().getUsername());
        assertEquals(clientEntity.getUser().getEmail(), response.getUser().getEmail());
        assertEquals(clientEntity.getUser().getPassword(), response.getUser().getPassword());

        verify(clientRepository, times(1)).save(any(ClientEntity.class));

    }

    @Test
    @DisplayName("Create Client - Client null")
    void createClientWithNullTest() {

        assertThrows(IllegalArgumentException.class, () -> {
            clientService.createClient(null);
        });
    }

    //Find all
    @Test
    @DisplayName("Find all clients - Success")
    void findAllClientsTest() {
        ClientEntity clientEntity1 = new ClientEntity();
        clientEntity1.setId(1L);

        ClientEntity clientEntity2 = new ClientEntity();
        clientEntity2.setId(2L);

        Client client1 = new Client();
        client1.setId(1L);

        Client client2 = new Client();
        client2.setId(2L);

        when(clientRepository.findAll()).thenReturn(Arrays.asList(clientEntity1, clientEntity2));

        List<Client> clients = clientService.findAll();

        assertNotNull(clients);
        assertEquals(2, clients.size());
        assertEquals(client1.getId(), clients.get(0).getId());
        assertEquals(client2.getId(), clients.get(1).getId());

        verify(clientRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Find all clients - No clients found")
    void findAllClientsFailsTest() {

        when(clientRepository.findAll()).thenReturn(Collections.emptyList());

        List<Client> clients = clientService.findAll();

        assertNotNull(clients);
        assertEquals(0, clients.size());

        verify(clientRepository, times(1)).findAll();
    }

    //    find by id
    @Test
    @DisplayName("Find Client By Id - Success")
    void findByIdTest() {
        Long id = 1L;
        when(clientRepository.findById(id)).thenReturn(Optional.of(modelMapper.map(clientModel, ClientEntity.class)));

        Client foundClient = clientService.findById(id);

        assertNotNull(foundClient);
        assertEquals(clientModel.getId(), foundClient.getId());
        assertEquals(clientModel.getAddress().getId(), foundClient.getAddress().getId());
        verify(clientRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Find by id - null id")
    void findByIdNullIdTest() {
        Long id = null;

        assertThrows(IllegalArgumentException.class, () -> {
            clientService.findById(id);
        });
    }

    //    find by user
    @Test
    @DisplayName("Find Client By User - Success")
    void findClientByUserTest() {
        when(clientRepository.findByUser(userEntity1)).thenReturn(Optional.of(modelMapper.map(clientModel, ClientEntity.class)));

        Client foundClient = clientService.findClientByUser(userModel);

        assertEquals(clientModel.getId(), foundClient.getId());
        assertEquals(clientModel.getUser(), foundClient.getUser());

        verify(clientRepository, times(1)).findByUser(userEntity1);
    }

    //    find by cuit number
    @Test
    @DisplayName("Find Client by CUIT - Success")
    void findClientByCuitTest() {
        String cuitNumber = "123456789";

        when(clientRepository.findByCuitNumber(cuitNumber)).thenReturn(Optional.of(modelMapper.map(clientModel, ClientEntity.class)));

        Client foundClient = clientService.findClientByCuit(cuitNumber);

        assertEquals(clientModel.getId(), foundClient.getId());
        assertEquals(clientModel.getCuitNumber(), foundClient.getCuitNumber());

        verify(clientRepository, times(1)).findByCuitNumber(cuitNumber);

    }
//    find by companyName

    @Test
    @DisplayName("Find Client by Company Name - Success")
    void findClientByCompanyNameTest() {
        String companyName = "Siglo21";

        when(clientRepository.findByCompanyName(companyName)).thenReturn(Optional.of(modelMapper.map(clientModel, ClientEntity.class)));

        Client foundClient = clientService.findClientByCompanyName(companyName);

        assertEquals(clientModel.getId(), foundClient.getId());
        assertEquals(clientModel.getCuitNumber(), foundClient.getCuitNumber());
        assertEquals(clientModel.getCompanyName(), foundClient.getCompanyName());

        verify(clientRepository, times(1)).findByCompanyName(companyName);
    }


//    find by legalName

    @Test
    @DisplayName("Find Client by Legal Name - Success")
    void findClientByLegalNameTest() {
        String legalName = "universidad SA";

        when(clientRepository.findByLegalName(legalName)).thenReturn(Optional.of(modelMapper.map(clientModel, ClientEntity.class)));

        Client foundClient = clientService.findClientByLegalName(legalName);

        assertEquals(clientModel.getId(), foundClient.getId());
        assertEquals(clientModel.getCuitNumber(), foundClient.getCuitNumber());
        assertEquals(clientModel.getCompanyName(), foundClient.getCompanyName());
        assertEquals(clientModel.getLegalName(), foundClient.getLegalName());

        verify(clientRepository, times(1)).findByLegalName(legalName);
    }

    //    update client
    @Test
    @DisplayName("Update Client - Success")
    void updateClientTest() {
        when(clientRepository.existsById(clientModel.getId())).thenReturn(true);

        when(clientRepository.save(any(ClientEntity.class))).thenReturn(modelMapper.map(clientModel, ClientEntity.class));
        when(addressRepository.save(any(AddressEntity.class))).thenReturn(modelMapper.map(address, AddressEntity.class));
        Client updatedClient = clientService.updateClient(clientModel);

        assertNotNull(updatedClient);
        assertNotNull(updatedClient);
        assertEquals(clientModel.getId(), updatedClient.getId());
        assertEquals(clientModel.getEmail(), updatedClient.getEmail());
        assertEquals(clientModel.getEmail(), updatedClient.getEmail());
        assertEquals(clientModel.getAddress().getId(), updatedClient.getAddress().getId());
    }


    //    delete client
    @Test
    @DisplayName("Delete Client by Id - Success")
    void deleteClientByIdTest() {
        Long id = 1L;
        when(clientRepository.existsById(id)).thenReturn(true);
        doNothing().when(clientRepository).deleteById(id);

        assertDoesNotThrow(() -> clientService.deleteClientById(id));
        verify(clientRepository, times(1)).deleteById(id);

    }

}
