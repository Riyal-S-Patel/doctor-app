package com.logicoy.doctorapp.repository;

import com.logicoy.doctorapp.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
