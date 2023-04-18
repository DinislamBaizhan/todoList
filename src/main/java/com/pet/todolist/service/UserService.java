package com.pet.todolist.service;

import com.pet.todolist.entity.profile.Profile;
import com.pet.todolist.entity.user.Role;
import com.pet.todolist.entity.user.User;
import com.pet.todolist.entity.user.auth.RegisterRequest;
import com.pet.todolist.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
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

//    public User edit(Long id, User user) {
//        Optional<User> optionalUser = repository.findById(id);
//        if (optionalUser.isPresent()) {
//            optionalUser.get().setFirstname(user.getFirstname());
//            optionalUser.get().setLastname(user.getLastname());
//
//            return repository.save(optionalUser.get());
//        }
//        return null;
//    }
}
