package com.logicoy.doctorapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.function.Function;


@Entity
@Table(name="refresh_token")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String token;
    @Column
    private Instant expiryDate;

//    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", unique = true)
    private User userInfo;

    
}
