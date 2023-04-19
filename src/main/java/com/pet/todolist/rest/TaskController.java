package com.pet.todolist.rest;

import com.pet.todolist.entity.task.Task;
import com.pet.todolist.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> save(@RequestBody Task task) {
        var savedTask = taskService.save(task);
        return ResponseEntity.ok(savedTask);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return taskService.get(id);
    }

    @PutMapping("/{id}")
    public Task edit(@PathVariable Long id, @RequestBody Task task) {
        return taskService.edit(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        taskService.delete(id);
    }
}
