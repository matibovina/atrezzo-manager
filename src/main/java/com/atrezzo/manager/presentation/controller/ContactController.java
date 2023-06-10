package com.atrezzo.manager.presentation.controller;

import com.atrezzo.manager.application.dto.ContactDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContactController {


    //Create contact
    ResponseEntity<ContactDTO> addNewContact(ContactDTO contactDTO);
    //find Contact by firstName, lastName, companyName
    ResponseEntity<ContactDTO> findContactByNameOrCompany(String firstName, String lastName, String companyName);
    //find contact by id
    ResponseEntity<ContactDTO> findContactById(Long id) throws ClassNotFoundException;
    //find all contacts (pageable)
    ResponseEntity<Page<ContactDTO>> findAllContactByPage(int pageNumber, int pageSize);
    //find contacts by companyName - List
    ResponseEntity<List<ContactDTO>> findContactsByCompanyName(String companyName);
    //Update contact
    ResponseEntity<ContactDTO> updateContact(ContactDTO contactDTO);
    //delete contact
    ResponseEntity<?> deleteContactById(Long id);


}
