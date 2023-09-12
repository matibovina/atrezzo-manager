package com.atrezzo.manager.application.service;

import com.atrezzo.manager.application.dto.ClientDTO;
import com.atrezzo.manager.application.dto.UserDTO;
import com.atrezzo.manager.application.util.ClientSearchCriteria;

import java.util.List;

public interface ClientService {

   ClientDTO createClient(ClientDTO client);

   List<ClientDTO> findAll();

   ClientDTO findById(Long id);

    ClientDTO findClientByUser(UserDTO user);

    ClientDTO findClientByCuit(String cuit);

   ClientDTO findClientByCompanyName(String companyName);

   ClientDTO findClientByLegalName(String legalName);

    ClientDTO findClient(ClientSearchCriteria searchCriteria);

    ClientDTO updateClient(ClientDTO client);

    void deleteClientById(Long id);

}
