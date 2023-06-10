package com.atrezzo.manager.presentation.controller.impl;

import com.atrezzo.manager.application.dto.ContactDTO;
import com.atrezzo.manager.application.service.ContactService;
import com.atrezzo.manager.presentation.controller.ContactController;
import com.atrezzo.manager.presentation.exception.CustomIllegalArgumentException;
import com.atrezzo.manager.presentation.exception.NoClassFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactControllerImpl implements ContactController {

    @Autowired
    private final ContactService contactService;


    @Override
    @PostMapping("/contacts")
    public ResponseEntity<ContactDTO> addNewContact(@RequestBody ContactDTO contactDTO) throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(contactService.addNewContact(contactDTO), HttpStatus.OK);
    }

    @Override
    @GetMapping("/contact/search")
    public ResponseEntity<ContactDTO> findContactByNameOrCompany(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String companyName)
            throws NoClassFoundException, CustomIllegalArgumentException{
        return new ResponseEntity<>(contactService.findContact(firstName, lastName, companyName), HttpStatus.OK);
    }

    @Override
    @GetMapping("/contact/{id}")
    public ResponseEntity<ContactDTO> findContactById(@PathVariable Long id) throws NoClassFoundException, CustomIllegalArgumentException, ClassNotFoundException {
        return new ResponseEntity<>(contactService.findContactById(id), HttpStatus.OK);

    }

    @Override
    @GetMapping("/contacts")
    public ResponseEntity<Page<ContactDTO>> findAllContactByPage(
            @RequestParam int pageNumber,
            @RequestParam int pageSize)
            throws NoClassFoundException, CustomIllegalArgumentException {
        return new ResponseEntity<>(contactService.findAllContacts(pageNumber, pageSize), HttpStatus.OK);

    }

    @Override
    @GetMapping("/contacts/company")
    public ResponseEntity<List<ContactDTO>> findContactsByCompanyName(@RequestParam String companyName)
            throws NoClassFoundException, CustomIllegalArgumentException{
        return new ResponseEntity<>(contactService.findAllContactsByCompanyName(companyName), HttpStatus.OK);
    }

    @Override
    @PutMapping("/contact")
    public ResponseEntity<ContactDTO> updateContact(@RequestBody ContactDTO contactDTO)
            throws NoClassFoundException, CustomIllegalArgumentException{
        return new ResponseEntity<>(contactService.updateContact(contactDTO), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/contact/{id}")
    public ResponseEntity<?> deleteContactById(@PathVariable Long id) {
        contactService.deleteContactById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
