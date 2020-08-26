package calculator;

import static utils.HitMapper.getValueFromFrame;

public class StrikeCalculator implements Calculator {

    @Override
    public Integer calculatePoints(String[] frames, int actualPosition) {
        Integer strikeBasicPoints = 10;
        Integer firstPosition = getValueFromFrame(frames[actualPosition +1]);
        Integer secondPosition = calculateSecondPosition(frames, actualPosition);
        return strikeBasicPoints + firstPosition + secondPosition;
    }

    private Integer calculateSecondPosition(String[] frames, int actualPosition) {
        Integer secondPosition;
        if (validateSecondPositionIsNotDoubleMarks(frames[actualPosition +2])) {
            secondPosition = getValueFromFrame(frames[actualPosition +2]);
        } else {
            if (null != frames[actualPosition +3]) {
                secondPosition = getValueFromFrame(frames[actualPosition +3].substring(0,1));
            } else {
                return 0;
            }
        }
        return secondPosition;
    }

    private Boolean validateSecondPositionIsNotDoubleMarks(String frame) {
        if (frame == "") {
            return false;
        }
        return true;
    }
}
