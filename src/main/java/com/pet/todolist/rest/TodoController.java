package com.pet.todolist.rest;

import com.pet.todolist.entity.task.Task;
import com.pet.todolist.entity.task.TaskDTO;
import com.pet.todolist.service.interfaces.ProfileService;
import com.pet.todolist.service.interfaces.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    private final TodoRepository todoRepository;
    private final ProfileService profileService;
    private final ModelMapper modelMapper;

    public TodoController(TodoRepository todoRepository, ProfileService profileService, ModelMapper modelMapper) {
        this.todoRepository = todoRepository;
        this.profileService = profileService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDTO> addTodo(Authentication authentication, @RequestBody TaskDTO taskDTO) {

        var profile = profileService.getProfileByEmail(authentication.getName());

        if (profile.isPresent()) {
            Task task = new Task(
                    taskDTO.getTitle(),
                    taskDTO.getContent(),
                    profile.get()
            );
            Task s = todoRepository.save(task);
            TaskDTO todoS = modelMapper.map(s, TaskDTO.class);
            return ResponseEntity.ok(taskDTO);
        }
        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        var task = todoRepository.getReferenceById(id);
        return ResponseEntity.ok(task);
    }
}
