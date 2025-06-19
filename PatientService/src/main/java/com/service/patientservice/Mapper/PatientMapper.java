package com.service.patientservice.Mapper;

import com.service.patientservice.DTO.PatientResponseDto;
import com.service.patientservice.model.Patient;

public class PatientMapper {
    public static PatientResponseDto mapToPatientResponseDto(Patient patient) {
        PatientResponseDto patientResponseDto = new PatientResponseDto();
        patientResponseDto.setId(patient.getId().hashCode());
        patientResponseDto.setPatientName(patient.getPatientName());
        patientResponseDto.setEmail(patient.getEmail());
        patientResponseDto.setDateOfBirth(patient.getDateOfBirth().toString());
        patientResponseDto.setAddress(patient.getAddress());
        return patientResponseDto;
    }
}
