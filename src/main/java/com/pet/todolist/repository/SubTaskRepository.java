package com.pet.todolist.repository;

import com.pet.todolist.entity.task.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubTaskRepository extends JpaRepository<SubTask, Long> {
}
