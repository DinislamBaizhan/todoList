package com.pet.todolist.rest;

import com.pet.todolist.entity.profile.Profile;
import com.pet.todolist.entity.task.Task;
import com.pet.todolist.service.ProfileService;
import com.pet.todolist.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    private final ProfileService profileService;
    private final TaskService taskService;

    public TaskController(ProfileService profileService, TaskService taskService) {
        this.profileService = profileService;
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> save(Principal principal, @RequestBody Task task) {

        var profile = profileService.getByEmail(principal.getName());

        var savedTask = taskService.save(task, profile);

        return ResponseEntity.ok(savedTask);

    }

    @GetMapping
    public List<Task> getAllTasks(Authentication authentication) {

        Profile profile = profileService.getByEmail(authentication.getName());

        return taskService.getAllTasks(profile.getId());

    }


    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id, Authentication authentication) {
        var profile = profileService.getByEmail(authentication.getName());

        return taskService.getByIdAndUserEmail(id, profile.getEmail());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id, Authentication authentication) {

        taskService.deleteById(id);
    }
}
