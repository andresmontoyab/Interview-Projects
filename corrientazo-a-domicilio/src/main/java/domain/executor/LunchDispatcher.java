package domain.executor;

import domain.Drone;
import domain.DroneBuilder;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class LunchDispatcher {

    private Logger logger = domain.Logger.getLogger();

    private static LunchDispatcher instance;

    private LunchDispatcher() {
    }

    public List<Drone> dispatch(List<Drone> drones, Integer concurrentDrones) {
        logger.log(Level.INFO, "Staring the dispatch process with "+ concurrentDrones  + " concurrent drones");
        ExecutorService executorService = Executors.newFixedThreadPool(concurrentDrones);
        List<Future<Drone>> futuresDeliveredPositions = null;
        try {
            futuresDeliveredPositions = executorService.invokeAll(drones);
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "There was an error processing the executor service");
        } finally {
            executorService.shutdown();
        }
        logger.log(Level.INFO, "Finishing the dispatch process ");
        return futuresDeliveredPositions.stream()
                .map(futures -> waitUntilDroneFinish(futures))
                .collect(Collectors.toList());
    }

    private Drone waitUntilDroneFinish(Future<Drone> futures) {
        Drone droneWithDeliveredPositions = DroneBuilder.builder().build();
        try {
            droneWithDeliveredPositions = futures.get();
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "There was an error waiting for the Drones responses");
        } catch (ExecutionException e) {
            logger.log(Level.SEVERE, "There was an error waiting for the Drones responses", e);
        }
        return droneWithDeliveredPositions;
    }

    public static synchronized LunchDispatcher getInstance() {
        if (instance == null) {
            instance = new LunchDispatcher();
        }
        return instance;
    }
}
