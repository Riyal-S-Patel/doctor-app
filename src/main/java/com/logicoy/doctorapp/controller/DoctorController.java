package com.logicoy.doctorapp.controller;

import com.logicoy.doctorapp.dto.DoctorDTO;
import com.logicoy.doctorapp.entity.Doctor;
import com.logicoy.doctorapp.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/doctors")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class DoctorController {


    private final DoctorService doctorService;

    public DoctorController(final DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('PATIENT','DOCTOR')")
    public String get(){
        log.info("Just checking");
        return "Just Testing";
    }

    @PostMapping("register")
    public DoctorDTO save(@RequestBody DoctorDTO doctor){
        return doctorService.save(doctor);
    }

    @GetMapping("/find")
    public List<DoctorDTO> findDoctor(@RequestParam String search){
        log.info("Search : {}", search);
        return doctorService.findDoctor(search);
    }
}
