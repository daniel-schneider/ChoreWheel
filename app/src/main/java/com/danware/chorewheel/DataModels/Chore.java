package com.danware.chorewheel.DataModels;

public class Chore {

    public String id;
    public String title;
    public String description;
    public String frequency;
    public boolean completed;
    public boolean shared;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        shared = shared;
    }

}
