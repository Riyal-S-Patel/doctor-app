package com.logicoy.doctorapp.controller;

import com.logicoy.doctorapp.dto.PatientDTO;
import com.logicoy.doctorapp.entity.Patient;
import com.logicoy.doctorapp.exception.PasswordMismatchException;
import com.logicoy.doctorapp.service.PatientService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/patients")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(final PatientService patientService){
        this.patientService = patientService;
    }

    @PostMapping("/register")
    public PatientDTO register(@RequestBody PatientDTO patientDTO) throws PasswordMismatchException {
        return patientService.register(patientDTO);
    }
}
