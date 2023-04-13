package com.pet.todolist.service;

import com.pet.todolist.entity.profile.Profile;
import com.pet.todolist.entity.task.Task;
import com.pet.todolist.repository.ProfileRepository;
import com.pet.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProfileRepository profileRepository;

    public TaskService(TaskRepository taskRepository, ProfileRepository profileRepository) {
        this.taskRepository = taskRepository;
        this.profileRepository = profileRepository;
    }

    public Task save(Task task, Profile profile) {
        task.setProfile(profile);
        return taskRepository.save(task);
    }

    public Task getByIdAndUserEmail(Long id, String email) {
        Optional<Profile> optionalProfile = profileRepository.findByEmail(email);

        Task task = null;

        if (optionalProfile.isPresent()) {

            task = taskRepository.findByProfileIdAndId(optionalProfile.get().getId(), id);
        }
        return task;
    }

    public List<Task> getAllTasks(Long id) {


        return taskRepository.findTasksByProfileId(id);
    }

    public Task getById(int id) {
        return taskRepository.getReferenceById((long) id);
    }

    public void deleteById(int id) {

        taskRepository.deleteById((long) id);

    }
}
