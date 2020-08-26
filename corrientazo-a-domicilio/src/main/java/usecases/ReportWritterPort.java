package usecases;

import domain.Drone;

import java.util.List;

public interface ReportWritterPort {
    void write(List<Drone> drones);
}
