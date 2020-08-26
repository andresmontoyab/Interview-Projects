package prueba.java.callcenter.observer.observador;

import org.apache.log4j.Logger;
import prueba.java.callcenter.observer.observable.Persona;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Dispatcher tiene la funcion de coordinar cada una de las llamadas,
 * en esta clase se tendrá un alto nivel de concurrencia por ende se deberá
 * manejar con cuidado una buena implementacion thread-safe, adicionalmente
 * la clase Distpacher es el observador en nuestro patron observer y observa
 * a cada uno de los hilos de la clase Persona.
 */
public class Dispatcher implements Observador {

    private static Dispatcher dispatcher;

    // Variable que almacenará las llamadas que no han podido ser procesadas
    private static Queue<String> llamadasEncoladas = new LinkedList<String>();

    // Variable que será una cola de empleados disponibles en el orden operador, supervisor, director.
    private static PriorityQueue<Persona> empleadosDisponibles = new PriorityQueue<Persona>();

    // Variable utilizada para el manejo de thread-safe en las llamadas que se esten procesando.
    private static AtomicInteger numeroLlamadasProcesandose = new AtomicInteger(0);
    private final Logger logger = Logger.getLogger(Persona.class);
    private final static String LLAMADA_A_ENCOLAR = "Pendiente";

    /**
     * Implementacion del patron singletoon, este patron
     * solo permitirá una instancia de nuestra clase distpacher.
     * @return
     */
    public static Dispatcher getSingletoonInstance() {
        if (dispatcher == null) {
            dispatcher = new Dispatcher();
        }
        return dispatcher;
    }

    /**
     * Este metodo es llamado por el sujeto observable cuando la llamada acabe
     * 1. Debido a que una llamada termino un empleado mas estará disponible.
     * 2. Una llamada que termino de procesarse
     * 3. Validaremos que no haya llamadas encoladas
     * 4. En caso de haberlas llamamos a nuestro distpacher.
     * @param persona
     */
    public synchronized void notificarLlamadaTerminada(Persona persona) {

        agregarEmpleado(persona);
        numeroLlamadasProcesandose.decrementAndGet();

        logger.info("Un " + persona.getCargo() +" ha terminado una llamada, el numero de llamadas actualmente en proceso es "+ numeroLlamadasProcesandose.get());

        synchronized (llamadasEncoladas) {
            if (!llamadasEncoladas.isEmpty()) {
                logger.info("Su llamada estaba en espera, ahora procederemos a atenderla.");
                llamadasEncoladas.poll();
                dispatchCall();
            }
        }
    }

    /**
     * Agregamos un nuevo empleado a la cola de empleados.
     * @param persona
     */
    public synchronized void agregarEmpleado(Persona persona) {
        synchronized (empleadosDisponibles) {
            empleadosDisponibles.offer(persona);
        }
    }


    /**
     * Metodo que se encargará de gestionar la distribucion de las llamadas entre los empleados.
     * 1. Si hay mas de 10 llamadas procesandose, no se podra gestionar la llamada y se encolará
     * 2. Si no hay empleados disponibles, no se podra gestionar la llamada y se encolará
     * 3. Si hay empleados disponbles y menos de diez llamadas, se asignará la llamada a un empleado
     */
    public synchronized void dispatchCall() {
        synchronized (empleadosDisponibles) {
            if(numeroLlamadasProcesandose.get() < 10 ){
                if (!empleadosDisponibles.isEmpty()) {
                   numeroLlamadasProcesandose.incrementAndGet();
                   logger.info("Su llamada será contestada por un "+ empleadosDisponibles.element().getCargo() + "" +
                               " y es la llamada numero "+ numeroLlamadasProcesandose.get());
                    Thread asignarLlamada = new Thread(empleadosDisponibles.poll());
                    asignarLlamada.start();
                } else {
                    logger.info("En este instante su llamada no puede ser procesada, en un momento lo atenderemos.");
                    llamadasEncoladas.offer(LLAMADA_A_ENCOLAR);
                }
            } else {
                llamadasEncoladas.offer(LLAMADA_A_ENCOLAR);
                logger.info("La llamada no pudo ser procesada, el cupo maximo de 10 llamadas esta lleno.");
            }
        }
    }

    public static Queue<String> getLlamadasEncoladas() {
        return llamadasEncoladas;
    }

    public static void setLlamadasEncoladas(Queue<String> llamadasPendiente) {
        llamadasEncoladas = llamadasPendiente;
    }

    public static PriorityQueue<Persona> getEmpleadosDisponibles() {
        return empleadosDisponibles;
    }

    public static void setEmpleadosDisponibles(PriorityQueue<Persona> empleadosDisponibles) {
        Dispatcher.empleadosDisponibles = empleadosDisponibles;
    }

    public static AtomicInteger getNumeroLlamadasProcesandose() {
        return numeroLlamadasProcesandose;
    }

    public static void setNumeroLlamadasProcesandose(AtomicInteger numeroLlamadasProcesandose) {
        Dispatcher.numeroLlamadasProcesandose = numeroLlamadasProcesandose;
    }




}

