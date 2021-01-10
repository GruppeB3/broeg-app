package models;

import java.util.ArrayList;

public class User {

    private String username;
    private int communityId = 0;
    private String apiToken = null;
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

    public int getCommunityId() {
        return communityId;
    }

    public String getApiToken() {
        return apiToken;
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

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    // Aux

    public boolean hasApiTokenSet() {
        return this.apiToken != null;
    }
}
