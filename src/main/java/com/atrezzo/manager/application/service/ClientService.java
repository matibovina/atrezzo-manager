package com.atrezzo.manager.application.service;

import com.atrezzo.manager.application.util.ClientSearchCriteria;
import com.atrezzo.manager.domain.model.Address;
import com.atrezzo.manager.domain.model.Client;
import com.atrezzo.manager.domain.model.User;

import java.util.List;

public interface ClientService {

   Client createClient(Client client, Address address);

   List<Client> findAll();

   Client findById(Long id);

    Client findClientByUser(User user);

    Client findClientByCuit(String cuit);

   Client findClientByCompanyName(String companyName);

   Client findClientByLegalName(String legalName);

    Client findClient(ClientSearchCriteria searchCriteria);

    Client updateClient(Client client, Address address);

    void deleteClientById(Long id);

}
