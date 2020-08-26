package router;

import calculator.Calculator;

import java.util.Map;

public interface Router {

    Calculator route(String kindOfHit, Map<String, Calculator> calculatorMap);
}
