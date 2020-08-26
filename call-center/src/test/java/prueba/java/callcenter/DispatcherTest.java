package prueba.java.callcenter;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import prueba.java.callcenter.builder.*;
import prueba.java.callcenter.builder.concretas.DirectorBuilder;
import prueba.java.callcenter.builder.concretas.OperadorBuilder;
import prueba.java.callcenter.builder.concretas.SupervisorBuilder;
import prueba.java.callcenter.observer.observador.Dispatcher;
import prueba.java.callcenter.observer.observable.Persona;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

/**
 * Clase encargada de las pruebas unitarias de las funcionalidades
 * de la clase Dispatcher.
 */
public class DispatcherTest {

    private static final Dispatcher DISPATCHER_INSTANCE = Dispatcher.getSingletoonInstance();
    private static final int   NUMERO_LLAMADAS_CONCURRENTES = 10;
    private static final int   TIEMPO_A_DORMIR_DIEZ_LLAMADAS = 30000;
    private static final int   TIEMPO_A_DORMIR_NOTIFICAR_LLAMADA = 26000;
    private static final int   TIEMPO_A_DORMIR_ENCOLAR_LLAMADAS = 1500;
    private static final int   NUMERO_LLAMADAS_A_ENCONLAR = 5;

    private Queue<String> llamdasEncoladas = new LinkedList<String>();
    private PriorityQueue<Persona> empleadosDisponibles = new PriorityQueue<Persona>();
    private AtomicInteger numeroLlamadasProcesandose;
    private PersonaDirector personaDirector;

    @Before
    public void setUp(){

        this.personaDirector = new PersonaDirector();
    }

    /**
     * Prueba unitaria en la cual se testea
     * la funcionalidad de agregar empleado.
     */
    @Test
    public void agregarEmpleadosTest() {
        //given
        personaDirector.setBuilderPerona(new OperadorBuilder());
        personaDirector.contruirPersona();
        Persona operador = personaDirector.getPersona();

        personaDirector.setBuilderPerona(new OperadorBuilder());
        personaDirector.contruirPersona();
        Persona director = personaDirector.getPersona();

        personaDirector.setBuilderPerona(new OperadorBuilder());
        personaDirector.contruirPersona();
        Persona supervisor = personaDirector.getPersona();

        empleadosDisponibles = new PriorityQueue<Persona>();
        Dispatcher.setEmpleadosDisponibles(empleadosDisponibles);
        empleadosDisponibles.add(supervisor);
        empleadosDisponibles.add(director);
        empleadosDisponibles.add(operador);

        //then
        DISPATCHER_INSTANCE.agregarEmpleado(supervisor);
        DISPATCHER_INSTANCE.agregarEmpleado(director);
        DISPATCHER_INSTANCE.agregarEmpleado(operador);

        //then

        assertEquals(empleadosDisponibles.size(), Dispatcher.getEmpleadosDisponibles().size());
        assertEquals(empleadosDisponibles.element().getCargo(),
                Dispatcher.getEmpleadosDisponibles().element().getCargo());

    }

    /**
     * Prueba unitaria que testea la clase Dispatcher
     * con diez llamadas concurrentes
     */
    @Test
    public void diezLlamadasConcurrentesTest() {

        //given
        Dispatcher mockDispacher = Mockito.spy(new Dispatcher());

        personaDirector.setBuilderPerona(new OperadorBuilder());
        personaDirector.contruirPersona();
        Persona operadorUno = personaDirector.getPersona();

        personaDirector.setBuilderPerona(new OperadorBuilder());
        personaDirector.contruirPersona();
        Persona operadorDos = personaDirector.getPersona();

        personaDirector.setBuilderPerona(new OperadorBuilder());
        personaDirector.contruirPersona();
        Persona operadorTres = personaDirector.getPersona();

        personaDirector.setBuilderPerona(new OperadorBuilder());
        personaDirector.contruirPersona();
        Persona operadorCuatro = personaDirector.getPersona();

        personaDirector.setBuilderPerona(new SupervisorBuilder());
        personaDirector.contruirPersona();
        Persona supervisor = personaDirector.getPersona();

        personaDirector.setBuilderPerona(new DirectorBuilder());
        personaDirector.contruirPersona();
        Persona director = personaDirector.getPersona();

        numeroLlamadasProcesandose = new AtomicInteger(0);
        Dispatcher.setNumeroLlamadasProcesandose(numeroLlamadasProcesandose);

        empleadosDisponibles = new PriorityQueue<Persona>();
        Dispatcher.setEmpleadosDisponibles(empleadosDisponibles);

        DISPATCHER_INSTANCE.agregarEmpleado(operadorUno);
        DISPATCHER_INSTANCE.agregarEmpleado(supervisor);
        DISPATCHER_INSTANCE.agregarEmpleado(operadorDos);
        DISPATCHER_INSTANCE.agregarEmpleado(operadorTres);
        DISPATCHER_INSTANCE.agregarEmpleado(director);
        DISPATCHER_INSTANCE.agregarEmpleado(operadorCuatro);

        llamdasEncoladas = new LinkedList<String>();
        Dispatcher.setLlamadasEncoladas(llamdasEncoladas);

        int numeroInicialPersonasDisponibles = Dispatcher.getEmpleadosDisponibles().size();
        int numeroInicialLlamadasPendientes = Dispatcher.getLlamadasEncoladas().size();
        int numeroLlamadasFinales   = 0;

        //when

        for (int i = 0; i < NUMERO_LLAMADAS_CONCURRENTES; i++) {
            Thread llamadosConcurrentes = new Thread(new Runnable() {
                public void run() {
                    DISPATCHER_INSTANCE.dispatchCall();
                }
            });
            llamadosConcurrentes.start();

        }
        mockDispacher.dispatchCall();
        try{
            Thread.sleep(TIEMPO_A_DORMIR_DIEZ_LLAMADAS);
        }catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //then
        assertEquals(numeroLlamadasFinales, Dispatcher.getNumeroLlamadasProcesandose().get());
        assertEquals(numeroInicialPersonasDisponibles, Dispatcher.getEmpleadosDisponibles().size());
        assertEquals(numeroInicialLlamadasPendientes, Dispatcher.getLlamadasEncoladas().size());
    }

