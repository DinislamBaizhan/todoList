package com.pet.todolist.rest;

import com.pet.todolist.entity.profile.Profile;
import com.pet.todolist.service.ProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping()
    public Profile get() {
        return profileService.get();
    }

    @PutMapping()
    public Profile edit(@RequestBody Profile profile) {
        return profileService.edit(profile);
    }

    @DeleteMapping
    public void delete() {
        profileService.delete();
    }
}
