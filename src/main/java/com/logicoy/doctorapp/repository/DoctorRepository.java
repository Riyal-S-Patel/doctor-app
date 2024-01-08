package com.logicoy.doctorapp.repository;

import com.logicoy.doctorapp.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
