package prueba.java.callcenter.builder;

import prueba.java.callcenter.observer.observador.Dispatcher;
import prueba.java.callcenter.observer.observable.Persona;

/**
 * BuilderPersona es una clase abstracta utilizada en el patron Builder la cual se encargará de definir los metodos
 * que las clases concretas deberan implementar.
 */
public abstract class BuilderPersona {

    /**
     * Se compone con el objeto Persona
     */
    protected Persona persona;

    public Persona getPersona() {
        return  this.persona;
    }

    /**
     * Crea una persona y le asigna inmediatamente un observador.
     */
    public void crearPersona() {
        persona = new Persona();
        persona.attach(Dispatcher.getSingletoonInstance());
    }

    /**
     * Metodos Abstractos que las clases concretas deben implementar.
     */
    public abstract void construirCargo();
}
