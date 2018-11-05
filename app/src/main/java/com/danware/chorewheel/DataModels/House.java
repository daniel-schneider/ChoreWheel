package com.danware.chorewheel.DataModels;

import java.util.List;

public class House {

    public String id;
    private List<Chore> chores;
    public List<User> user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Chore> getChores() {
        return chores;
    }

    public void setChores(List<Chore> chores) {
        this.chores = chores;
    }

    public List<com.danware.chorewheel.DataModels.User> getUser() {
        return user;
    }

    public void setUser(List<com.danware.chorewheel.DataModels.User> user) {
        this.user = user;
    }
}
