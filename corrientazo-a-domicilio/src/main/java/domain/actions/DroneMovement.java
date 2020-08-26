package domain.actions;

import domain.Position;

public interface DroneMovement {

    void applyMovement(Position position, String movement);
}
