package com.pet.todolist.entity.profile;


import com.pet.todolist.entity.BaseEntity;
import com.pet.todolist.entity.task.Task;
import com.pet.todolist.entity.user.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "profiles")
public class Profile extends BaseEntity {
    private String email;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "profile")
    private List<Task> taskList;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Profile(String email, String firstName, String lastName, User user) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
    }

    public Profile() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}