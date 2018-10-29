package com.danware.chorewheel.DataModels;

import java.util.List;

public class House {

    public String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public List<Chore> getChores() {
        return Chores;
    }

    public void setChores(List<Chore> chores) {
        Chores = chores;
    }

    public List<com.danware.chorewheel.DataModels.User> getUser() {
        return User;
    }

    public void setUser(List<com.danware.chorewheel.DataModels.User> user) {
        User = user;
    }

    public List<Chore> Chores;
    public List<User> User;
}
