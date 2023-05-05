package com.atrezzo.manager.presentation.controller.impl;

import com.atrezzo.manager.application.dto.ContactDTO;
import com.atrezzo.manager.application.service.ContactService;
import com.atrezzo.manager.domain.model.Contact;
import com.atrezzo.manager.presentation.controller.ContactController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return null;
    }

    @Override
    public ResponseEntity<?> findContactById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> findAllContactByPage(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<?> findContactsByCompanyName(String companyName) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateContact(ContactDTO contactDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteContactById(Long id) {
        return null;
    }
}
