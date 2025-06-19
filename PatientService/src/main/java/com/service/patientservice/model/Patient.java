package com.service.patientservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id;

    @NotNull
    private String patientName;

    @NotNull
    @Column(unique = true)
    private String Email;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private LocalDate registrationDate;
    private String address;
}

