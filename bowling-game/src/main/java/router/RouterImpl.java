package router;

import calculator.Calculator;

import java.util.Map;

public class RouterImpl implements Router{

    @Override
    public Calculator route(String kindOfHit, Map<String, Calculator> calculatorMap) {
        if (kindOfHit.equals("X"))  return calculatorMap.get("Strike");
        if (kindOfHit.contains("/")) return calculatorMap.get("Spare");
        return calculatorMap.get("Basic");
    }
}
