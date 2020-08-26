package domain.actions;

import domain.Cardinality;
import domain.Drone;
import domain.DroneBuilder;
import domain.Position;
import domain.actions.factory.ActionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DeliverLunchTest {

    private DroneAction droneAction;

    @Before
    public void setUp() throws Exception {
        droneAction  = ActionFactory.getDroneAction(ActionFactory.Types.LUNCH_TYPE);
    }

    @Test
    public void deliverLunchTest() {
        //given
        Drone drone = createBasicDrone();

        //when
        Drone droneWithLunchPositions = droneAction.doAction(drone);
        List<Position> positions = droneWithLunchPositions.getDeliveredLunches();

        //then
        assertEquals(positions.size(), 3);
        assertEquals(positions.get(0).getX().intValue(), -2);
        assertEquals(positions.get(0).getY().intValue(), 4);
        assertEquals(positions.get(0).getCardinality(), Cardinality.W);

        assertEquals(positions.get(1).getX().intValue(), -1);
        assertEquals(positions.get(1).getY().intValue(), 3);
        assertEquals(positions.get(1).getCardinality(), Cardinality.S);

        assertEquals(positions.get(2).getX().intValue(), 0);
        assertEquals(positions.get(2).getY().intValue(), 0);
        assertEquals(positions.get(2).getCardinality(), Cardinality.W);
    }

    private Drone createBasicDrone() {
        return DroneBuilder.builder()
                .setId("id")
                .setPosition(new Position(0,0, Cardinality.N))
                .setDeliveries(Arrays.asList("AAAAIAA", "DDDAIAD", "AAIADAD"))
                .setDeliveredLunches(new ArrayList<>())
                .build();
    }
}