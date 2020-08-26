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

import static org.junit.Assert.*;

/***
 * Clase encagarga de prbar las difernetes funcionalidades de
 * la clase Persona.
 */
public class PersonaTest {

    PersonaDirector personaDirector;

    // Instanca de la clase Dispatcher
    private static final Dispatcher DISPATCHER_INSTANCE = Dispatcher.getSingletoonInstance();
    private static final int    SEGUNDOS_DORMIR_HILO_INTERRUMPIDO = 1500;

    @Before
    public void setUp() {
       this.personaDirector = new PersonaDirector();
    }

    /**
     * Prueba unitaria de la creacion de nuestra clase concreta
     * OperadorBuilder
     */
    @Test
    public void usandoBuilderOperador() {
        personaDirector.setBuilderPerona(new OperadorBuilder());
        personaDirector.contruirPersona();
        Persona operador = personaDirector.getPersona();

        assertEquals(Cargos.OPERADOR, operador.getCargo());
    }

    /**
     * Prueba unitaria de la creacion de nuestra clase concreta
     * SupervisorBuilder
     */
    @Test
    public void usandoBuilderSupervisor() {
        personaDirector.setBuilderPerona(new SupervisorBuilder());
        personaDirector.contruirPersona();
        Persona supervisor = personaDirector.getPersona();

        assertEquals(Cargos.SUPERVISOR, supervisor.getCargo());
    }

    /**
     * Prueba unitaria de la creacion de nuestra clase concreta
     * DirectorBuilder
     */
    @Test
    public void usandoBuilderDirector() {
        personaDirector.setBuilderPerona(new DirectorBuilder());
        personaDirector.contruirPersona();
        Persona director = personaDirector.getPersona();

        assertEquals(Cargos.DIRECTOR, director.getCargo());
    }

    /**
     * Prueba unitaria para el constructor de nuestra clase persona
     * con el argumento de Cargos.
     */
    @Test
    public void constructorConCargo(){
        //when, given
        Persona director = new Persona(Cargos.DIRECTOR);
        // then
        assertEquals(Cargos.DIRECTOR, director.getCargo());
        assertEquals(DISPATCHER_INSTANCE, director.getObservers());
    }

    /**
     * Prueba unitaria para el constructor de nuestra clase persona
     * sin argunmentos
     */
    @Test
    public void constructorPersonaSinArgumentosTest() {
        Persona director = new Persona();
        director.setCargo(Cargos.DIRECTOR);

        assertEquals(Cargos.DIRECTOR, director.getCargo());
        assertEquals(DISPATCHER_INSTANCE, director.getObservers());
    }


    /**
     * Prueba unitaria en la cual se prueba la funcionalidad de nuestro metodo compareTo()
     * Este metodo funciona de la siguiente manera, el comparará cada uno de los cargos Operador(1),
     * Supervisor(2) y Director(3) y en los cuales se presentará los siguientes escenarios.
     * 1 < 2 --> -1
     * 1 < 3 --> -1
     * 1 = 1 --> 0
     * 2 > 1 --> 1
     * Con base en estos tres resultados (0,1,-1)seran ubicados en la cola en donde
     * los mas cercanos a la cola seran aquellos que tengan mas alto numero y los mas cercanos
     * a la cabeza seran los que tengan menor numero, asi garantizamos que sean primero obtenidos los
     * operadores, luego los supervisore y como ultimo los directores.
     */
    @Test
    public void compareToTest() {
        //when
        personaDirector.setBuilderPerona(new OperadorBuilder());
        personaDirector.contruirPersona();
        Persona operador = personaDirector.getPersona();

        personaDirector.setBuilderPerona(new SupervisorBuilder());
        personaDirector.contruirPersona();
        Persona supervisor = personaDirector.getPersona();

        personaDirector.setBuilderPerona(new DirectorBuilder());
        personaDirector.contruirPersona();
        Persona director = personaDirector.getPersona();

        int resultadoOperadorUno    =  operador.compareTo(director);
        int resultadoOperadorDos    =  operador.compareTo(supervisor);
        int resultadoOperadorTres   =  operador.compareTo(operador);
        int resultadoSupervisorUno  = supervisor.compareTo(director);
        int resultadoSupervisorDos  = supervisor.compareTo(operador);
        int resultadoSupervisorTres = supervisor.compareTo(supervisor);
        int resultadoDirector1Uno   = director.compareTo(operador);
        int resultadoDirectorDos    = director.compareTo(supervisor);
        int resultadoDirectorTres   = director.compareTo(director);

        //then
        assertEquals(-1, resultadoOperadorUno);
        assertEquals(-1, resultadoOperadorDos);
        assertEquals(0, resultadoOperadorTres);
        assertEquals(-1, resultadoSupervisorUno);
        assertEquals(1, resultadoSupervisorDos);
        assertEquals(0, resultadoSupervisorTres);
        assertEquals(1, resultadoDirector1Uno);
        assertEquals(1, resultadoDirectorDos);
        assertEquals(0, resultadoDirectorTres);
    }

    /**
     * Prueba unitaria en la cual se lanzar un hilo y se verifica su
     * lanzamiento
     */
    @Test
    public void lanzarHiloTest() {
        personaDirector.setBuilderPerona(new DirectorBuilder());
        personaDirector.contruirPersona();
        Persona director = personaDirector.getPersona();

        Thread mockThread = Mockito.spy(new Thread(director));

        mockThread.start();
        Mockito.verify(mockThread).start();
    }

    /**
     * Prueba unitaria en la cual se verifica
     * el interrumpimiento de un hilo
     */
    @Test
    public void hiloInterrumpidoTest () {

        personaDirector.setBuilderPerona(new DirectorBuilder());
        personaDirector.contruirPersona();
        Persona director = personaDirector.getPersona();

        Persona mockPersona = Mockito.spy(director);
        Thread threadTest = new Thread(director);

        threadTest.start();
        threadTest.interrupt();

        mockPersona.run();
        try {
            Thread.sleep(SEGUNDOS_DORMIR_HILO_INTERRUMPIDO);
        }catch (InterruptedException e) {
        }

        assert(!threadTest.isAlive());

    }

}