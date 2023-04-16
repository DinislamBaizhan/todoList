package com.pet.todolist.entity.task;

import com.pet.todolist.entity.BaseEntity;
import com.pet.todolist.entity.category.Category;
import com.pet.todolist.entity.profile.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Task extends BaseEntity {
    private String title;
    private String content;
    private Priority priority;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    private Profile profile;

    public Task() {
    }

    public Task(String title, String content, Priority priority, Status status, Category category, Profile profile) {
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.status = status;
        this.category = category;
        this.profile = profile;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
}
