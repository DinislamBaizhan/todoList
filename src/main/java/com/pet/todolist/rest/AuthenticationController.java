package com.pet.todolist.rest;


import com.pet.todolist.auth.AuthenticationRequest;
import com.pet.todolist.auth.AuthenticationResponse;
import com.pet.todolist.auth.RegisterRequest;
import com.pet.todolist.entity.Profile;
import com.pet.todolist.entity.ProfileDTO;
import com.pet.todolist.repository.ProfileRepository;
import com.pet.todolist.service.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final ModelMapper modelMapper;
    private final AuthenticationService service;
    private final ProfileRepository repository;

    public AuthenticationController(ModelMapper modelMapper,
                                    AuthenticationService service,
                                    ProfileRepository repository) {
        this.modelMapper = modelMapper;
        this.service = service;
        this.repository = repository;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/profile")
//    @PreAuthorize("hasAnyAuthority()")
    public ResponseEntity<ProfileDTO> profile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String profile = auth.getName();
        Profile newProfile = repository.findByEmail(profile).get();
        ProfileDTO profileDTO = modelMapper.map(newProfile, ProfileDTO.class);
        return ResponseEntity.ok(profileDTO);
    }
}