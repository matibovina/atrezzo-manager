package com.atrezzo.manager.presentation.controller.impl;

import com.atrezzo.manager.application.dto.ContactDTO;
import com.atrezzo.manager.application.service.ContactService;
import com.atrezzo.manager.domain.model.Contact;
import com.atrezzo.manager.presentation.controller.ContactController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/contacts")
public class ContactControllerImpl implements ContactController {

    @Autowired
    private ContactService contactService;

    private ModelMapper modelMapper = new ModelMapper();



    @Override
    public ResponseEntity<?> addNewContact(ContactDTO contactDTO) {

        try {
            Contact savedContact =  contactService.addNewContact(modelMapper.map(contactDTO, Contact.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(savedContact, ContactDTO.class));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while trying to save contact");
        }
    }

    @Override
    public ResponseEntity<?> findContactByNameOrCompany(String firstName, String lastName, String companyName) {
        try {
            Contact contact = contactService.findContact(firstName, lastName, companyName);
            return ResponseEntity.ok(modelMapper.map(contact, ContactDTO.class));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while trying to find contact");
        }
    }

    @Override
    public ResponseEntity<?> findContactById(Long id) {
        try {
            Contact contact = contactService.findContactById(id);
            return ResponseEntity.ok(modelMapper.map(contact, ContactDTO.class));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while trying to find contact");
        }
    }

    @Override
    public ResponseEntity<?> findAllContactByPage(int pageNumber, int pageSize) {
        try {
            Page<Contact> contactsPage = contactService.findAllContacts(pageNumber, pageSize);
            Page<ContactDTO> dtoPage = contactsPage.map(contact -> modelMapper.map(contact, ContactDTO.class));
            return ResponseEntity.ok(dtoPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while trying to find contacts");
        }
    }

    @Override
    public ResponseEntity<?> findContactsByCompanyName(String companyName) {
        try {
            List<Contact> contacts = contactService.findAllContactsByCompanyName(companyName);
            List<ContactDTO> dtoList = contacts.stream().map(contact -> modelMapper.map(contact, ContactDTO.class)).collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while trying to find contacts");
        }
    }

    @Override
    public ResponseEntity<?> updateContact(ContactDTO contactDTO) {
        try {
            Contact updatedContact = contactService.updateContact(modelMapper.map(contactDTO, Contact.class));
            return ResponseEntity.ok(modelMapper.map(updatedContact, ContactDTO.class));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while trying to update contact");
        }
    }

    @Override
    public ResponseEntity<?> deleteContactById(Long id) {
        try {
            contactService.deleteContactById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while trying to delete contact");
        }
    }
}
