package com.atrezzo.manager.application.service.impl;

import com.atrezzo.manager.application.dto.ContactDTO;
import com.atrezzo.manager.application.service.ContactService;
import com.atrezzo.manager.domain.repository.ContactRepository;
import com.atrezzo.manager.domain.model.ContactEntity;
import com.atrezzo.manager.presentation.exception.NoClassFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public ContactDTO addNewContact(ContactDTO contact) {

        ContactEntity existingContact = contactRepository.findByEmail(contact.getEmail());
        if (existingContact != null) {
            throw new IllegalArgumentException("El contacto ya existe");
        }
        if(contact.getClient() == null) {
            throw new IllegalArgumentException("Client cannot be null ");
        }
        ContactEntity saveContact;
        try {
            saveContact = contactRepository.save(modelMapper.map(contact, ContactEntity.class));
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo guardar el contacto. " + e.getMessage());
        }
        return modelMapper.map(saveContact, ContactDTO.class);
    }

    @Override
    public ContactDTO findContact(String firstName, String lastName, String companyName) {

        ContactEntity contact;

        try {
            contact = contactRepository
                    .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrClientCompanyNameContainingIgnoreCase
                            (firstName, lastName, companyName);
            if (contact == null) {
                throw new ClassNotFoundException("No contact found with this arguments");
            }
            return modelMapper.map(contact, ContactDTO.class);
        } catch (Exception e) {
            throw new ClassCastException("An error occurred during search " + e.getMessage());
        }
    }

    @Override
    public Page<ContactDTO> findAllContacts(int pageNumber, int pageSize) {

        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<ContactEntity> contactsPage = contactRepository.findAll(pageable);
            return contactsPage.map(contact -> modelMapper.map(contact, ContactDTO.class));
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de contactos.", e);
        }
    }

    @Override
    public List<ContactDTO> findAllContactsByCompanyName(String companyName) {
        try {
            List<ContactEntity> contactsEntity = contactRepository.findByClientCompanyName(companyName);
            return contactsEntity.stream()
                    .map(contact -> modelMapper.map(contact, ContactDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error while trying to get all contacts." + e.getCause().getMessage());
        }
    }

    @Override
    public ContactDTO findContactById(Long id) throws NoClassFoundException {

        if (id == null) {
            throw new IllegalArgumentException("Id canot be null");
        }
        try {
            ContactEntity contact = contactRepository.findById(id)
                    .orElseThrow(() -> new NoClassFoundException("Contact already exists."));
            return modelMapper.map(contact, ContactDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error while finding contact with id " + id + " " + e.getMessage());
        }
    }

    @Override
    public ContactDTO updateContact(ContactDTO contact) {
        if(contact.getClient() == null) {
            throw new IllegalArgumentException("Client cannot be null ");
        }
        try {
            ContactEntity contactEntity = modelMapper.map(contact, ContactEntity.class);
            ContactEntity updatedContact = contactRepository.save(contactEntity);
            return modelMapper.map(updatedContact, ContactDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error while trying to update contact ." + contact.getFirstName() + e.getMessage()
            );
        }
    }

    @Override
    public void deleteContactById(Long id) {
        try {
            if (contactRepository.existsById(id)) {
                contactRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while trying to delete contact " + e.getMessage());
        }

    }
}
