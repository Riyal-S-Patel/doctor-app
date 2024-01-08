package com.logicoy.doctorapp.utils;

public enum RolesEnum {
    PATIENT("Patient"), DOCTOR("Doctor");


    public final String value;

    RolesEnum(String value) {
        this.value = value;
    }
}
