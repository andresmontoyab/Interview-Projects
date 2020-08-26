package domain;

public class Position {

    private Integer x;
    private Integer y;
    private Cardinality cardinality;

    public Position(Integer x, Integer y, Cardinality cardinality) {
        this.x = x;
        this.y = y;
        this.cardinality = cardinality;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Cardinality getCardinality() {
        return cardinality;
    }

    public void setCardinality(Cardinality cardinality) {
        this.cardinality = cardinality;
    }
}
