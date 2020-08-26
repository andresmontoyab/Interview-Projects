package prueba.java.callcenter.observer.observable;


import org.apache.log4j.Logger;
import prueba.java.callcenter.builder.Cargos;
import prueba.java.callcenter.observer.observador.Dispatcher;
import prueba.java.callcenter.observer.observador.Observador;

import java.util.Random;

/**
 * La clase persona en nuestro patron observer será la clase observable.
 * Adicionalmente esta clase implementará las interfaces Runnable para el manejo de hilos
 * y Comparable<Persona> para el manejo de una cola con prioridad, que nos ayudará a mantener
 * los empleados ordenados en el orden operador, supervisor, director.
 */
public class Persona implements Observable, Runnable, Comparable<Persona> {

    private Cargos cargo;
    private Observador observador;
    private final  Logger logger = Logger.getLogger(Persona.class);


    /**
     * Definicion Constantes
     */
    private static final Dispatcher DISPATCHER_INSTANCE = Dispatcher.getSingletoonInstance();
    private static final int TIEMPO_MAX_DURACION = 5;
    private static final int TIEMPO_MIN_DURACION = 5;
    private static final int SEGUNDOS_A_MILISEGUNDOS = 1000;

    /**
     * Se le asigna a todas nuestras clases persona un observer que será nuestro dispatcher.
     */
    public Persona() {
        attach(DISPATCHER_INSTANCE);
    }

    /**
     * Constructor con el parametro de Cargos(OPERADOR, SUPERVISOR, DIRECTOR)
     * @param cargo
     */
    public Persona(Cargos cargo) {
        this.cargo = cargo;
        attach(DISPATCHER_INSTANCE);
    }

    /**
     * El metodo run es un metodo perteneciente a la interface Runnable, el cual deberemos implementar
     * para el adecuado manejo de los hilos,
     * 1. Hallamos un tiempo entre 5 y 10 segundos y dormimos el hilo
     * 2. Notificamos a los observadores
     */
    public void run() {
        int tiempoDuracionLlamada;
        try {
            tiempoDuracionLlamada = (new Random().nextInt(TIEMPO_MIN_DURACION)+ TIEMPO_MAX_DURACION) * SEGUNDOS_A_MILISEGUNDOS;
            Thread.sleep(tiempoDuracionLlamada);
            this.notifyObservers();
        } catch (InterruptedException e){
            logger.error("El hilo ha fallado" +e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Metodo fundamental para el ordenamiento de PriorityQueue, este es un
     * metodo que se debe implementar cuando se implementa la interface Comparable,
     * En este definiremos la prioridad de cada uno de los cargos.
     * @param persona
     * @return
     */
    public int compareTo(Persona persona) {
        if (this.getCargo().getCategoriaCargo() == persona.getCargo().getCategoriaCargo()){
            return 0;
        }
        else if(this.getCargo().getCategoriaCargo() < persona.getCargo().getCategoriaCargo()) {
            return -1;
        }
        else {return 1;
        }
    }

    /**
     * Asignamos observador
     * @param dispatcher
     */
    public void attach(Dispatcher dispatcher) {
        observador = dispatcher;
    }

    /**
     * Notificamos al observador
     */
    public void notifyObservers() {
        observador.notificarLlamadaTerminada(this);
    }

    public Cargos getCargo() {
        return cargo;
    }

    public void setCargo(Cargos cargo) {
        this.cargo = cargo;
    }

    public Observador getObservers() {
        return observador;
    }

}
