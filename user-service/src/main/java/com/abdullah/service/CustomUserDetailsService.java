package com.abdullah.service;

import com.abdullah.config.CustomUserDetails;
import com.abdullah.exception.UserNotFoundException;
import com.abdullah.model.User;
import com.abdullah.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Optional<User> user = userRepository.findUserByEmail(email);
       return user.map(CustomUserDetails::new).orElseThrow(()-> new UserNotFoundException("User not found"));
    }
}
