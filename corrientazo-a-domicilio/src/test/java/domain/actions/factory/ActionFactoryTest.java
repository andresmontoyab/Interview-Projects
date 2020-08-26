package domain.actions.factory;

import domain.actions.DeliverLunchAction;
import domain.actions.DroneAction;
import domain.actions.factory.ActionFactory.Types;
import org.junit.Test;

import static org.junit.Assert.*;

public class ActionFactoryTest {

    private ActionFactory actionFactory;

    @Test
    public void creatingLunchDeliverAction() {

        //when
        DroneAction droneAction = ActionFactory.getDroneAction(Types.LUNCH_TYPE);

        //Then
        assertTrue(droneAction instanceof DeliverLunchAction);
    }

}