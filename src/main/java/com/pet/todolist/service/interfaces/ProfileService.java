package com.pet.todolist.service.interfaces;

import com.pet.todolist.entity.profile.Profile;

import java.util.Optional;

public interface ProfileService {

    Optional<Profile> getProfileByEmail(String email);

    Profile findById(Long id);

}
