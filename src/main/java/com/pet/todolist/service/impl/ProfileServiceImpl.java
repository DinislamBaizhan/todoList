package com.pet.todolist.service.impl;

import com.pet.todolist.entity.profile.Profile;
import com.pet.todolist.repository.ProfileRepository;
import com.pet.todolist.service.interfaces.ProfileService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> getProfileByEmail(String email) {

        return profileRepository.findByEmail(email);

    }

    @Override
    public Profile findById(Long id) {
        return profileRepository.findById(id).get();
    }
}
