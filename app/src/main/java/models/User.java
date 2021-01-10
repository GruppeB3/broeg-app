package models;

import java.util.ArrayList;

public class User {

    private String email;
    private String name;
    private int communityId = 0;
    private String apiToken = null;
    private ArrayList<Brew> brewPresets;
    private ArrayList<Brewer> brewers;

    // Constructor
    public User(String name) {
        this.name = name;
    }

    // Getters
    public ArrayList<Brew> getBrewPresets() {
        return brewPresets;
    }

    public ArrayList<Brewer> getBrewers() {
        return brewers;
    }

    public String getEmail() {
        return email;
    }

    public int getCommunityId() {
        return communityId;
    }

    public String getApiToken() {
        return apiToken;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setBrewPresets(ArrayList<Brew> brewPresets) {
        this.brewPresets = brewPresets;
    }

    public void setBrewers(ArrayList<Brewer> brewers) {
        this.brewers = brewers;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Aux

    public boolean hasApiTokenSet() {
        return this.apiToken != null;
    }
}
