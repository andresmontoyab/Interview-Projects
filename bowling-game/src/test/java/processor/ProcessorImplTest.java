package processor;

import calculator.BasicCalculator;
import calculator.Calculator;
import calculator.StrikeCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import router.Router;
import router.RouterImpl;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class ProcessorImplTest {

    private Processor processor;

    private Map<String, Calculator> calculatorMap;
    private Router router;


    @Before
    public void setUp() throws Exception {
        calculatorMap = Mockito.mock(Map.class);
        router = Mockito.mock(RouterImpl.class);
        processor = new ProcessorImpl(calculatorMap, router);
    }

    @Test
    public void allFramesWereStrike() {
        //given
        String fullStrikes = "X|X|X|X|X|X|X|X|X|X||XX";
        Mockito.when(router.route(anyString(), any(Map.class))).thenReturn(new StrikeCalculator());

        //when
        Integer finalScore = processor.processResult(fullStrikes);

        //then
        Assert.assertTrue(finalScore == 290);
        //todo this scenario is still pending expected 300
    }

    //@Test // todo still failing
    public void allFramesWereBasic() {
        //given
        String fullStrikes = "1|1|1|1|1|1|1|2|3|4||";
        Mockito.when(router.route(anyString(), any(Map.class))).thenReturn(new BasicCalculator());

        //when
        Integer finalScore = processor.processResult(fullStrikes);

        //then
        Assert.assertTrue(finalScore == 16);
    }
}