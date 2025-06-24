package com.service.patientservice.DTO;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Setter
@Getter
public class PatientRequestDto {
    private String patientName;
    @NotNull
    private String email;
    private String dateOfBirth;
    private String address;

}
