package com.service.patientservice.Controllers;

import com.service.patientservice.DTO.PatientRequestDto;
import com.service.patientservice.DTO.PatientResponseDto;
import com.service.patientservice.Exceptions.PatientNotFoundException;
import com.service.patientservice.Service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PutMapping("/updatePatient/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable("id") String idString, @Validated @RequestBody PatientRequestDto patientRequestDto) {
        try {
            UUID id = UUID.fromString(idString);
            PatientResponseDto patientResponseDto = patientService.updatePatient(id, patientRequestDto);
            return new ResponseEntity<>(patientResponseDto, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new PatientNotFoundException("Invalid UUID format: " + idString);
        }
    }
}
