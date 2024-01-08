package com.logicoy.doctorapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logicoy.doctorapp.utils.RolesEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String contactNumber;
    @Column
    @JsonIgnore
    private String password;
    @Column
    private RolesEnum role;

    @Column
    private String username;




}
