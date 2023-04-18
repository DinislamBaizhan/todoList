package com.pet.todolist.entity.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pet.todolist.entity.BaseEntity;
import com.pet.todolist.entity.category.Category;
import com.pet.todolist.entity.profile.Profile;
import jakarta.persistence.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Task extends BaseEntity {
    private String title;
    private String content;
    private Priority priority;
    private Status status;
    //    private LocalDateTime deadLine;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JsonIgnore
    private Profile profile;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<SubTask> subTasks;

    public Task() {
    }

    public Task(String title, String content, Priority priority, Status status, Category category, Profile profile, List<SubTask> subTasks) {
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.status = status;
        this.category = category;
        this.profile = profile;
        this.subTasks = subTasks;
    }

//    public LocalDateTime getDeadLine() {
//        return deadLine;
//    }
//
//    public void setDeadLine(LocalDateTime deadLine) {
//        this.deadLine = deadLine;
//    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public void addSubTasks(SubTask subTask) {
        subTasks.add(subTask);
        subTask.setTask(this);
    }

    public void removeSubtask(SubTask subTask) {
        subTasks.remove(subTask);
        subTask.setTask(null);
    }
}