package com.pet.todolist.service.interfaces;

import com.pet.todolist.auth.AuthenticationRequest;
import com.pet.todolist.auth.AuthenticationResponse;
import com.pet.todolist.auth.RegisterRequest;
import com.pet.todolist.entity.user.User;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void saveUserToken(User user, String jwtToken);

    void revokeAllUserTokens(User user);
}
