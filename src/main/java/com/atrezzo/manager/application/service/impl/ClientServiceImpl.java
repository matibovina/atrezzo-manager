package com.atrezzo.manager.application.service.impl;

import com.atrezzo.manager.application.service.ClientService;
import com.atrezzo.manager.application.util.ClientSearchCriteria;
import com.atrezzo.manager.domain.model.Address;
import com.atrezzo.manager.domain.model.Client;
import com.atrezzo.manager.domain.model.User;
import com.atrezzo.manager.domain.repository.AddressRepository;
import com.atrezzo.manager.domain.repository.ClientRepository;
import com.atrezzo.manager.infrastructure.exceptions.NoClientsFoundException;
import com.atrezzo.manager.infrastructure.persistence.AddressEntity;
import com.atrezzo.manager.infrastructure.persistence.ClientEntity;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.atrezzo.manager.infrastructure.persistence.UserEntity;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ClientServiceImpl implements ClientService {


    private final ClientRepository clientRepository;

    private final AddressRepository addressRepository;

    private static final Pattern CUIT_PATTERN = Pattern.compile("^\\d{2}-\\d{8}-\\d{1}$");



    private final ModelMapper modelMapper = new ModelMapper();

    public ClientServiceImpl(ClientRepository clientRepository, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
    }

    public static boolean isValid(String cuit) {
        Matcher matcher = CUIT_PATTERN.matcher(cuit);
        return matcher.matches();
    }


    @Override
    public Client createClient(Client client, Address address) {

        if (client == null || client.getEmail() == null) {
            throw new IllegalArgumentException("Client or client email can't be null.");
        }

        if(!isValid(client.getCuitNumber())) {
            throw new IllegalArgumentException("Cuit number is invalid");
        }


        clientRepository.findByCuitNumber(client.getCuitNumber()).ifPresent(existingClient -> {
            throw new IllegalArgumentException("Cuit number already exists");
        });

        clientRepository.findByLegalName(client.getLegalName()).ifPresent(existingClient -> {
            throw new IllegalArgumentException("Legal Name already exists");
        });

        clientRepository.findByEmail(client.getEmail()).ifPresent(existingClient -> {
            throw new IllegalArgumentException("Email number already exists");
        });



        AddressEntity newAddress = modelMapper.map(address, AddressEntity.class);

        ClientEntity newClient = modelMapper.map(client, ClientEntity.class);
        newClient.setAddress(newAddress);

        try {
            clientRepository.save(newClient);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return modelMapper.map(newClient, Client.class);
    }

    @Override
    public List<Client> findAll() {

        List<ClientEntity> clientEntities = clientRepository.findAll();

        return clientEntities.stream().map(
                clientEntity -> modelMapper.map(clientEntity, Client.class)
        ).collect(Collectors.toList());
    }

    @Override
    public Client findById(Long id) {

        if(id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }

        ClientEntity foundClient = clientRepository.findById(id).orElseThrow(
                () -> new NoClientsFoundException("Client don't exist.")
        );

        return modelMapper.map(foundClient, Client.class);
    }

    @Override
    public Client findClientByUser(User user) {

        if(user == null) {
            throw new IllegalArgumentException("User can't be null");
        }
        ClientEntity foundClient;
        try {
            foundClient = clientRepository.findByUser(modelMapper.map(user, UserEntity.class)).orElseThrow(
                    ()-> new NoClientsFoundException("Client doesn't exists with that user")
            );
        } catch (NoClientsFoundException e) {
            throw new RuntimeException(e);
        }

        return modelMapper.map(foundClient, Client.class);
    }

    @Override
    public Client findClientByCuit(String cuit) {

        if(cuit == null) {
            throw new IllegalArgumentException("Cuit number can't be null");
        }

        ClientEntity foundClient = clientRepository.findByCuitNumber(cuit).orElseThrow(
                ()-> new NoClientsFoundException("Client doesn't exists.")
        );

        return modelMapper.map(foundClient, Client.class);
    }

    @Override
    public Client findClientByCompanyName(String companyName) {

        if(companyName == null) {
            throw new IllegalArgumentException("Company name can't be null");
        }

        ClientEntity foundClient = clientRepository.findByCompanyName(companyName).orElseThrow(
                ()-> new NoClientsFoundException("Client doesn't exists.")
        );

        return modelMapper.map(foundClient, Client.class);
    }

    @Override
    public Client findClientByLegalName(String legalName) {
        if(legalName == null) {
            throw new IllegalArgumentException("Company name can't be null");
        }

        ClientEntity foundClient = clientRepository.findByLegalName(legalName).orElseThrow(
                ()-> new NoClientsFoundException("Client doesn't exists.")
        );

        return modelMapper.map(foundClient, Client.class);
    }

    @Override
    public Client findClient(ClientSearchCriteria searchCriteria) {
        if (searchCriteria == null) {
            throw new IllegalArgumentException("Search criteria can't be null.");
        }

        Optional<ClientEntity> foundClientOptional = Optional.empty();

        if (searchCriteria.getUser() != null) {
            foundClientOptional = clientRepository.findByUser(modelMapper.map(searchCriteria.getUser(), UserEntity.class));
        } else if (searchCriteria.getCuit() != null) {
            foundClientOptional = clientRepository.findByCuitNumber(searchCriteria.getCuit());
        } else if (searchCriteria.getCompanyName() != null) {
            foundClientOptional = clientRepository.findByCompanyName(searchCriteria.getCompanyName());
        } else if (searchCriteria.getLegalName() != null) {
            foundClientOptional = clientRepository.findByLegalName(searchCriteria.getLegalName());
        }

        ClientEntity foundClient = foundClientOptional.orElseThrow(
                () -> new NoClientsFoundException("Client doesn't exist.")
        );

        return modelMapper.map(foundClient, Client.class);
    }

    @Override
    public Client updateClient(Client client, Address address) {

        if (client == null || client.getEmail() == null) {
            throw new IllegalArgumentException("Client or client email can't be null.");
        }

        if(!isValid(client.getCuitNumber())) {
            throw new IllegalArgumentException("Cuit number is invalid");
        }

        if(!clientRepository.existsById(client.getId())) {
            throw new NoClientsFoundException("Client doesn't exists.");
        }

        if(!clientRepository.existsById(client.getId())) {
            throw new NoClientsFoundException("Client doesn't exists.");
        }


        AddressEntity updatedAddress = modelMapper.map(address, AddressEntity.class);

        ClientEntity updatedClient = modelMapper.map(client, ClientEntity.class);
        updatedClient.setAddress(updatedAddress);

        try {
            clientRepository.save(updatedClient);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return modelMapper.map(updatedClient, Client.class);
    }

    @Override
    public void deleteClientById(Long id) {

        if(id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }

        if(clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
        } else {
            throw new NoClientsFoundException("Client doesn't exist with id " + id);
        }
    }
}
