package models;

import org.apache.commons.math3.util.Precision;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class BrewTest {

    Brew brew;
    Random rand;

    @Before
    public void setUp() throws Exception {
        this.brew = new Brew();
        this.rand = new Random();
    }

    @Test
    public void settersRoundToAppropriateValues() {
        double bloomAmount = rand.nextDouble() * rand.nextInt();
        brew.setBloomAmount(bloomAmount);
        Assert.assertEquals(brew.getBloomAmount(), Precision.round(bloomAmount, 1), 0);

        double brewingTemp = rand.nextDouble() * rand.nextInt();
        brew.setBrewingTemperature(brewingTemp);
        Assert.assertEquals(brew.getBrewingTemperature(), Precision.round(brewingTemp, 1), 0);

        double groundCoffeeAmount = rand.nextDouble() * rand.nextInt();
        brew.setGroundCoffeeAmount(groundCoffeeAmount);
        Assert.assertEquals(brew.getGroundCoffeeAmount(), Precision.round(groundCoffeeAmount, 1), 0);

        double coffeeWaterRatio = rand.nextDouble() * rand.nextInt();
        brew.setCoffeeWaterRatio(coffeeWaterRatio);
        Assert.assertEquals(brew.getCoffeeWaterRatio(), coffeeWaterRatio, 0);

        int bloomTime = rand.nextInt();
        brew.setBloomTime(bloomTime);
        Assert.assertEquals(brew.getBloomTime(), bloomTime, 0);

        int totalTime = rand.nextInt();
        brew.setTotalBrewTime(totalTime);
        Assert.assertEquals(brew.getTotalBrewTime(), totalTime, 0);
    }
}