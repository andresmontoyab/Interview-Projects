package processor;

import calculator.Calculator;
import calculator.StrikeCalculator;
import router.Router;

import java.util.Map;

public class ProcessorImpl implements Processor {

    public ProcessorImpl(Map<String, Calculator> calculatorMap, Router router) {
        this.calculatorMap = calculatorMap;
        this.router = router;
    }

    private final Map<String, Calculator> calculatorMap;
    private final Router router;

    @Override
    public Integer processResult(String bowlingFrames) {
        Integer amountOfFrames = 10;
        String[] frames = bowlingFrames.split("\\|");
        Integer totalPoints = 0;
        for (int i=0; i<amountOfFrames; i++) {
            Calculator kindOfHit = router.route(frames[i], calculatorMap);
            totalPoints = totalPoints + kindOfHit.calculatePoints(frames, i);
        }
        return totalPoints;
    }
}
