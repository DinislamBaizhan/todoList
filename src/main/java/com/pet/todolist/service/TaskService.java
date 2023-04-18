package com.pet.todolist.service;

import com.pet.todolist.entity.category.Category;
import com.pet.todolist.entity.profile.Profile;
import com.pet.todolist.entity.task.SubTask;
import com.pet.todolist.entity.task.Task;
import com.pet.todolist.repository.CategoryRepository;
import com.pet.todolist.repository.ProfileRepository;
import com.pet.todolist.repository.SubTaskRepository;
import com.pet.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProfileRepository profileRepository;
    private final CategoryRepository categoryRepository;
    private final SubTaskRepository subTaskRepository;

    public TaskService(TaskRepository taskRepository,
                       ProfileRepository profileRepository,
                       CategoryRepository categoryRepository,
                       SubTaskRepository subTaskRepository) {
        this.taskRepository = taskRepository;
        this.profileRepository = profileRepository;
        this.categoryRepository = categoryRepository;
        this.subTaskRepository = subTaskRepository;
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

    public Task getById(Long id) {
        return taskRepository.getReferenceById(id);
    }

    public void deleteById(int id) {

        taskRepository.deleteById((long) id);

    }


    public Task edit(Long id, Task task, Profile profile) {
        Optional<Category> s = categoryRepository.findById(task.getCategory().getId());
        Optional<Task> oldTask = taskRepository.findById(id);
        if (oldTask.isPresent()) {

            oldTask.get().setTitle(task.getTitle());
            oldTask.get().setContent(task.getContent());
            oldTask.get().setPriority(task.getPriority());
            oldTask.get().setStatus(task.getStatus());
            oldTask.get().setCategory(s.get());
            oldTask.get().setProfile(profile);

            return taskRepository.save(oldTask.get());
        }

        return null;

    }


    public Task addSubTask(Long taskId, SubTask subTask) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            task.get().addSubTasks(subTask);
            return taskRepository.save(task.get());
        }
        return null;
    }

    public List<SubTask> getAll(Long id) {

        Optional<Task> optionalTask = taskRepository.findById(id);

        return optionalTask.map(Task::getSubTasks).orElse(null);
    }

    public SubTask edit(Long id, SubTask subTask) {
        Optional<SubTask> optionalSubTask = subTaskRepository.findSubTaskByTaskIdAndId(id, subTask.getId());
        if (optionalSubTask.isPresent()) {

            optionalSubTask.get().setTitle(subTask.getTitle());
            optionalSubTask.get().setContent(subTask.getContent());
            optionalSubTask.get().setPriority(subTask.getPriority());
            optionalSubTask.get().setStatus(subTask.getStatus());

            return subTaskRepository.save(optionalSubTask.get());
        }
        return null;
    }

    public void deleteSubtask(Long taskId, Long subTaskId) {
        subTaskRepository.removeByTaskIdAndId(taskId, subTaskId);
    }
}
