package calculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicCalculatorTest {

    Calculator calculator = new BasicCalculator();

    @Test
    public void calculateOneMissingHit() {
        //given
        String[] frames = "9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||".split("\\|");

        //when
        Integer resultBasicPoints = calculator.calculatePoints(frames, 0);

        //then
        Assert.assertTrue(resultBasicPoints == 9);
    }

    @Test
    public void calculateNoMissingFrame() {
        //given
        String[] frames = "21|9-|9-|9-|9-|9-|9-|9-|9-|9-||".split("\\|");

        //when
        Integer resultBasicPoints = calculator.calculatePoints(frames, 0);

        //then
        Assert.assertTrue(resultBasicPoints == 3);
    }


    @Test
    public void calculateTwoMissingHit() {
        //given
        String[] frames = "--|9-|9-|9-|9-|9-|9-|9-|9-|9-||".split("\\|");

        //when
        Integer resultBasicPoints = calculator.calculatePoints(frames, 0);

        //then
        Assert.assertTrue(resultBasicPoints == 0);
    }
}