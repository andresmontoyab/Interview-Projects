import domain.actions.builder.DeliverLunchActionBuilder;
import domain.executor.LunchDispatcher;
import domain.validator.AmountLunchValidator;
import domain.validator.DroneValidation;
import infraestructure.Reader;
import infraestructure.ReaderBuilder;
import infraestructure.ReportWritterAdapter;
import infraestructure.ReportWritterAdapterBuilder;
import infraestructure.converter.FileToDroneConverter;
import usecases.DeliverLunchService;
import usecases.ReportWritterPort;
import usecases.builder.DeliverLunchServiceBuilder;

public class Starter {
    public static void main(String[] args) {
        FileToDroneConverter fileToDroneConverter = FileToDroneConverter.getInstance();
        LunchDispatcher lunchDispatcher = LunchDispatcher.getInstance();

        ReportWritterPort reportWritter = ReportWritterAdapterBuilder.builder()
                .setPattern("(%s, %s) Direccion %s")
                .setTargetFolder("./src/main/resources/output/out%s.txt")
                .build();

        DroneValidation droneValidation = AmountLunchValidator.getInstance();

        DeliverLunchService deliverLunchService = DeliverLunchServiceBuilder.builder()
                .setLunchDispatcher(lunchDispatcher)
                .setDroneValidation(droneValidation)
                .setReportWritter(reportWritter)
                .build();

        Reader reader = ReaderBuilder.builder()
                .setConverter(fileToDroneConverter)
                .setDeliveryLunch(deliverLunchService)
                .setSourceFolder("./src/main/resources/input")
                .build();

        reader.read();
    }
}
