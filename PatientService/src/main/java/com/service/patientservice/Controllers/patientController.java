package com.service.patientservice.Controllers;


import com.service.patientservice.DTO.PatientResponseDto;
import com.service.patientservice.Service.PatientService;
import com.service.patientservice.model.Patient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class patientController {

    private PatientService patientService;
    public patientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/allPatients")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients() {
        List<PatientResponseDto> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}
