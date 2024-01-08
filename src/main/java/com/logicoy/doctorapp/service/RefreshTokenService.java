package com.logicoy.doctorapp.service;

import com.logicoy.doctorapp.dto.RefreshTokenRequestDTO;
import com.logicoy.doctorapp.entity.RefreshToken;
import com.logicoy.doctorapp.repository.RefreshTokenRepository;
import com.logicoy.doctorapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Autowired
    RefreshTokenService(final RefreshTokenRepository refreshTokenRepository, final UserRepository userRepository){
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public RefreshToken createRefreshToken(String username){
        Instant expiry = ZonedDateTime.now().plusSeconds(36000).toInstant();
        RefreshToken refreshToken = RefreshToken.builder()
                .userInfo(userRepository.findByUsername(username))
                .token(UUID.randomUUID().toString())
                .expiryDate(expiry)
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }


    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;

    }

    public RefreshToken findByUserByUsername(String username) {
        return refreshTokenRepository.findByUserInfo_Username(username);
    }

    public RefreshToken validateToken(String username) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserInfo_Username(username);
        if(refreshToken == null){
            return  createRefreshToken(username);
        }else {
            Instant expiryInstance = ZonedDateTime.now().plusSeconds(36000).toInstant();
            log.info("DATE ::::::: {}",expiryInstance);
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setExpiryDate(expiryInstance);
            return refreshTokenRepository.save(refreshToken);
        }
    }
}
