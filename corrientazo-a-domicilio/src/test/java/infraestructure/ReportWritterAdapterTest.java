package infraestructure;

import domain.Cardinality;
import domain.Drone;
import domain.DroneBuilder;
import domain.Position;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class ReportWritterAdapterTest {

    private ReportWritterAdapter reportWritterAdapter;

    private final String targetFolder = "./src/test/resources/out/out%s.txt";

    @Test
    public void writeTest() {
        //given
        reportWritterAdapter = ReportWritterAdapterBuilder.builder()
                .setPattern("(%s, %s) Direccion %s")
                .setTargetFolder(targetFolder)
                .build();
        List<Drone> drones = createDrones();

        //when
        reportWritterAdapter.write(drones);

        //then
        Assert.assertTrue(countFilesCreated() == drones.size());
    }


    private long countFilesCreated() {
        String folderName = targetFolder.replace("out%s.txt", "");
        try {
            return Files.walk(Paths.get(folderName),1).filter(Files::isRegularFile).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private List<Drone> createDrones() {
        return Arrays.asList(
                createDrone("id1"),
                createDrone("id2"),
                createDrone("id3")
        );
    }

    private Drone createDrone(String id) {
        return DroneBuilder.builder()
                .setId(id)
                .setPosition(new Position(0,0, Cardinality.N))
                .setDeliveredLunches(Arrays.asList(
                        new Position(0,0, Cardinality.N),
                        new Position(1,2, Cardinality.S),
                        new Position(3,5, Cardinality.N)
                ))
                .build();
    }

    @After
    public void runAfterTestMethod() {
        String folderName = targetFolder.replace("out%s.txt", "");
        try {
            Files.walk(Paths.get(folderName), 1)
                    .filter(Files::isRegularFile)
                    .forEach(eachFile -> deleteFile(eachFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFile (Path path){
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}