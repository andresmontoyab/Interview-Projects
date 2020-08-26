package infraestructure;

import domain.Drone;
import domain.Position;
import usecases.ReportWritterPort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ReportWritterAdapter implements ReportWritterPort {

    //"./src/main/resources/output/out%s.txt"
    //
    private final String pattern;
    private final String targetFolder;

    public ReportWritterAdapter(String pattern, String targetFolder) {
        this.pattern = pattern;
        this.targetFolder = targetFolder;
    }

    @Override
    public void write(List<Drone> drones) {
        drones.forEach(drone -> {
            List<String> information = getDeliveredLunchsInformation(drone.getDeliveredLunches());
            writeInformationInOutFile(drone, information);
        });
    }

    private List<String> getDeliveredLunchsInformation(List<Position> deliveredLunches) {
        return deliveredLunches.stream()
                .map(x -> String.format(pattern, x.getX(), x.getY(), x.getCardinality().getDescription()))
                .collect(Collectors.toList());
    }

    private void writeInformationInOutFile(Drone drone, List<String> information)  {
        Path path = Paths.get(String.format(targetFolder, drone.getId()));
        try {
            Files.write(path, information);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
