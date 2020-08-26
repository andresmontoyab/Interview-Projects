package infraestructure;

import domain.Drone;
import infraestructure.converter.Converter;
import usecases.DeliverLunchService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reader {

    private final Converter converter;
    private final DeliverLunchService deliverLunchService;
    private final String sourceFolder;

    private Logger logger = domain.Logger.getLogger();

    public Reader(Converter converter, DeliverLunchService deliverLunchService, String sourceFolder) {
        this.converter = converter;
        this.deliverLunchService = deliverLunchService;
        this.sourceFolder = sourceFolder;
    }

    public void read() {
        try (Stream<Path> paths = Files.walk(Paths.get(sourceFolder), 1)) {
            List<Path> foundFiles = paths
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());

            List<Drone> drones = foundFiles.stream()
                    .map(file -> (Drone)converter.convert(file))
                    .collect(Collectors.toList());

            deliverLunchService.process(drones);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "There was an error reading the path " + sourceFolder);
        }
    }
}
