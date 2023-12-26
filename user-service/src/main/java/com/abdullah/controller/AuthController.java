package com.abdullah.controller;

import com.abdullah.dto.AuthRequest;
import com.abdullah.dto.CreateUserRequest;
import com.abdullah.exception.InvalidCredentialException;
import com.abdullah.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager manager;

    public AuthController(AuthService authService, AuthenticationManager manager) {
        this.authService = authService;
        this.manager = manager;
    }

    @PostMapping(path = "/register")
    public void register(@RequestBody @Valid CreateUserRequest request){
        authService.createUser(request);
    }
    @GetMapping(path = "/token")
    public String getToken(@RequestBody AuthRequest request) throws InvalidCredentialException {
        Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        if (authenticate.isAuthenticated())
           return authService.generateToken(request.email());
        else {
            throw new InvalidCredentialException("invalid credentials!");
        }
        }

    @GetMapping(path = "/validate")
    public String validate(@RequestParam("token") String token){
        authService.validateToken(token);
        return "Token is valid";
    }


}
