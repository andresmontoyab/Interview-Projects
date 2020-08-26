package domain.actions.factory;

import domain.actions.DroneAction;
import domain.actions.LinealMovement;
import domain.actions.builder.DeliverLunchActionBuilder;

public class ActionFactory {

    public static DroneAction getDroneAction(Types actionType) {
        switch (actionType) {
            case LUNCH_TYPE:
                return DeliverLunchActionBuilder.builder()
                        .setDroneMovement(LinealMovement.getInstance())
                        .build();
        }
        return null;
    }

    public static enum Types {
        LUNCH_TYPE
    }
}
