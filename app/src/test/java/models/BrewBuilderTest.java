package models;

import org.apache.commons.math3.util.Precision;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class BrewBuilderTest {

    BrewBuilder builder;
    Brew brew;
    Random rand;

    @Before
    public void setUp() throws Exception {
        this.builder = BrewBuilder.getInstance();
        this.brew = this.builder.get();

        this.rand = new Random();
    }

    @Test
    public void valuesAreSetAppropriately() {
        double bloomAmount = rand.nextDouble() * rand.nextInt();
        builder.bloomAmount(bloomAmount);
        Assert.assertEquals(brew.getBloomAmount(), Precision.round(bloomAmount, 1), 0);

        double brewingTemp = rand.nextDouble() * rand.nextInt();
        builder.brewingTemperature(brewingTemp);
        Assert.assertEquals(brew.getBrewingTemperature(), Precision.round(brewingTemp, 1), 0);

        double groundCoffeeAmount = rand.nextDouble() * rand.nextInt();
        builder.groundCoffeeAmount(groundCoffeeAmount);
        Assert.assertEquals(brew.getGroundCoffeeAmount(), Precision.round(groundCoffeeAmount, 1), 0);

        double coffeeWaterRatio = rand.nextDouble() * rand.nextInt();
        builder.coffeeWaterRatio(coffeeWaterRatio);
        Assert.assertEquals(brew.getCoffeeWaterRatio(), coffeeWaterRatio, 0);

        int bloomTime = rand.nextInt();
        builder.bloomTime(bloomTime);
        Assert.assertEquals(brew.getBloomTime(), bloomTime, 0);

        int totalBrewTime = rand.nextInt();
        builder.totalBrewTime(totalBrewTime);
        Assert.assertEquals(brew.getTotalBrewTime(), totalBrewTime, 0);
    }

    @Test
    public void brewIsNotNull() {
        BrewBuilder builder = new BrewBuilder();
        Assert.assertNotNull(builder.get());
    }
}