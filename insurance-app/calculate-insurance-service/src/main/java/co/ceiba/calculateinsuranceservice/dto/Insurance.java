package co.ceiba.calculateinsuranceservice.dto;

import org.springframework.boot.autoconfigure.security.SecurityProperties;

public class Insurance {

    private String id;
    private User user;
    private Property property;
    private double insuranceValue;

    public Insurance(String id, User user, Property property, double insuranceValue) {
        this.id = id;
        this.user = user;
        this.property = property;
        this.insuranceValue = insuranceValue;
    }

    public Insurance() {
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public double getInsuranceValue() {
        return insuranceValue;
    }

    public void setInsuranceValue(double insuranceValue) {
        this.insuranceValue = insuranceValue;
    }
}
