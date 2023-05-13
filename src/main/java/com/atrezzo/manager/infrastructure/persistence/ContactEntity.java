package com.atrezzo.manager.infrastructure.persistence;

import com.atrezzo.manager.domain.model.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacts")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private String department;

    private String position;

    private String profilePicture;


}
