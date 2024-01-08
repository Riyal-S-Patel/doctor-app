package com.logicoy.doctorapp.repository;

import com.logicoy.doctorapp.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
