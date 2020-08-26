package domain.actions;

public enum Movement {
    A("Forward"),
    I("Left"),
    D("Right");

    private String description;

    Movement(String description) {
        this.description = description;
    }
}
