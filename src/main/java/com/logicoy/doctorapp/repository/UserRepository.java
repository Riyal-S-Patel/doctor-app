package com.logicoy.doctorapp.repository;

import com.logicoy.doctorapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String username);

    User findByContactNumber(String username);
}
