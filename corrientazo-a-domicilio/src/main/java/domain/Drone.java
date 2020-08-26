package domain;

import domain.actions.DroneAction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

public class Drone implements Callable<Drone> {

    private Logger logger = domain.Logger.getLogger();
    private final static String PROCESS_BEGINS =  "Starting the %s for the drone %s in Thread %s ";
    private final static String PROCESS_FINISH =  "Finishing the %s for the drone %s in Thread %s ";

    private final String id;
    private final List<String> deliveries;
    private final Position position;
    private final List<Position> deliveredLunches;
    private final DroneAction droneAction;

    @Override
    public Drone call() {
        logger.info(String.format(PROCESS_BEGINS, droneAction.getClass().getSimpleName(), id, Thread.currentThread().getName()));
        Drone droneWithDeliveredLunchs = droneAction.doAction(this);
        logger.info(String.format(PROCESS_FINISH, droneAction.getClass().getSimpleName(), id, Thread.currentThread().getName()));
        return droneWithDeliveredLunchs;
    }

    public Drone(String id, List<String> deliveries, Position position, List<Position> deliveredLunches, DroneAction droneAction) {
        this.id = id;
        this.deliveries = deliveries;
        this.position = position;
        this.deliveredLunches = deliveredLunches;
        this.droneAction = droneAction;
    }

    public String getId() {
        return id;
    }

    public List<String> getDeliveries() {
        return deliveries;
    }

    public Position getPosition() {
        return position;
    }

    public void addNewDeliveredLunch(Position position) {
        deliveredLunches.add(position);
    }

    public List<Position> getDeliveredLunches() {
        return new ArrayList<>(deliveredLunches);
    }

}
