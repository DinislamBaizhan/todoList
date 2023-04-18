package com.pet.todolist.utils;

import com.pet.todolist.entity.task.Status;
import com.pet.todolist.entity.task.Task;
import com.pet.todolist.repository.TaskRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MyTaskScheduler {

    private final TaskRepository taskRepository;

    public MyTaskScheduler(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Scheduled(fixedDelay = 60000)
    public void checkTaskDeadline() {

        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Task> tasks = taskRepository.findByStatus(Status.NEW);
        for (Task task : tasks) {
            if (task.getDeadLine().isBefore(currentDateTime)) {
                task.setStatus(Status.CANCELLED);
                taskRepository.save(task);
            }
        }
    }
}
