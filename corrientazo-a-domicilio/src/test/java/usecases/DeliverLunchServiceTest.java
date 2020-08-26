package usecases;

import domain.Cardinality;
import domain.Drone;
import domain.DroneBuilder;
import domain.Position;
import domain.actions.factory.ActionFactory;
import domain.executor.LunchDispatcher;
import domain.validator.DroneValidation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import usecases.builder.DeliverLunchServiceBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DeliverLunchServiceTest {

    private DeliverLunchService deliverLunchService;

    private LunchDispatcher lunchDispatcherMock;
    private ReportWritterPort reportWritterMock;
    private DroneValidation droneValidationMock;
    private ConcurrentDronesPort concurrentDronesPortMock;

    @Before
    public void setUp() throws Exception {
        lunchDispatcherMock = Mockito.mock(LunchDispatcher.class);
        reportWritterMock = Mockito.mock(ReportWritterPort.class);
        droneValidationMock = Mockito.mock(DroneValidation.class);
        concurrentDronesPortMock = Mockito.mock(ConcurrentDronesPort.class);
        deliverLunchService = DeliverLunchServiceBuilder.builder()
                .setLunchDispatcher(lunchDispatcherMock)
                .setReportWritter(reportWritterMock)
                .setDroneValidation(droneValidationMock)
                .setConcurrentDronesPort(concurrentDronesPortMock)
                .build();
    }

    @Test
    public void processTest() {
        //given
        List<Drone> drones = createDrones();
        Mockito.when(concurrentDronesPortMock.getAmount()).thenReturn(Integer.valueOf(1));
        Mockito.when(lunchDispatcherMock.dispatch(anyListOf(Drone.class),any(Integer.class))).thenReturn(createDrones());
        Mockito.doNothing().when(reportWritterMock).write(anyListOf(Drone.class));

        //when
        deliverLunchService.process(drones);

        //then
        verify(concurrentDronesPortMock , times(1)).getAmount();
        verify(lunchDispatcherMock , times(1)).dispatch(anyListOf(Drone.class), any(Integer.class));
        verify(reportWritterMock , times(1)).write(anyListOf(Drone.class));
        verify(droneValidationMock , times(createDrones().size())).validate(any(Drone.class));
    }

    private List<Drone> createDrones() {
        return Arrays.asList(createBuilderWithId("id1"), createBuilderWithId("id2"), createBuilderWithId("id3"));
    }

    private Drone createBuilderWithId(String id) {
        return DroneBuilder.builder()
                .setId(id)
                .setPosition(new Position(0,0, Cardinality.N))
                .setDeliveredLunches(new ArrayList<>())
                .setDroneAction(ActionFactory.getDroneAction(ActionFactory.Types.LUNCH_TYPE))
                .setDeliveries(Arrays.asList("A", "A", "I"))
                .build();
    }
}