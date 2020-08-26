package domain.validator;

import domain.Drone;

import java.util.logging.Logger;

public class AmountLunchValidator implements DroneValidation {

    private static DroneValidation instance;

    private AmountLunchValidator() {
    }

    private Logger logger = domain.Logger.getLogger();
    private static final Integer MAX_AMOUNT_LUNCH_PER_DRONE = 3;

    public Boolean validate(Drone drone) {
        Integer amountLunchsToDelivery = drone.getDeliveries().size();
        Boolean result = amountLunchsToDelivery <= MAX_AMOUNT_LUNCH_PER_DRONE;
        if (!result) {
            logger.info(String.format(" The Drone %s is not able to delivery %s lunchs", drone.getId(), amountLunchsToDelivery));
        }
        return result;
    }

    public static synchronized DroneValidation getInstance() {
        if (instance == null) {
            instance = new AmountLunchValidator();
        }
        return instance;
    }
}
