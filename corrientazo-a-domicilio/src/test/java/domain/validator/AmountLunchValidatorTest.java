package domain.validator;

import domain.Drone;
import domain.DroneBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AmountLunchValidatorTest {
    
    private DroneValidation droneValidation;

    @Before
    public void setUp() throws Exception {
        droneValidation = AmountLunchValidator.getInstance();
    }

    @Test
    public void validateDroneWithAllowdNumberOfLunches() {
        //given
        Drone drone = createDroneWithLunch(2);

        //when
        Boolean result = droneValidation.validate(drone);

        //then
        Assert.assertEquals(true, result);
    }

    @Test
    public void validateDroneWithNotAllowdNumberOfLunches() {
        //given
        Drone drone = createDroneWithLunch(4);

        //when
        Boolean result = droneValidation.validate(drone);

        //then
        Assert.assertEquals(false, result);
    }

    private Drone createDroneWithLunch(int i) {
        return DroneBuilder.builder()
                .setId("id")
                .setDeliveries(Stream.generate(() -> "A").limit(i).collect(Collectors.toList()))
                .build();
    }
}