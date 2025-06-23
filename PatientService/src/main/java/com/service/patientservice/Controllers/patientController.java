package com.service.patientservice.Controllers;

import com.service.patientservice.DTO.PatientRequestDto;
import com.service.patientservice.DTO.PatientResponseDto;
import com.service.patientservice.Service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class patientController {

    private final PatientService patientService;

    public patientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/allPatients")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients() {
        List<PatientResponseDto> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PostMapping("/createPatient")
    public ResponseEntity<PatientResponseDto> createPatient(@RequestBody PatientRequestDto patientRequestDto) {
        PatientResponseDto createdPatient = patientService.createPatient(patientRequestDto);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }
}
