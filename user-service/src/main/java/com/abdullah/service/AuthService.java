package com.abdullah.service;

import com.abdullah.dto.AuthRequest;
import com.abdullah.dto.CreateUserRequest;
import com.abdullah.dto.UserKafkaPayload;
import com.abdullah.exception.KafkaPublisherException;
import com.abdullah.model.User;
import com.abdullah.producer.KafkaProducer;
import com.abdullah.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final KafkaProducer producer;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, KafkaProducer producer, BCryptPasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.producer = producer;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String generateToken(String email){
        return jwtService.generateToken(email);
    }

    public void validateToken(String token){
        jwtService.validate(token);
    }

    public void createUser(CreateUserRequest request) {
        final User user = User.builder()
                .fullName(request.fullName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .city(request.city())
                .build();

        final User savedUser = userRepository.save(user);
        try {
            producer.publish("create_wallet", convertToKafkaPayload(savedUser));
            log.info(String.format("Payload sent to wallet service = %s", savedUser));
        } catch (KafkaPublisherException e) {
            log.error(String.format("Exception while sending event via kafka = %s", e.getMessage()));
        }
    }

    private UserKafkaPayload convertToKafkaPayload(User user){
        return UserKafkaPayload.builder()
                .userId(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .location(user.getCity())
                .build();
    }
}