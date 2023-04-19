package com.pet.todolist.rest;


import com.pet.todolist.entity.user.User;
import com.pet.todolist.entity.user.auth.AuthenticationRequest;
import com.pet.todolist.entity.user.auth.AuthenticationResponse;
import com.pet.todolist.entity.user.auth.RegisterRequest;
import com.pet.todolist.repository.UserRepository;
import com.pet.todolist.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService service;
    private final UserRepository userRepository;

    public AuthenticationController(
            AuthenticationService service,
            UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {

        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()) {
            return ResponseEntity.
                    status(HttpStatus.CONFLICT)
                    .body(new AuthenticationResponse(
                            "Email already exists"
                    ));
        }

        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}