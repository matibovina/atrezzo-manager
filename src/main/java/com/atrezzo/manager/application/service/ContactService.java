package com.atrezzo.manager.application.service;

import com.atrezzo.manager.application.dto.ContactDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContactService {

    ContactDTO addNewContact(ContactDTO contact);

    ContactDTO findContact(String firstName, String lastName, String companyName);

    Page<ContactDTO> findAllContacts(int pageNumber, int pageSize);

    List<ContactDTO> findAllContactsByCompanyName(String companyName);

    ContactDTO findContactById(Long id) throws ClassNotFoundException;

    ContactDTO updateContact(ContactDTO contact);

    void deleteContactById(Long id);

}
