package com.pet.todolist.entity.task;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pet.todolist.entity.BaseEntity;
import com.pet.todolist.entity.profile.Profile;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Task extends BaseEntity {
    private String title;
    private String content;
    @ManyToOne
    private Profile profile;

    public Task(String title, String content, Profile profile) {
        this.title = title;
        this.content = content;
        this.profile = profile;
    }

    @JsonBackReference
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
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

    public Task() {
    }
}
