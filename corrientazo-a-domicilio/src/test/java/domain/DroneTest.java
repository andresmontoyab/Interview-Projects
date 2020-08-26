package domain;

import domain.actions.DroneAction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Matchers.any;

public class DroneTest {

    private Drone drone;
    private DroneAction droneActionMock;

    @Before
    public void setUp() throws Exception {
        droneActionMock = Mockito.mock(DroneAction.class);
        drone = DroneBuilder.builder()
                .setId("id")
                .setDroneAction(droneActionMock)
                .build();
    }

    @Test
    public void testCallable() {
        //given
        Drone mockedDrone = createDroneWithDeliveredLunches();
        Mockito.when(droneActionMock.doAction(any(Drone.class))).thenReturn(mockedDrone);

        //when
        Drone droneWithDeliveredLunches = drone.call();

        //then
        Mockito.verify(droneActionMock, Mockito.times(1)).doAction(any(Drone.class));
        Assert.assertEquals(mockedDrone.getDeliveredLunches().size(), droneWithDeliveredLunches.getDeliveredLunches().size());

    }

    private Drone createDroneWithDeliveredLunches() {
        return DroneBuilder.builder()
                .setDeliveredLunches(Arrays.asList(
                        new Position(0,0, Cardinality.N),
                        new Position(1,1, Cardinality.S)))
                .build();

    }
}