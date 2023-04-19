package com.pet.todolist.service;

import com.pet.todolist.entity.category.Category;
import com.pet.todolist.entity.profile.Profile;
import com.pet.todolist.entity.task.SubTask;
import com.pet.todolist.entity.task.Task;
import com.pet.todolist.repository.CategoryRepository;
import com.pet.todolist.repository.SubTaskRepository;
import com.pet.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final ProfileService profileService;
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final SubTaskRepository subTaskRepository;

    public TaskService(TaskRepository taskRepository,
                       ProfileService profileService, CategoryRepository categoryRepository,
                       SubTaskRepository subTaskRepository) {
        this.taskRepository = taskRepository;
        this.profileService = profileService;
        this.categoryRepository = categoryRepository;
        this.subTaskRepository = subTaskRepository;
    }

    public Task save(Task task) {

        Profile profile = profileService.get();

        Optional<Category> category = categoryRepository.findById(task.getCategory().getId());

        task.setProfile(profile);
        task.setCategory(category.get());
        return taskRepository.save(task);
    }

    public Task get(Long id) {
        Profile profile = profileService.get();
        return taskRepository.findByProfileIdAndId(profile.getId(), id);
    }

    public List<Task> getAllTasks() {
        Profile profile = profileService.get();
        return taskRepository.findTasksByProfileId(profile.getId());
    }

    public void delete(Long id) {
        Profile profile = profileService.get();
        taskRepository.deleteByProfileAndId(profile, id);
    }


    public Task edit(Long id, Task task) {

        Profile profile = profileService.get();

        Optional<Category> category = categoryRepository.findById(task.getCategory().getId());
        Optional<Task> oldTask = taskRepository.findById(id);
        if (oldTask.isPresent()) {

            oldTask.get().setTitle(task.getTitle());
            oldTask.get().setContent(task.getContent());
            oldTask.get().setPriority(task.getPriority());
            oldTask.get().setStatus(task.getStatus());
            oldTask.get().setCategory(category.get());
            oldTask.get().setProfile(profile);

            return taskRepository.save(oldTask.get());
        }

        return null;

    }


    public Task addSubTask(Long taskId, SubTask subTask) {
        Task task = get(taskId);

        task.addSubTasks(subTask);
        return taskRepository.save(task);

    }

    public List<SubTask> getAll(Long id) {
        Task task = get(id);
        return task.getSubTasks();
    }

    public SubTask getSubTaskById(Long taskId, Long subtaskId) {
        Task task = get(taskId);
        return task.getSubTasks().get(subtaskId.intValue());
    }

    public SubTask edit(Long taskId, Long subTaskId, SubTask subTask) {
        SubTask editSubTask = getSubTaskById(taskId, subTaskId);
        Task task = get(taskId);

        editSubTask.setTitle(subTask.getTitle());
        editSubTask.setContent(subTask.getContent());
        editSubTask.setPriority(subTask.getPriority());
        editSubTask.setStatus(subTask.getStatus());
        editSubTask.setTask(task);

        return subTaskRepository.save(editSubTask);
    }

    public void deleteSubtask(Long taskId, Long subTaskId) {
        SubTask subTask = getSubTaskById(taskId, subTaskId);
        Task task = get(taskId);
        task.removeSubtask(subTask);
        taskRepository.save(task);
    }
}
