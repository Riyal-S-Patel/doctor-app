package com.logicoy.doctorapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="doctors")
@Data
public class Doctor extends User{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="hospital_id")
    private Hospital hospital;
    private boolean isActive;

    @Column
    private String speciality;
}
