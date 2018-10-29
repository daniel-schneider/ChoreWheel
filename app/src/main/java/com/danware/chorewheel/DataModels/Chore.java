package com.danware.chorewheel.DataModels;

public class Chore {

    public String Id;
    public String Title;
    public String Description;
    public String Frequency;
    public boolean Completed;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getFrequency() {
        return Frequency;
    }

    public void setFrequency(String frequency) {
        Frequency = frequency;
    }

    public boolean isCompleted() {
        return Completed;
    }

    public void setCompleted(boolean completed) {
        Completed = completed;
    }

    public boolean isShared() {
        return Shared;
    }

    public void setShared(boolean shared) {
        Shared = shared;
    }

    public boolean Shared;

}
