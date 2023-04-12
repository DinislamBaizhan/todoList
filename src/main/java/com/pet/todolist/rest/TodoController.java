package com.pet.todolist.rest;

import com.pet.todolist.entity.profile.Profile;
import com.pet.todolist.entity.task.Task;
import com.pet.todolist.repository.TodoRepository;
import com.pet.todolist.service.ProfileService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    private final TodoRepository todoRepository;
    private final ProfileService profileService;

    public TodoController(TodoRepository todoRepository, ProfileService profileService) {
        this.todoRepository = todoRepository;
        this.profileService = profileService;
    }

    @PostMapping
    public Task addTodo(Authentication authentication, @RequestBody Task task) {

        var profile = profileService.getProfileByEmail(authentication.getName());

        if (profile.isPresent()) {
            return todoRepository.save(task);
        }
        return null;

    }

    @GetMapping
    public List<Task> getAllTasks(Authentication authentication) {

        var profile = profileService.getProfileByEmail(authentication.getName());

        return profile.map(Profile::getTaskList).orElse(null);

    }


    @GetMapping("/{id}")
    public Task getById(@PathVariable int id, Authentication authentication) {
        var profile = profileService.getProfileByEmail(authentication.getName()).get();

        return profile.getTaskList().get(id);
    }

    @DeleteMapping
    public void deleteById(@PathVariable Long id, Authentication authentication) {

        var profile = profileService.getProfileByEmail(authentication.getName()).get();

        todoRepository.deleteById(id);
    }
}
