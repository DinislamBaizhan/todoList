package com.pet.todolist.repository;

import com.pet.todolist.entity.profile.Profile;
import com.pet.todolist.entity.task.Status;
import com.pet.todolist.entity.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findTasksByProfileId(Long id);

    Task findByProfileIdAndId(Long profile_id, Long id);

    List<Task> findByStatus(Status status);

    void deleteByProfileAndId(Profile profile, Long id);
}
