package com.logicoy.doctorapp.security;

import com.logicoy.doctorapp.entity.User;
import com.logicoy.doctorapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;


@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl( UserRepository userRepository){
        this.userRepository = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        if(user == null){
            log.error("Username not found: {}" , username);
            throw new UsernameNotFoundException("could not found user..!!");
        }
        return new CustomUserDetails(user);
    }

    private User getUserByUsername(String username) {
        User user = null;
        user = userRepository.findByUsername(username);
        if(user == null){
            user = userRepository.findByEmail(username);
        }
        if(user == null){
            user = userRepository.findByContactNumber(username);
        }
        return user;
    }
}
