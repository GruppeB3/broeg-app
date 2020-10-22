package models;

import java.util.ArrayList;

public class User {

    // we set a default name here since the user might never get to changing the username.
    private String username = "local user";
    private ArrayList<Brew> brewPresets;
    private ArrayList<Brewer> brewers;

    // Constructor
    public User(String username) {
        this.username = username;
    }

    // Getters
    public ArrayList<Brew> getBrewPresets() {
        return brewPresets;
    }

    public ArrayList<Brewer> getBrewers() {
        return brewers;
    }

    public String getUsername() {
        return username;
    }

    // Setters
    public void setBrewPresets(ArrayList<Brew> brewPresets) {
        this.brewPresets = brewPresets;
    }

    public void setBrewers(ArrayList<Brewer> brewers) {
        this.brewers = brewers;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
