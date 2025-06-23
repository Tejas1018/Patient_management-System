package com.service.patientservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Patient {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;


    private String patientName;


    @Column(unique = true)
    private String email;


    private LocalDate dateOfBirth;


    private LocalDate registrationDate;

    private String address;
}