    /**
     * Prueba unitaria en la cual se pureba la funcionadalidad
     * de la funcion notificarLlamadaTerminadaTest, en este test se prueban simultaneamente
     * que si hay llamadas pendientes (se ejecuten hasta que no hayan), que se adicionen los empleados que quedaron disponibles y
     * que las llamadas que se esten procesando al final sean 0 (la idea es procesar todas las llamadas).
     */
    @Test
    public void notificarLlamadaTerminadaTest () {

        //given
        Dispatcher mockDispacher = Mockito.spy(new Dispatcher());

        personaDirector.setBuilderPerona(new OperadorBuilder());
        personaDirector.contruirPersona();
        Persona operadorUno = personaDirector.getPersona();

        personaDirector.setBuilderPerona(new OperadorBuilder());
        personaDirector.contruirPersona();
        Persona operadorDos = personaDirector.getPersona();

        empleadosDisponibles = new PriorityQueue<Persona>();
        Dispatcher.setEmpleadosDisponibles(empleadosDisponibles);
        DISPATCHER_INSTANCE.agregarEmpleado(operadorUno);
        DISPATCHER_INSTANCE.agregarEmpleado(operadorDos);

        numeroLlamadasProcesandose = new AtomicInteger(1);
        Dispatcher.setNumeroLlamadasProcesandose(numeroLlamadasProcesandose);

        llamdasEncoladas = new LinkedList<String>();
        llamdasEncoladas.add("Pendiente");
        Dispatcher.setLlamadasEncoladas(llamdasEncoladas);

        int numneroPersonaDisponiblesFinales = empleadosDisponibles.size()  + numeroLlamadasProcesandose.get();
        int numerollamadasPendientesFinal = 0;
        int currentLlamadasFinal    = 0;

        //when
        mockDispacher.notificarLlamadaTerminada(operadorUno);

        try{
            Thread.sleep(TIEMPO_A_DORMIR_NOTIFICAR_LLAMADA);
        }catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //then

        assertEquals(currentLlamadasFinal, Dispatcher.getNumeroLlamadasProcesandose().get());
        assertEquals(numerollamadasPendientesFinal, Dispatcher.getLlamadasEncoladas().size());
        assertEquals(numneroPersonaDisponiblesFinales, Dispatcher.getEmpleadosDisponibles().size());
    }

    /**
     * EXTRA.
     * ¿Mas de 10 Llamadas?
     * ¿Por que resolvi este punto? --> Me parece que cada una de las llamadas debe ser procesada,
     * independientemetne que haya o no disponibilidad en ese momento, por ende la solucion que plantee
     * es, si no se puede procesar la llamada debido a que ya hay 10 en proceso, esta llamada se
     * guardará en una cola de llamadas pendientes y una vez haya disponibilidad esta llamada encolada podrá
     * ser gestionada y procesada.
     */
    @Test
    public void masDeDiezLlamadasTest() {

        //given
        numeroLlamadasProcesandose = new AtomicInteger(10);
        Dispatcher.setNumeroLlamadasProcesandose(numeroLlamadasProcesandose);

        llamdasEncoladas = new LinkedList<String>();
        Dispatcher.setLlamadasEncoladas(llamdasEncoladas);

        int numeroLlamadasEncoladas = 1;

        //when
        DISPATCHER_INSTANCE.dispatchCall();

        //
        assertEquals(numeroLlamadasEncoladas, Dispatcher.getLlamadasEncoladas().size());
        assertEquals(numeroLlamadasProcesandose.get(), Dispatcher.getNumeroLlamadasProcesandose().get());
    }

