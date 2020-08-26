package co.ceiba.guardarinsuranceservice.guardar_insurance_service.dominio;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Insurance {

    @Id
    String id;

    User user;
    Property property;
    double costInsurance;


    public double getCostInsurance() {
        return costInsurance;
    }

    public void setCostInsurance(double costInsurance) {
        this.costInsurance = costInsurance;
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


    @Override
    public String toString() {
        return "Insurance{" +
                "id='" + id + '\'' +
                ", user=" + user.toString() +
                ", property=" + property.toString() +
                ", costInsurance=" + costInsurance +
                '}';
    }
}

