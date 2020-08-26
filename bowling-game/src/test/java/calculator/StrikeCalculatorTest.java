package calculator;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StrikeCalculatorTest {

    Calculator calculator = new StrikeCalculator();

    @Test
    public void calculateTripleStrike() {
        //given
        String[] bowlingFrame = "X|X|X|X|X|X|X|X".split("\\|");

        //when
        Integer resultFirstFrame = calculator.calculatePoints(bowlingFrame, 0);

        //then
        Assert.assertTrue(resultFirstFrame == 30);

    }

    @Test
    public void calculateTripleStrikeAtTheEnd() {
        //given
        String[] bowlingFrame = "X|X|X|X|X|X|X|X|X|X||XX".split("\\|");

        //when
        Integer resultFirstFrame = calculator.calculatePoints(bowlingFrame, 9);

        //then
        Assert.assertTrue(resultFirstFrame == 30);

    }

    @Test
    public void calculatefinalFrameStrikeAndTwoBonus() {
        //given
        String[] bowlingFrame = "X|X|X|X|X|X|X|X|X|X||81".split("\\|");

        //when
        Integer resultFirstFrame = calculator.calculatePoints(bowlingFrame, 9);

        //then
        Assert.assertTrue(resultFirstFrame == 19);

    }
}