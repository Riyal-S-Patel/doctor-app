package com.logicoy.doctorapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logicoy.doctorapp.dto.DoctorDTO;
import com.logicoy.doctorapp.entity.Doctor;
import com.logicoy.doctorapp.entity.Hospital;
import com.logicoy.doctorapp.repository.DoctorRepository;
import com.logicoy.doctorapp.utils.RolesEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class DoctorService {

    private final EntityManager entityManager;

    private final ObjectMapper objectMapper;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DoctorService(final JpaContext context, final ObjectMapper objectMapper, final DoctorRepository doctorRepository, final PasswordEncoder passwordEncoder){
        this.entityManager = context.getEntityManagerByManagedType(Doctor.class);
        this.objectMapper = objectMapper;
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<DoctorDTO> findDoctor(String search){
        String likeSearch = new StringBuilder("%").append(search).append("%").toString();
       CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Doctor> query = builder.createQuery(Doctor.class);
        Root<Doctor> root = query.from(Doctor.class);

        Predicate containName = builder.like(root.get("name"), likeSearch);
        Predicate containEmail = builder.like(root.get("email"), likeSearch);
        Predicate doctorUsername = builder.like(root.get("username"), likeSearch);
        Predicate activeDoctor = builder.equal(root.get("isActive"), Boolean.TRUE);
        Predicate containSpeciality = builder.like(root.get("speciality"), likeSearch);
        Join<Doctor, Hospital> hospitalJoin = root.join("hospital");
        Predicate containHospitalName = builder.like(hospitalJoin.get("name"), likeSearch);
        Predicate containHospitalLocation = builder.like(hospitalJoin.get("location"), likeSearch);
        Predicate doctorUser = builder.equal(root.get("role"), RolesEnum.DOCTOR);

        query.where( builder.and(activeDoctor, doctorUser , builder.or(containName,containSpeciality,containHospitalName,containEmail,containHospitalLocation,doctorUsername)) );
        List<Doctor> doctors = entityManager.createQuery(query.select(root)).getResultList();
        if(doctors != null && !doctors.isEmpty()){
            List<DoctorDTO> list = new ArrayList<>();
            for(Doctor doctor : doctors){
//                log.info("Speciality ::: {}",doctor.geS);
                list.add(objectMapper.convertValue(doctor,DoctorDTO.class));
            }
            return list;
        }
        return Collections.emptyList();

    }

    public DoctorDTO save(DoctorDTO doctor) {
        Doctor doctorEntity = objectMapper.convertValue(doctor,Doctor.class);
        doctorEntity.setPassword(passwordEncoder.encode(doctor.getPassword()));

        doctorEntity.setActive(Boolean.TRUE);
        doctorEntity.setRole(RolesEnum.DOCTOR);
        doctorEntity = this.doctorRepository.save(doctorEntity);
        return objectMapper.convertValue(doctorEntity, DoctorDTO.class);
    }
}
