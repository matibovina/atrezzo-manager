package com.atrezzo.manager.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO implements Serializable {


    private Long id;

    private ClientDTO client;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private String department;

    private String position;

    private String profilePicture;

    private LocalDateTime createdAt;


    @Serial
    private static final long serialVersionUID = -9133126963222175615L;

}
