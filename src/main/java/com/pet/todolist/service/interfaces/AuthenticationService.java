package com.pet.todolist.service.interfaces;

import com.pet.todolist.entity.user.User;
import com.pet.todolist.entity.user.auth.AuthenticationRequest;
import com.pet.todolist.entity.user.auth.AuthenticationResponse;
import com.pet.todolist.entity.user.auth.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void saveUserToken(User user, String jwtToken);

    void revokeAllUserTokens(User user);
}