    /**
     * !Extra!
     * Esta prueba unitaría se encargará de probar la funcionalidad
     * de la cola de llamadas Pendientes, esta cola de llamadas pendientes es la que contendra
     * el numnero de llamadas que falta por procesar.
     */
    @Test
    public void encolarLlamadasTest () {
        //given
        Dispatcher mockDispacher = Mockito.spy(new Dispatcher());

        numeroLlamadasProcesandose = new AtomicInteger(10);
        Dispatcher.setNumeroLlamadasProcesandose(numeroLlamadasProcesandose);

        llamdasEncoladas = new LinkedList<String>();
        Dispatcher.setLlamadasEncoladas(llamdasEncoladas);

        //when
        for (int i = 0; i < NUMERO_LLAMADAS_A_ENCONLAR -1; i++) {
            Thread llamadosConcurrentes = new Thread(new Runnable() {
                public void run() {
                    Dispatcher.getSingletoonInstance().dispatchCall();
                }
            });
            llamadosConcurrentes.start();
        }
        mockDispacher.dispatchCall();
        try{
            Thread.sleep(TIEMPO_A_DORMIR_ENCOLAR_LLAMADAS);
        }catch(InterruptedException e) {
        }

        //
        assertEquals(NUMERO_LLAMADAS_A_ENCONLAR, Dispatcher.getLlamadasEncoladas().size());
        assertEquals(numeroLlamadasProcesandose.get(), Dispatcher.getNumeroLlamadasProcesandose().get());
    }

    /**
     * !Extra!
     * ¿Por que resolvi este punto? --> Independientemente de que hayan no empleados disponibles
     * la llamada deberá ser atendida, quizás con un poco de retraso mientras un empelado se libere.
     * La forma como solucione este punto es con el uso de una cola que gestione las llamadas
     * pendientes y esperar que alguno de nuestro empleados se libere y una ves se libere
     *  podremos gestionar desde nuestro metodo notificarLlamadaTerminada()
     * la ejecucion de la llamada encolada.
     */
    @Test
    public void sinEmpleadosDisponiblesTest(){

        //given
        numeroLlamadasProcesandose = new AtomicInteger(5);
        Dispatcher.setNumeroLlamadasProcesandose(numeroLlamadasProcesandose);
        int llamadasAtendidas = 5;

        llamdasEncoladas = new LinkedList<String>();
        Dispatcher.setLlamadasEncoladas(llamdasEncoladas);
        int llamadasPendientesEsperadas = llamdasEncoladas.size() +1;

        empleadosDisponibles = new PriorityQueue<Persona>();
        Dispatcher.setEmpleadosDisponibles(empleadosDisponibles);

        //when
        DISPATCHER_INSTANCE.dispatchCall();

        //then
        assertEquals(llamadasPendientesEsperadas, Dispatcher.getLlamadasEncoladas().size());
        assertEquals(llamadasAtendidas, Dispatcher.getNumeroLlamadasProcesandose().get());
    }

    /**
     * En esta prueba unitaria se probará que la cola de empelados
     * disponibles si quede ordenada como se espera.
     */
    @Test
    public void ordenColaEmpleadoDisponiblesTest() {

        //given
        personaDirector.setBuilderPerona(new OperadorBuilder());
        personaDirector.contruirPersona();
        Persona operador = personaDirector.getPersona();

        personaDirector.setBuilderPerona(new DirectorBuilder());
        personaDirector.contruirPersona();
        Persona director = personaDirector.getPersona();

        personaDirector.setBuilderPerona(new SupervisorBuilder());
        personaDirector.contruirPersona();
        Persona supervisor = personaDirector.getPersona();

        //when
        empleadosDisponibles = new PriorityQueue<Persona>();
        Dispatcher.setEmpleadosDisponibles(empleadosDisponibles);
        DISPATCHER_INSTANCE.agregarEmpleado(supervisor);
        DISPATCHER_INSTANCE.agregarEmpleado(operador);
        DISPATCHER_INSTANCE.agregarEmpleado(director);

        //then
        assertEquals(Cargos.OPERADOR, Dispatcher.getEmpleadosDisponibles().poll().getCargo());
        assertEquals(Cargos.SUPERVISOR, Dispatcher.getEmpleadosDisponibles().poll().getCargo());
        assertEquals(Cargos.DIRECTOR, Dispatcher.getEmpleadosDisponibles().poll().getCargo());
    }

}