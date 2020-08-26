package infraestructure;

import domain.Cardinality;
import domain.Drone;
import domain.DroneBuilder;
import domain.Position;
import infraestructure.converter.Converter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import usecases.DeliverLunchService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ReaderTest {

    private static final String PATH_TEST_FILE = "./src/test/resources/in/";
    private static final List<String> filesName = Arrays.asList("in01.txt", "in02.txt", "in03.txt");
    private static final List<Path> paths = filesName.stream().map(fileName -> Paths.get((PATH_TEST_FILE + fileName))).collect(Collectors.toList());

    private Reader reader;

    private Converter converterMock;
    private DeliverLunchService deliverLunchServiceMock;
    private String sourceFolder;

    @Before
    public void setUp() throws Exception {
        writeInformationToTest();
        converterMock = Mockito.mock(Converter.class);
        deliverLunchServiceMock = Mockito.mock(DeliverLunchService.class);
        sourceFolder= PATH_TEST_FILE;
    }

    @Test
    public void readTestWithCorrectPaths() {
        //given
        reader = ReaderBuilder.builder()
                .setSourceFolder(sourceFolder)
                .setConverter(converterMock)
                .setDeliveryLunch(deliverLunchServiceMock)
                .build();

        when(converterMock.convert(any(Path.class))).thenReturn(createDummyDrone());
        doNothing().when(deliverLunchServiceMock).process(anyListOf(Drone.class));

        //when
        reader.read();

        //then
        verify(deliverLunchServiceMock, times(1)).process(anyListOf(Drone.class));

    }

    @Test
    public void readTestWithIncorrectPaths() {
        //given
        reader = ReaderBuilder.builder()
                .setSourceFolder("WRONG_PATH")
                .setConverter(converterMock)
                .setDeliveryLunch(deliverLunchServiceMock)
                .build();

        //when
        reader.read();

        //then
        verify(converterMock, times(0)).convert(any(Drone.class));
        verify(deliverLunchServiceMock, times(0)).process(anyListOf(Drone.class));

    }

    private Drone createDummyDrone() {
        return DroneBuilder.builder()
                .setId("id")
                .setDeliveries(Arrays.asList("A", "A"))
                .setPosition(new Position(0,0, Cardinality.N))
                .build();
    }

    private void writeInformationToTest() {
        List<String> information = Arrays.asList("A", "A", "A");
        paths.forEach(eachFile -> writeFile(eachFile, information));
    }

    private void writeFile(Path eachFile, List<String> information) {
        try {
            Files.write(eachFile, information);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void runAfterTestMethod() {
        paths.forEach(eachFile -> deleteFiles(eachFile) );
    }

    private void deleteFiles (Path path){
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}