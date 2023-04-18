package com.pet.todolist.rest;

import com.pet.todolist.entity.profile.Profile;
import com.pet.todolist.service.ProfileService;
import com.pet.todolist.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    private final UserService userService;

    public ProfileController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    @GetMapping()
    public Profile profile() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return profileService.getByEmail(auth.getName());

    }

    @PutMapping()
    public Profile edit(@RequestBody Profile profile, Authentication authentication) {

        return profileService.edit(authentication.getName(), profile);
    }

    @DeleteMapping
    public void delete(Authentication authentication) {
        String email = authentication.getName();

        profileService.delete(email);
    }
}
