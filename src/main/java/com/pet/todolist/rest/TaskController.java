package com.pet.todolist.rest;

import com.pet.todolist.entity.profile.Profile;
import com.pet.todolist.entity.task.SubTask;
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
    public void deleteById(@PathVariable int id) {

        taskService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Task edit(Authentication authentication, @PathVariable Long id, @RequestBody Task task) {
        Profile profile = profileService.getByEmail(authentication.getName());
        return taskService.edit(id, task, profile);
    }

    @PostMapping("/subtask/{taskId}")
    public Task subTasks(@RequestBody SubTask subTask, @PathVariable Long taskId) {
        return taskService.addSubTask(taskId, subTask);

    }

    @GetMapping("/subtask/{taskId}")
    public List<SubTask> getAll(@PathVariable Long taskId) {
        return taskService.getAll(taskId);
    }

    @PutMapping("/subtask/{taskId}")
    public SubTask edit(@PathVariable Long taskId, @RequestBody SubTask subTask) {
        return taskService.edit(taskId, subTask);
    }

//    @DeleteMapping("/subtask/{taskId}")
//    public void delete(@PathVariable Long taskId, @RequestBody Long subTaskId) {
//        taskService.deleteSubtask(taskId, subTaskId);
//    }
}
