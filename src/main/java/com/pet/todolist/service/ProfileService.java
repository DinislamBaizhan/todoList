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


    public Profile getByEmail(String email) {

        Optional<Profile> optionalProfile = profileRepository.findByEmail(email);

        Profile profile = null;

        if (optionalProfile.isPresent()) {
            profile = optionalProfile.get();
        }
        return profile;

    }
    
}
