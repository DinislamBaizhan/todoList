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

    public Profile edit(String email, Profile profile) {
        var newProfile = profileRepository.findByEmail(email);

        if (newProfile.isPresent()) {

            newProfile.get().setFirstName(profile.getFirstName());
            newProfile.get().setLastName(profile.getLastName());

            return profileRepository.save(newProfile.get());
        }
        return null;
    }

    public void delete(String email) {
        Optional<Profile> profile = profileRepository.findByEmail(email);

        profile.ifPresent(profileRepository::delete);
    }

}
