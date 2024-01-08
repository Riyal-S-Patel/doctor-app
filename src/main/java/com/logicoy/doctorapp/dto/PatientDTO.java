package com.logicoy.doctorapp.dto;

import lombok.Data;

@Data
public class PatientDTO {

    private String name;
    private String email;
    private String contactNumber;
    private String role;
    private String username;
    private String password;
    private String confirmPassword;


}
