package models;

import models.enums.GrindSize;

public class BrewBuilder {

    private static BrewBuilder instance;
    private Brew brew;

    private BrewBuilder() {
        this.brew = new Brew();
    }

    public static void reset(){
        instance = new BrewBuilder();
    }

    public static BrewBuilder getInstance() {
        if (instance == null) {
            instance = new BrewBuilder();
        }

        return instance;
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

    public BrewBuilder name(String name){
        this.brew.setName(name);
        return this;
    }

    public Brew get() {
        return this.brew;
    }

    public void set(Brew brew) {
        this.brew = brew;
    }

}
