package com.pet.todolist.service.impl;

import com.pet.todolist.auth.AuthenticationRequest;
import com.pet.todolist.auth.AuthenticationResponse;
import com.pet.todolist.auth.RegisterRequest;
import com.pet.todolist.config.JwtService;
import com.pet.todolist.entity.token.Token;
import com.pet.todolist.entity.token.TokenType;
import com.pet.todolist.entity.user.User;
import com.pet.todolist.repository.TokenRepository;
import com.pet.todolist.service.interfaces.AuthenticationService;
import com.pet.todolist.service.interfaces.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserServiceImpl userServiceImpl;
    private final UserService userService;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserServiceImpl userServiceImpl,
                                     UserService userService, TokenRepository tokenRepository,
                                     JwtService jwtService,
                                     AuthenticationManager authenticationManager) {
        this.userServiceImpl = userServiceImpl;
        this.userService = userService;
        this.tokenRepository = tokenRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {

        User user = userServiceImpl.createUser(request);
        String jwtToken = jwtService.generateToken(user);
        saveUserToken(user, jwtToken);
        return new AuthenticationResponse(
                jwtToken
        );
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userServiceImpl.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return new AuthenticationResponse(
                jwtToken
        );
    }

    public void saveUserToken(User user, String jwtToken) {
        var token = new Token(
                jwtToken,
                TokenType.BEARER,
                false,
                false, user
        );
        tokenRepository.save(token);
    }

    public void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}

