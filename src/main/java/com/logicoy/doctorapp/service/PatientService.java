package com.logicoy.doctorapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicoy.doctorapp.dto.PatientDTO;
import com.logicoy.doctorapp.entity.Patient;
import com.logicoy.doctorapp.exception.PasswordMismatchException;
import com.logicoy.doctorapp.repository.PatientRepository;
import com.logicoy.doctorapp.utils.RolesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    @Autowired
    public PatientService(final PatientRepository patientRepository, final PasswordEncoder passwordEncoder, final ObjectMapper objectMapper){
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
        this.objectMapper = objectMapper;
    }


    public PatientDTO register(PatientDTO patientDTO) throws PasswordMismatchException {
        if(patientDTO.getPassword() != null && !patientDTO.getPassword().equals(patientDTO.getConfirmPassword())){
            throw new PasswordMismatchException("Password and confirm password is not matching");
        }
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setEmail(patientDTO.getEmail());
        patient.setRole(RolesEnum.PATIENT);
        patient.setContactNumber(patientDTO.getContactNumber());
        patient.setUsername(patientDTO.getUsername());
        patient.setPassword(passwordEncoder.encode(patientDTO.getPassword()));
        patient = patientRepository.save(patient);
        patientDTO = objectMapper.convertValue(patient, PatientDTO.class);
        return patientDTO;
    }
}
