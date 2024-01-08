package com.logicoy.doctorapp.dto;

import com.logicoy.doctorapp.entity.Hospital;
import lombok.Data;

@Data
public class DoctorDTO {

    private Long id;
    private String name;
    private String email;
    private String contactNumber;
    private HospitalDTO hospital;
    private String speciality;
    private String username;
    private String password;
    private String confirmPassword;

}
