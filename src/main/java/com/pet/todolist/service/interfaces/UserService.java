package com.pet.todolist.service.interfaces;

import com.pet.todolist.entity.user.User;
import com.pet.todolist.entity.user.auth.RegisterRequest;

import java.util.Optional;

public interface UserService {
    User createUser(RegisterRequest request);

    Optional<User> findByEmail(String email);
}
