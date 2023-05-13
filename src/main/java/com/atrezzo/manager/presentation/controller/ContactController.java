package com.atrezzo.manager.presentation.controller;

import com.atrezzo.manager.application.dto.ContactDTO;
import org.springframework.http.ResponseEntity;

public interface ContactController {


    //Create contact
    ResponseEntity<?> addNewContact(ContactDTO contactDTO);
    //find Contact by firstName, lastName, companyName
    ResponseEntity<?> findContactByNameOrCompany(String firstName, String lastName, String companyName);
    //find contact by id
    ResponseEntity<?> findContactById(Long id);
    //find all contacts (pageable)
    ResponseEntity<?> findAllContactByPage(int pageNumber, int pageSize);
    //find contacts by companyName - List
    ResponseEntity<?> findContactsByCompanyName(String companyName);
    //Update contact
    ResponseEntity<?> updateContact(ContactDTO contactDTO);
    //delete contact
    ResponseEntity<?> deleteContactById(Long id);


}
