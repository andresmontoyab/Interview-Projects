package domain.actions;

import domain.Cardinality;
import domain.Position;

public class LinealMovement implements DroneMovement {

    private static DroneMovement instance;

    private LinealMovement(){}

    public static synchronized DroneMovement getInstance() {
        if (instance == null) {
            instance = new LinealMovement();
        }
        return instance;
    }

    private static final Integer BLOCK_RATIO = 10;

    public void applyMovement(Position position, String movement) {
        switch (Movement.valueOf(movement)) {
            case A:
                move(position);
                break;
            case D:
                turnRight(position);
                break;
            case I:
                turnLeft(position);
                break;
        }
    }

    private void move(Position position) {
        Integer x,y;
        switch (position.getCardinality()) {
            case N:
                y = position.getY() + 1;
                if (validateIsInBlockRatio(y)) {
                  position.setY(y);
                }
                break;
            case E:
                x = position.getX() + 1;
                if (validateIsInBlockRatio(x)) {
                    position.setX(x);
                }
                break;
            case S:
                y = position.getY() - 1;
                if (validateIsInBlockRatio(y)) {
                    position.setY(y);
                }
                break;
            case W:
                x = position.getX() - 1;
                if (validateIsInBlockRatio(x)) {
                    position.setX(x);
                }
                break;
        }
    }

    private boolean validateIsInBlockRatio(Integer y) {
        return Math.abs(y) <= BLOCK_RATIO;
    }

    private void turnRight(Position position) {
        switch (position.getCardinality()) {
            case N:
                position.setCardinality(Cardinality.valueOf("E"));
                break;
            case E:
                position.setCardinality(Cardinality.valueOf("S"));
                break;
            case S:
                position.setCardinality(Cardinality.valueOf("W"));
                break;
            case W:
                position.setCardinality(Cardinality.valueOf("N"));
                break;
        }
    }

    private void turnLeft(Position position) {
        switch (position.getCardinality()) {
            case N:
                position.setCardinality(Cardinality.valueOf("W"));
                break;
            case E:
                position.setCardinality(Cardinality.valueOf("N"));
                break;
            case S:
                position.setCardinality(Cardinality.valueOf("E"));
                break;
            case W:
                position.setCardinality(Cardinality.valueOf("S"));
                break;
        }
    }


}
