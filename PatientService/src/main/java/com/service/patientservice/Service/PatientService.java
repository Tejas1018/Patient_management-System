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
    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto) {
        try {
            System.out.println("Attempting to update patient with ID: " + id);
            Patient patient = patientRepository.findById(id)
                    .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));

            // Only update fields that are present in the request
            if (patientRequestDto.getPatientName() != null) {
                patient.setPatientName(patientRequestDto.getPatientName());
            }
            if (patientRequestDto.getEmail() != null) {
                // Check if new email already exists for another patient
                if (!patient.getEmail().equals(patientRequestDto.getEmail()) &&
                    patientRepository.existsByEmail(patientRequestDto.getEmail())) {
                    throw new EmailAlreadyExistException("Email already exists: " + patientRequestDto.getEmail());
                }
                patient.setEmail(patientRequestDto.getEmail());
            }
            if (patientRequestDto.getDateOfBirth() != null) {
                try {
                    patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
                } catch (Exception e) {
                    throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd");
                }
            }
            if (patientRequestDto.getAddress() != null) {
                patient.setAddress(patientRequestDto.getAddress());
            }

            Patient updatedPatient = patientRepository.save(patient);
            return PatientMapper.toDTO(updatedPatient);
        } catch (PatientNotFoundException e) {
            System.err.println("Patient not found: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Error updating patient: " + e.getMessage());
            throw e;
        }
    }
}
