package models;

import org.apache.commons.math3.util.Precision;
import org.json.JSONException;
import org.json.JSONObject;

import models.enums.GrindSize;

/**
 * This model represents the brew that the user is going to make.
 * It contains all the relevant settings that should be forwarded to the brewer.
 */
public class Brew {

    private GrindSize grindSize = GrindSize.MEDIUM;
    private double brewingTemperature = 93;
    private double groundCoffeeAmount = 60;
    private double bloomAmount = 150;
    private double coffeeWaterRatio = 6;
    private int bloomTime = 30;
    private int totalBrewTime = 180;

    private String name;
    private int communityId = 0;
    private boolean isSystem = false;

    // we provide an empty constructor in case somebody wants to use the setters
    public Brew() {}

    public Brew(GrindSize grindSize, double brewingTemperature, double groundCoffeeAmount, double coffeeWaterRatio, double bloomAmount, int totalBrewTime, int bloomTime) {
        this.grindSize = grindSize;
        this.brewingTemperature = Precision.round(brewingTemperature, 1);
        this.groundCoffeeAmount = Precision.round(groundCoffeeAmount, 1);
        this.bloomAmount = Precision.round(bloomAmount, 1);
        this.coffeeWaterRatio = Precision.round(coffeeWaterRatio, 1);
        this.bloomTime = bloomTime;
        this.totalBrewTime = totalBrewTime;
    }

    // Setters
    public void setBrewingTemperature(double brewingTemperature) {
        this.brewingTemperature = Precision.round(brewingTemperature, 1);
    }

    public void setGroundCoffeeAmount(double groundCoffeeAmount) {
        this.groundCoffeeAmount = Precision.round(groundCoffeeAmount, 1);
    }

    public void setBloomAmount(double bloomAmount) {
        this.bloomAmount = Precision.round(bloomAmount, 1);
    }

    public void setCoffeeWaterRatio(double coffeeWaterRatio) {
        this.coffeeWaterRatio = coffeeWaterRatio;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setGrindSize(GrindSize grindSize) {
        this.grindSize = grindSize;
    }

    public void setBloomTime(int bloomTime) {
        this.bloomTime = bloomTime;
    }

    public void setTotalBrewTime(int totalBrewTime) {
        this.totalBrewTime = totalBrewTime;
    }

    public void setCommunityId(int id) {
        this.communityId = id;
    }

    public void isSystem(boolean isSystem) {
        this.isSystem = isSystem;
    }

    // Getters
    public GrindSize getGrindSize() {
        return grindSize;
    }

    public double getBrewingTemperature() {
        return brewingTemperature;
    }

    public double getGroundCoffeeAmount() {
        return groundCoffeeAmount;
    }

    public int getBloomTime() {
        return bloomTime;
    }

    public double getBloomAmount() {
        return bloomAmount;
    }

    public int getTotalBrewTime() {
        return totalBrewTime;
    }

    public double getCoffeeWaterRatio() {
        return coffeeWaterRatio;
    }

    public String getName() { return name; }

    public int getCommunityId() {
        return communityId;
    }

    public boolean isSystem() {
        return isSystem;
    }

    public void update(Brew brew) {
        this.setBloomTime(brew.getBloomTime());
        this.setBloomAmount(brew.getBloomAmount());
        this.setBrewingTemperature(brew.getBrewingTemperature());
        this.setGroundCoffeeAmount(brew.getGroundCoffeeAmount());
        this.setGrindSize(brew.getGrindSize());
        this.setCoffeeWaterRatio(brew.getCoffeeWaterRatio());
        this.setTotalBrewTime(brew.getTotalBrewTime());

        this.setName(brew.getName());
        this.setCommunityId(brew.getCommunityId());
    }

    public static Brew fromApi(JSONObject json) throws JSONException {
        Brew brew = new Brew();
        brew.setCommunityId(json.getInt("id"));
        brew.setName(json.getString("name"));
        brew.setGrindSize(GrindSize.valueOf(json.getString("grind_size")));
        brew.setBrewingTemperature(json.getDouble("brewing_temperature"));
        brew.setGroundCoffeeAmount(json.getDouble("ground_coffee_amount"));
        brew.setCoffeeWaterRatio(json.getDouble("coffee_water_ratio"));
        brew.setBloomTime(json.getInt("bloom_time"));
        brew.setBloomAmount(json.getDouble("bloom_water_amount"));
        brew.setTotalBrewTime(json.getInt("total_brew_time"));
        return brew;
    }

    public JSONObject toApiJson() throws JSONException {
        JSONObject json = new JSONObject();

        json.put("name", this.getName());
        json.put("local_id", this.getCommunityId());
        json.put("grind_size", this.getGrindSize().toString());
        json.put("brewing_temperature", this.getBrewingTemperature());
        json.put("ground_coffee_amount", this.getGroundCoffeeAmount());
        json.put("coffee_water_ratio", this.getCoffeeWaterRatio());
        json.put("bloom_time", this.getBloomTime());
        json.put("bloom_water_amount", this.getBloomAmount());
        json.put("total_brew_time", this.getTotalBrewTime());

        return json;
    }
}
