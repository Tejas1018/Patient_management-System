package com.service.patientservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientResponseDto {
    private int id;
    private String patientName;
    private String email;
    private String dateOfBirth;
    private String address;
}
