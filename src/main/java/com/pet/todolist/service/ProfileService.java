package com.pet.todolist.service;

import com.pet.todolist.entity.profile.Profile;
import com.pet.todolist.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }


    public Optional<Profile> getProfileByEmail(String email) {

        return profileRepository.findByEmail(email);

    }


    public Profile findById(Long id) {
        return profileRepository.findById(id).get();
    }
}
