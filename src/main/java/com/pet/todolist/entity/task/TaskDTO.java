package com.pet.todolist.entity.task;

public class TaskDTO {
    private String title;
    private String content;

    public TaskDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public TaskDTO() {
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
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
}
