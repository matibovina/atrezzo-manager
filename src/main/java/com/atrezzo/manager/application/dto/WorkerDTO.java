package com.atrezzo.manager.application.dto;

import com.atrezzo.manager.domain.model.enums.TaxCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String cuit;
    private AddressDTO address;
    private TaxCondition taxCondition;
    private String email;
    private String phone;
    private UserDTO user;
   // private List<ServiceDTO> services;
   // private List<EventDTO> events;
   // private List<SessionServiceDTO> sessionServices;
   // private List<InvoiceDTO> invoices;
    private Date birthDate;
}
