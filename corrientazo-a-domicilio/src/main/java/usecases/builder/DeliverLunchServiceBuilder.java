package usecases.builder;

import domain.executor.LunchDispatcher;
import domain.validator.DroneValidation;
import usecases.ConcurrentDronesPort;
import usecases.DeliverLunchService;
import usecases.ReportWritterPort;

public class DeliverLunchServiceBuilder {

    private  LunchDispatcher lunchDispatcher;
    private  ReportWritterPort reportWritter;
    private  DroneValidation droneValidation;
    private ConcurrentDronesPort concurrentDronesPort;

    public static DeliverLunchServiceBuilder builder() {
        return new DeliverLunchServiceBuilder();
    }

    public DeliverLunchServiceBuilder setLunchDispatcher(LunchDispatcher lunchDispatcher) {
        this.lunchDispatcher = lunchDispatcher;
        return this;
    }

    public DeliverLunchServiceBuilder setReportWritter(ReportWritterPort reportWritter) {
        this.reportWritter = reportWritter;
        return this;
    }

    public DeliverLunchServiceBuilder setDroneValidation(DroneValidation droneValidation) {
        this.droneValidation = droneValidation;
        return this;
    }

    public DeliverLunchServiceBuilder setConcurrentDronesPort(ConcurrentDronesPort concurrentDronesPort) {
        this.concurrentDronesPort = concurrentDronesPort;
        return this;
    }

    public DeliverLunchService build() {
        return new DeliverLunchService(lunchDispatcher, reportWritter, droneValidation, concurrentDronesPort);
    }

}
