package com.logicoy.doctorapp.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name="patient_id")
    private Patient patient;
    @OneToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;



    private LocalDate appointmentDate;
    private LocalTime time;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
