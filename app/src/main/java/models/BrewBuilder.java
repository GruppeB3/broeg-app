package models;

import models.enums.GrindSize;

public class BrewBuilder {

    private final Brew brew;

    public BrewBuilder() {
        this.brew = new Brew();
    }

    public BrewBuilder grindSize(GrindSize size) {
        this.brew.setGrindSize(size);
        return this;
    }

    public BrewBuilder groundCoffeeAmount(double amount) {
        this.brew.setGroundCoffeeAmount(amount);
        return this;
    }

    public BrewBuilder brewingTemperature(double temp) {
        this.brew.setBrewingTemperature(temp);
        return this;
    }

    public BrewBuilder bloomAmount(double amount) {
        this.brew.setBloomAmount(amount);
        return this;
    }

    public BrewBuilder bloomTime(int time) {
        this.brew.setBloomTime(time);
        return this;
    }

    public BrewBuilder coffeeWaterRatio(double ratio) {
        this.brew.setCoffeeWaterRatio(ratio);
        return this;
    }

    public BrewBuilder totalBrewTime(int time) {
        this.brew.setTotalBrewTime(time);
        return this;
    }

    public Brew get() {
        return this.brew;
    }

}
