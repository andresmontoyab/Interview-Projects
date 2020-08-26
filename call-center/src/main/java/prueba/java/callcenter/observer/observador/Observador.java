package prueba.java.callcenter.observer.observador;

import prueba.java.callcenter.observer.observable.Persona;

/**
 * Interface creada bajo el patron Observer, esta interface
 * tiene el trabajo de establecer la firma de los metodos necesarios
 * para el Observador que en nuestro caso será la clase Dispatcher
 */
public interface Observador {

    /**
     * Metodo mediente el cual el sujeto
     * observado notificará al observador
     * @param persona
     */
    void notificarLlamadaTerminada(Persona persona);
}
