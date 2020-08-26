package domain.actions.builder;

import domain.actions.DeliverLunchAction;
import domain.actions.DroneMovement;

public class DeliverLunchActionBuilder {

    private DroneMovement droneMovement;

    public static DeliverLunchActionBuilder builder() {
        return new DeliverLunchActionBuilder();
    }

    public DeliverLunchActionBuilder setDroneMovement(DroneMovement droneMovement) {
        this.droneMovement = droneMovement;
        return this;
    }

    public DeliverLunchAction build() {
        return new DeliverLunchAction(droneMovement);
    }
}
