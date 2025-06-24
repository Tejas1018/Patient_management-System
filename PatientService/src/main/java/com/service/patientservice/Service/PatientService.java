package com.service.patientservice.Service;

import com.service.patientservice.DTO.PatientRequestDto;
import com.service.patientservice.DTO.PatientResponseDto;
import com.service.patientservice.Exceptions.EmailAlreadyExistException;
import com.service.patientservice.Exceptions.PatientNotFoundException;
import com.service.patientservice.Mapper.PatientMapper;
import com.service.patientservice.Repository.PatientRepository;
import com.service.patientservice.model.Patient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    PatientRepository patientRepository;

    PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream().
                map(PatientMapper::toDTO)
                .toList();
    }

    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto) {
        if(patientRepository.existsByEmail(patientRequestDto.getEmail())) {
            throw new EmailAlreadyExistException("Patient with this email already exists" + patientRequestDto.getEmail());
        }
        Patient patient = PatientMapper.toPatient(patientRequestDto);
        patient.setRegistrationDate(java.time.LocalDate.now());
        Patient savedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(savedPatient);
    }
    public Patient updatePatient(UUID id,PatientRequestDto patientRequestDto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));

        if(patientRequestDto.getPatientName() != null) {
            patient.setPatientName(patientRequestDto.getPatientName());
        }
        if(patientRequestDto.getEmail() != null) {
            patient.setEmail(patientRequestDto.getEmail());
        }
        if(patientRequestDto.getDateOfBirth() != null) {
            patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
        }
        if(patientRequestDto.getAddress() != null) {
            patient.setAddress(patientRequestDto.getAddress());
        }
        Patient updatedPatient = patientRepository.save(patient);
        return updatedPatient;
    }
}
