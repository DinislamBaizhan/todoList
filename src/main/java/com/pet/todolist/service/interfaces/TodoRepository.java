package com.pet.todolist.service.interfaces;

import com.pet.todolist.entity.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Task, Long> {
}
