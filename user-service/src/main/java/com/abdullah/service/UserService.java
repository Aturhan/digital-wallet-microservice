package com.abdullah.service;


import com.abdullah.dto.SearchUserResponse;
import com.abdullah.dto.UpdateUserRequest;
import com.abdullah.exception.UserNotFoundException;
import com.abdullah.model.User;
import com.abdullah.repository.UserRepository;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SearchUserResponse getUserById(String id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return SearchUserResponse.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .city(user.getCity())
                .createdAt(user.getCreatedAt())
                .build();
    }


    @Transactional
    public void updateUser(UpdateUserRequest request){
        final User user = userRepository.findById(request.id())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setCity(request.city());
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }


}
