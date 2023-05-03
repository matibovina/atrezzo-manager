package com.atrezzo.manager.presentation.controller.impl;

import com.atrezzo.manager.application.service.ClientService;
import com.atrezzo.manager.presentation.controller.ClientController;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ClientControllerImpl implements ClientController {

    private ClientService clientService;

    private ModelMapper modelMapper = new ModelMapper();



    //POST - Create Client

    //GET - Find all Clients

    //GET - Find client by Id

    //GET - Find client by CuitNUmber

    //GET - Find client by LegalName

    //GET - Find client by CompanyName

    //GET - Find client by User

    //PUT - Update Client

    //DELETE - Delete Client by Id


}
