package domain;

import domain.actions.DroneAction;

import java.util.ArrayList;
import java.util.List;

public class DroneBuilder {

    private String id;
    private List<String> deliveries;
    private Position position;
    private List<Position> deliveredLunches = new ArrayList<>();
    private DroneAction droneAction;

    public static DroneBuilder builder() {
        return new DroneBuilder();
    }

    public DroneBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public DroneBuilder setDeliveries(List<String> deliveries) {
        this.deliveries = deliveries;
        return this;
    }

    public DroneBuilder setPosition(Position position) {
        this.position = position;
        return this;
    }

    public DroneBuilder setDeliveredLunches(List<Position> deliveredLunches) {
        this.deliveredLunches= deliveredLunches;
        return this;
    }

    public DroneBuilder setDroneAction(DroneAction droneAction) {
        this.droneAction = droneAction;
        return this;
    }

    public Drone build() {
        return new Drone(id, deliveries, position, deliveredLunches, droneAction);
    }

}
