package com.pet.todolist.repository;

import com.pet.todolist.entity.task.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubTaskRepository extends JpaRepository<SubTask, Long> {

    Optional<SubTask> findSubTaskByTaskIdAndId(Long taskId, Long subtaskId);

    void removeByTaskIdAndId(Long taskId, Long subTaskId);
}
