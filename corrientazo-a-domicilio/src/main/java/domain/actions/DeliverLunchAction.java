package domain.actions;

import domain.Drone;
import domain.Position;

import java.util.stream.Collectors;

public class DeliverLunchAction implements DroneAction {

    private final DroneMovement droneMovement;

    public DeliverLunchAction(DroneMovement droneMovement) {
        this.droneMovement = droneMovement;
    }

    public Drone doAction(Drone drone) {
        drone.getDeliveries().stream()
                .map(delivery -> deliverLunch(drone, delivery))
                .collect(Collectors.toList());
        return drone;
    }

    private Drone deliverLunch(Drone drone, String delivery) {
        delivery.chars()
                .mapToObj(charValue -> (char)charValue)
                .map(charValue -> String.valueOf(charValue))
                .forEach(action -> droneMovement.applyMovement(drone.getPosition(), action));
        Position deliveredLunch = createPositionWithDelivery(drone.getPosition());
        drone.addNewDeliveredLunch(deliveredLunch);
        return drone;
    }

    private Position createPositionWithDelivery(Position position) {
        Position lunchDeliverPosition = new Position(position.getX(), position.getY(), position.getCardinality());
        return lunchDeliverPosition;
    }
}
