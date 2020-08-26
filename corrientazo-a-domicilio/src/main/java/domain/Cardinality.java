package domain;

public enum Cardinality {
    N("Norte"),
    S("Sur"),
    E("Oriente"),
    W("Occidente");

    private String description;

    Cardinality(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
