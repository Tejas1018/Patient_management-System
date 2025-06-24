package com.service.patientservice.Repository;


import com.service.patientservice.DTO.PatientRequestDto;
import com.service.patientservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    List<Patient> findAll();
    PatientRequestDto save(PatientRequestDto patientRequestDto);
    boolean existsByEmail(String email);

}
