package com.atrezzo.manager.application.service;

import com.atrezzo.manager.domain.model.Contact;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContactService {

    Contact addNewContact(Contact contact);

    Contact findContact(String firstName, String lastName, String companyName);

    Page<Contact> findAllContacts(int pageNumber, int pageSize);

    List<Contact> findAllContactsByCompanyName(String companyName);

    Contact findContactById(Long id) throws ClassNotFoundException;

    Contact updateContact(Contact contact);

    void deleteContactById(Long id);

}
