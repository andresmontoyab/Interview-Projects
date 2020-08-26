package infraestructure.converter;

import domain.Drone;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileToDroneConverterTest {

    private static final String PATH_TEST_FILE = "src/test/resources/in/in01.txt";
    private Converter converter;

    @Before
    public void setUp() throws Exception {
        converter = FileToDroneConverter.getInstance();
        writeInformationToTest();

    }

    @Test
    public void fileToDroneConverterTest() throws IOException {
        //given
        Path path = Paths.get(PATH_TEST_FILE);

        //when
        Drone drone = (Drone)converter.convert(path);

        //then
        Assert.assertTrue(drone != null);
        Assert.assertTrue(drone.getId() != null);
        Assert.assertTrue(drone.getPosition() != null);
        Assert.assertTrue(drone.getDeliveries().size() == 3);

    }

    @Test
    public void fileToDroneConverterExceptionTest() throws IOException {
        //given
        Path path = Paths.get("");

        //when
        Drone drone = (Drone)converter.convert(path);

        //then
        Assert.assertTrue(drone == null);

    }

    private void writeInformationToTest() {
        Path path = Paths.get((PATH_TEST_FILE));
        List<String> information = Arrays.asList("A", "A", "A");
        try {
            Files.write(path, information);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void runAfterTestMethod() {
        try {
            Files.deleteIfExists(Paths.get((PATH_TEST_FILE)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}