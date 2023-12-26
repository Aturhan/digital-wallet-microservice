package com.abdullah.controller;

import com.abdullah.dto.CreateUserRequest;
import com.abdullah.dto.SearchUserResponse;
import com.abdullah.dto.UpdateUserRequest;
import com.abdullah.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
Abdullah Turhan
21.12.2023
Implemented CRUD operations fot User
 */
@RestController
@RequestMapping(path = "/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<SearchUserResponse> getUser(@RequestParam("id") String id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }
    @PutMapping
    public void update(@RequestBody @Valid UpdateUserRequest request){
        userService.updateUser(request);
    }
    @DeleteMapping
    public void delete(@RequestParam ("id") String id){
        userService.deleteUser(id);
    }
}

