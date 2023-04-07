package com.pet.todolist.service.impl;

import com.pet.todolist.entity.profile.Profile;
import com.pet.todolist.entity.user.Role;
import com.pet.todolist.entity.user.User;
import com.pet.todolist.entity.user.auth.RegisterRequest;
import com.pet.todolist.repository.UserRepository;
import com.pet.todolist.service.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(RegisterRequest request) {

        var user = new User(
                request.getFirstname(),
                request.getLastname(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                Role.USER,
                new Profile()
        );
        return repository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
