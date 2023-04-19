package com.pet.todolist.service;

import com.pet.todolist.entity.profile.Profile;
import com.pet.todolist.repository.ProfileRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    private Profile getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Optional<Profile> profile = profileRepository.findByEmail(email);
        return profile.orElse(null);

    }

    public Profile get() {
        return getCurrentUser();
    }

    public Profile edit(Profile profile) {
        Profile newProfile = get();

        newProfile.setFirstName(profile.getFirstName());
        newProfile.setLastName(profile.getLastName());

        return profileRepository.save(newProfile);
    }

    public void delete() {
        Profile profile = get();

        profileRepository.delete(profile);
    }

}
