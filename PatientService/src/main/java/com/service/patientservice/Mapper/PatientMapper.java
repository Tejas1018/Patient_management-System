package com.service.patientservice.Mapper;

import com.service.patientservice.DTO.PatientRequestDto;
import com.service.patientservice.DTO.PatientResponseDto;
import com.service.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDto toDTO(Patient patient) {
        PatientResponseDto patientResponseDto = new PatientResponseDto();
        patientResponseDto.setId(patient.getId());
        patientResponseDto.setPatientName(patient.getPatientName());
        patientResponseDto.setEmail(patient.getEmail());
        patientResponseDto.setDateOfBirth(patient.getDateOfBirth().toString());
        patientResponseDto.setAddress(patient.getAddress());
        return patientResponseDto;
    }

    public static Patient toPatient(PatientRequestDto patientRequestDto) {
        Patient patient = new Patient();
        patient.setPatientName(patientRequestDto.getPatientName());
        patient.setEmail(patientRequestDto.getEmail());
        if (patientRequestDto.getDateOfBirth() != null && !patientRequestDto.getDateOfBirth().isEmpty()) {
            patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
        } else {
            throw new IllegalArgumentException("dateOfBirth must not be null or empty");
        }
        patient.setAddress(patientRequestDto.getAddress());
        patient.setRegistrationDate(LocalDate.now());
        return patient;
    }
}
