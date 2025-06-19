package com.service.patientservice.Service;

import com.service.patientservice.DTO.PatientResponseDto;
import com.service.patientservice.Mapper.PatientMapper;
import com.service.patientservice.Repository.PatientRepository;
import com.service.patientservice.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    PatientRepository patientRepository;

    PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream().
                map(PatientMapper::mapToPatientResponseDto)
                .toList();
    }
}
