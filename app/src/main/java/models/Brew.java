package models;

import org.apache.commons.math3.util.Precision;

import models.enums.GrindSize;

/**
 * This model represents the brew that the user is going to make.
 * It contains all the relevant settings that should be forwarded to the brewer.
 */
public class Brew {

    private GrindSize grindSize;
    private double brewingTemperature;
    private double groundCoffeeAmount;
    private double bloomAmount;
    private double coffeeWaterRatio;
    private int bloomTime;
    private int totalBrewTime;

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

    public void setGrindSize(GrindSize grindSize) {
        this.grindSize = grindSize;
    }

    public void setBloomTime(int bloomTime) {
        this.bloomTime = bloomTime;
    }

    public void setTotalBrewTime(int totalBrewTime) {
        this.totalBrewTime = totalBrewTime;
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
}
