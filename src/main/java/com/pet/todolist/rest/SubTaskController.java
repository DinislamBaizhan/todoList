package com.pet.todolist.rest;

import com.pet.todolist.entity.task.SubTask;
import com.pet.todolist.entity.task.Task;
import com.pet.todolist.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class SubTaskController {

    private final TaskService taskService;

    public SubTaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/subtask/{taskId}")
    public Task subTasks(@RequestBody SubTask subTask, @PathVariable Long taskId) {
        return taskService.addSubTask(taskId, subTask);
    }

    @GetMapping("/subtask/{taskId}")
    public List<SubTask> getAll(@PathVariable Long taskId) {
        return taskService.getAll(taskId);
    }

    @GetMapping("/{taskId}/subtask/{subTaskId}")
    public SubTask get(@PathVariable Long taskId, @PathVariable Long subTaskId) {
        return taskService.getSubTaskById(taskId, subTaskId);
    }

    @PutMapping("/{taskId}/subtask/{subtaskId}")
    public SubTask edit(@PathVariable Long taskId,
                        @PathVariable Long subtaskId,
                        @RequestBody SubTask subTask) {
        return taskService.edit(taskId, subtaskId, subTask);
    }

    @DeleteMapping("/{taskId}/subtask/{subtaskId}")
    public void delete(@PathVariable Long taskId, @PathVariable Long subtaskId) {
        taskService.deleteSubtask(taskId, subtaskId);
    }
}
