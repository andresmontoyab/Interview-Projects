package domain.actions;

import domain.Cardinality;
import domain.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class DroneMovementTest {

    private DroneMovement droneMovement = LinealMovement.getInstance();

    @Test
    public void moveToNorth() {
        //given
        Integer initialX = 0;
        Integer initialY = 0;
        Cardinality initialCardinality = Cardinality.N;
        Position position = new Position(initialX, initialY, initialCardinality);

        //when
        droneMovement.applyMovement(position, "A");

        //then
        Integer finalYValue = (initialY +1);
        assertEquals(position.getX(), initialX);
        assertEquals(position.getY(),finalYValue );
        assertEquals(position.getCardinality(), initialCardinality);
    }

    @Test
    public void moveToSouth() {
        //given
        Integer initialX = 0;
        Integer initialy = 0;
        Cardinality initialCardinality = Cardinality.S;
        Position position = new Position(initialX, initialy, initialCardinality );

        //when
        droneMovement.applyMovement(position, "A");

        //then
        Integer finalYValue = (initialy - 1);
        assertEquals(position.getX(), initialX);
        assertEquals(position.getY(),finalYValue );
        assertEquals(position.getCardinality(), initialCardinality);
    }

    @Test
    public void moveToEast() {
        //given
        Integer initialX = 0;
        Integer initialY = 0;
        Cardinality initialCardinality = Cardinality.E;
        Position position = new Position(initialX, initialY, initialCardinality );

        //when
        droneMovement.applyMovement(position, "A");

        //then
        Integer finalXValue = (initialX + 1);
        assertEquals(position.getX(), finalXValue);
        assertEquals(position.getY(), initialY);
        assertEquals(position.getCardinality(), initialCardinality);
    }

    @Test
    public void moveToWest() {
        //given
        Integer initialX = 0;
        Integer initialy = 0;
        Cardinality initialCardinality = Cardinality.W;
        Position position = new Position(initialX, initialy, initialCardinality );

        //when
        droneMovement.applyMovement(position, "A");

        //then
        Integer finalXValue = (initialX - 1);
        assertEquals(position.getX(), finalXValue);
        assertEquals(position.getY(), initialy );
        assertEquals(position.getCardinality(), initialCardinality);
    }

    @Test
    public void northTurnToLeft() {
        //given
        Cardinality initialCardinality = Cardinality.N;
        Position position = new Position(0, 0, initialCardinality );

        //when
        droneMovement.applyMovement(position, "I");

        //then
        Cardinality expectedCardinality = Cardinality.W;
        assertEquals(position.getCardinality(), expectedCardinality);
        assertEquals(position.getX().intValue(), 0);
        assertEquals(position.getY().intValue(), 0);
    }

    @Test
    public void northTurnToRight() {
        //given
        Cardinality initialCardinality = Cardinality.N;
        Position position = new Position(0, 0, initialCardinality );

        //when
        droneMovement.applyMovement(position, "D");

        //then
        Cardinality expectedCardinality = Cardinality.E;
        assertEquals(position.getCardinality(), expectedCardinality);
        assertEquals(position.getX().intValue(), 0);
        assertEquals(position.getY().intValue(), 0);
    }

    @Test
    public void southTurnToLeft() {
        //given
        Cardinality initialCardinality = Cardinality.S;
        Position position = new Position(0, 0, initialCardinality );

        //when
        droneMovement.applyMovement(position, "I");

        //then
        Cardinality expectedCardinality = Cardinality.E;
        assertEquals(position.getCardinality(), expectedCardinality);
        assertEquals(position.getX().intValue(), 0);
        assertEquals(position.getY().intValue(), 0);
    }

    @Test
    public void southTurnToRight() {
        //given
        Cardinality initialCardinality = Cardinality.S;
        Position position = new Position(0, 0, initialCardinality );

        //when
        droneMovement.applyMovement(position, "D");

        //then
        Cardinality expectedCardinality = Cardinality.W;
        assertEquals(position.getCardinality(), expectedCardinality);
        assertEquals(position.getX().intValue(), 0);
        assertEquals(position.getY().intValue(), 0);
    }

    @Test
    public void eastTurnToLeft() {
        //given
        Cardinality initialCardinality = Cardinality.E;
        Position position = new Position(0, 0, initialCardinality );

        //when
        droneMovement.applyMovement(position, "I");

        //then
        Cardinality expectedCardinality = Cardinality.N;
        assertEquals(position.getCardinality(), expectedCardinality);
        assertEquals(position.getX().intValue(), 0);
        assertEquals(position.getY().intValue(), 0);
    }

    @Test
    public void eastTurnToRight() {
        //given
        Cardinality initialCardinality = Cardinality.E;
        Position position = new Position(0, 0, initialCardinality );

        //when
        droneMovement.applyMovement(position, "D");

        //then
        Cardinality expectedCardinality = Cardinality.S;
        assertEquals(position.getCardinality(), expectedCardinality);
        assertEquals(position.getX().intValue(), 0);
        assertEquals(position.getY().intValue(), 0);
    }

    @Test
    public void westTurnToLeft() {
        //given
        Cardinality initialCardinality = Cardinality.W;
        Position position = new Position(0, 0, initialCardinality );

        //when
        droneMovement.applyMovement(position, "I");

        //then
        Cardinality expectedCardinality = Cardinality.S;
        assertEquals(position.getCardinality(), expectedCardinality);
        assertEquals(position.getX().intValue(), 0);
        assertEquals(position.getY().intValue(), 0);
    }

    @Test
    public void westTurnToRight() {
        //given
        Cardinality initialCardinality = Cardinality.W;
        Position position = new Position(0, 0, initialCardinality );

        //when
        droneMovement.applyMovement(position, "D");

        //then
        Cardinality expectedCardinality = Cardinality.N;
        assertEquals(position.getCardinality(), expectedCardinality);
        assertEquals(position.getX().intValue(), 0);
        assertEquals(position.getY().intValue(), 0);
    }
}