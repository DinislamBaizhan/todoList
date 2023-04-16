package com.pet.todolist.service;

import com.pet.todolist.entity.category.Category;
import com.pet.todolist.entity.profile.Profile;
import com.pet.todolist.entity.task.Task;
import com.pet.todolist.repository.CategoryRepository;
import com.pet.todolist.repository.ProfileRepository;
import com.pet.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProfileRepository profileRepository;
    private final CategoryRepository categoryRepository;

    public TaskService(TaskRepository taskRepository, ProfileRepository profileRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.profileRepository = profileRepository;
        this.categoryRepository = categoryRepository;
    }

    public Task save(Task task, Profile profile) {
        Optional<Category> category = categoryRepository.findById(task.getCategory().getId());

        task.setProfile(profile);
        task.setCategory(category.get());
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
