package com.atrezzo.manager.domain.repository;

import com.atrezzo.manager.domain.model.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

    ContactEntity findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrClientCompanyNameContainingIgnoreCase(String firstName, String lastName, String companyName);

    ContactEntity findByLastName(String lastName);

    ContactEntity findByFirstName(String lastName);

    List<ContactEntity> findByClientCompanyName(String companyName);

    ContactEntity findByEmail(String email);

}
