package usecases;

import domain.Drone;
import domain.executor.LunchDispatcher;
import domain.validator.DroneValidation;

import java.util.List;
import java.util.stream.Collectors;

public class DeliverLunchService {

    private final LunchDispatcher lunchDispatcher;
    private final ReportWritterPort reportWritter;
    private final DroneValidation droneValidation;
    private final ConcurrentDronesPort concurrentDronesPort;

    public DeliverLunchService(LunchDispatcher lunchDispatcher, ReportWritterPort reportWritter, DroneValidation droneValidation, ConcurrentDronesPort concurrentDronesPort) {
        this.lunchDispatcher = lunchDispatcher;
        this.reportWritter = reportWritter;
        this.droneValidation = droneValidation;
        this.concurrentDronesPort = concurrentDronesPort;
    }

    public void process(List<Drone> drones) {
        List<Drone> validatedDrones = drones.stream().filter(drone -> droneValidation.validate(drone)).collect(Collectors.toList());
        Integer amountConcurrentDrones = concurrentDronesPort.getAmount();
        lunchDispatcher.dispatch(validatedDrones, amountConcurrentDrones );
        reportWritter.write(drones);
    }
}
