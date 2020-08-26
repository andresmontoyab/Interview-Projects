import calculator.BasicCalculator;
import calculator.Calculator;
import calculator.SpareCalculator;
import calculator.StrikeCalculator;
import processor.Processor;
import processor.ProcessorImpl;
import router.Router;
import router.RouterImpl;

import java.util.HashMap;
import java.util.Map;

public class BowlingGameStarter {
    public static void main(String[] args) {

        Router router = new RouterImpl();
        Map<String, Calculator> calculatorMap = new HashMap<>();
        calculatorMap.put("Strike", new StrikeCalculator());
        calculatorMap.put("Spare", new SpareCalculator());
        calculatorMap.put("Basic", new BasicCalculator());
        Processor processor = new ProcessorImpl(calculatorMap, router);
        System.out.println(processor.processResult("X|X|X|X|X|X|X|X|X|X||XX"));
    }
}
