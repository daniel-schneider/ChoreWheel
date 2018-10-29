package com.danware.chorewheel.DataModels;

import java.util.List;

public class User {

    private String Id;
    private String Name;
    private List<Chore> Chores;
    private String HouseId;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Chore> getChores() {
        return Chores;
    }

    public void setChores(List<Chore> chores) {
        Chores = chores;
    }

    public String getHouseId() {
        return HouseId;
    }

    public void setHouseId(String houseId) {
        HouseId = houseId;
    }

}
