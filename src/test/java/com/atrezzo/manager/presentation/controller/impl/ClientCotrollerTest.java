package com.atrezzo.manager.presentation.controller.impl;


import com.atrezzo.manager.application.service.impl.ClientServiceImpl;
import com.atrezzo.manager.domain.model.Address;
import com.atrezzo.manager.domain.model.Client;
import com.atrezzo.manager.domain.model.User;
import com.atrezzo.manager.infrastructure.persistence.ClientEntity;
import com.atrezzo.manager.infrastructure.persistence.UserEntity;
import com.atrezzo.manager.presentation.controller.ClientController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class ClientCotrollerTest {


    @Mock
    ClientServiceImpl clientService;

    @InjectMocks
    ClientController clientController;

    Client clientModel;

    ClientEntity clientEntity;

    Address address;

    ModelMapper modelMapper = new ModelMapper();

    User userModel;

    @BeforeEach
    void setup() {


        clientModel = new Client();
        clientModel.setId(1L);
        clientModel.setEmail("ma@ma.com");
        clientModel.setPhone("1345679841");
        clientModel.setCuitNumber("20-33894856-6");

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



        clientModel.setUser(userModel);
        clientModel.setAddress(address);

    }


    //Create Client

    //Find all Clients

    //Find Client by Id

    //Find Client by CuitNumber

    //Find Client by legalName

    //Find Client by User

    //Find Client by Company Name

    //Update Client

    //Delete Client






}
