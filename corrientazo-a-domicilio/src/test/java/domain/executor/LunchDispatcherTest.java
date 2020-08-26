package domain.executor;

import domain.Cardinality;
import domain.Drone;
import domain.DroneBuilder;
import domain.Position;
import domain.actions.factory.ActionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LunchDispatcherTest {

    private LunchDispatcher lunchDispatcher;

    @Before
    public void setUp() throws Exception {
        lunchDispatcher = LunchDispatcher.getInstance();
    }

    @Test
    public void testDispatcher() {
        //given
        List<Drone> drones = createDrones();
        Integer concurrentDrones = 2;

        //when
        List<Drone> dispatchedDrones = lunchDispatcher.dispatch(drones, concurrentDrones);

        //then
        Assert.assertTrue(dispatchedDrones!= null);
        Assert.assertTrue(dispatchedDrones.stream().allMatch(drone -> drone.getDeliveries().size() > 0));
    }

    @Test
    public void testDispatcherWithBadDrones() {
        //given
        List<Drone> drones = createWrongDrones();
        Integer concurrentDrones = 2;

        //when
        List<Drone> dispatchedDrones = lunchDispatcher.dispatch(drones, concurrentDrones);

        //then
        Assert.assertTrue(dispatchedDrones!= null);
        Assert.assertTrue(dispatchedDrones.get(0).getId() == null);
        Assert.assertTrue(dispatchedDrones.get(0).getDeliveries() == null);
    }

    private List<Drone> createDrones() {
        return Arrays.asList(
                createDrone("id1"),
                createDrone("id2"),
                createDrone("id3")
        );
    }

    private List<Drone> createWrongDrones() {
        return Arrays.asList(
                DroneBuilder.builder()
                        .setId("id1")
                        .setPosition(new Position(0,0, Cardinality.N))
                        .setDeliveredLunches(new ArrayList<>())
                        .setDroneAction(ActionFactory.getDroneAction(ActionFactory.Types.LUNCH_TYPE))
                        .build()
        );
    }

    private Drone createDrone(String id) {
        return DroneBuilder.builder()
                .setId(id)
                .setPosition(new Position(0,0, Cardinality.N))
                .setDeliveries(Arrays.asList("A", "I", "AA"))
                .setDeliveredLunches(new ArrayList<>())
                .setDroneAction(ActionFactory.getDroneAction(ActionFactory.Types.LUNCH_TYPE))
                .build();
    }
}