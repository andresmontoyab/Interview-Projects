package calculator;

import utils.HitMapper;

public class BasicCalculator implements Calculator{

    @Override
    public Integer calculatePoints(String[] frames, int actualPosition) {
        Integer firstHit = HitMapper.getValueFromFrame(frames[actualPosition].substring(0,1));
        Integer secondHit = HitMapper.getValueFromFrame(frames[actualPosition].substring(1,2));
        return firstHit + secondHit;
    }
}
