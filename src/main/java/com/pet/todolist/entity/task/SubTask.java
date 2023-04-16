package com.pet.todolist.entity.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pet.todolist.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SubTask extends BaseEntity {

    private String title;
    private String content;
    private Priority priority;
    private Status status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public SubTask() {
    }

    public SubTask(String title, String content, Priority priority, Status status) {
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.status = status;
//        this.task = task;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
