package infraestructure.converter;

import domain.Cardinality;
import domain.Drone;
import domain.DroneBuilder;
import domain.Position;
import domain.actions.factory.ActionFactory;
import domain.validator.AmountLunchValidator;
import infraestructure.ReportWritterAdapter;
import usecases.ReportWritterPort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class FileToDroneConverter implements Converter<Path, Drone> {

    private static FileToDroneConverter instance;
    private Logger logger = domain.Logger.getLogger();

    private FileToDroneConverter() {}

    @Override
    public Drone convert(Path source) {
        try {
            List<String> deliveries = Files.lines(source).collect(Collectors.toList());
            Drone drone = buildDrone(source.getFileName(), deliveries);
            return drone;
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "There was an expcetion trying to read the path " + source);
        }
        return null;
    }

    private Drone buildDrone(Path fileName, List<String> deliveries) {
        String id = extractId(fileName.toString());
        return DroneBuilder.builder()
                    .setId(id)
                    .setDeliveries(deliveries)
                    .setPosition(createInitialPosition())
                    .setDeliveredLunches(new ArrayList<>())
                    .setDroneAction(ActionFactory.getDroneAction(ActionFactory.Types.LUNCH_TYPE))
                    .build();
    }

    private Position createInitialPosition() {
        return new Position(0,0, Cardinality.N);
    }

    private String extractId(String fileName) {
        String fileNameWithoutIn = fileName.replace("in", "");
        String fileNameWithoutExtension = fileNameWithoutIn.replace(".txt", "");
        return fileNameWithoutExtension;
    }

    public static synchronized FileToDroneConverter getInstance() {
        if (instance == null) {
            instance = new FileToDroneConverter();
        }
        return instance;
    }
}
