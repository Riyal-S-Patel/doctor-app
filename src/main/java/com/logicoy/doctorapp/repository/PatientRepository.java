package com.logicoy.doctorapp.repository;

import com.logicoy.doctorapp.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
