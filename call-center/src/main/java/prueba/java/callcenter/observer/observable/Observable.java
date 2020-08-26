package prueba.java.callcenter.observer.observable;


import prueba.java.callcenter.observer.observador.Dispatcher;

/**
 * Observable es una interface perteneciente al patron Observer, esta interface
 * la deberá implementar la clase que esta siendo observada, es decir Persona.
 */
public interface Observable {

    /**
     * Le asignará a la clase que implemente esta interface un observador
     * @param dispatcher
     */
    void attach(Dispatcher dispatcher);

    /**
     * Con base en el patron observar, este metodo se encargará de notificar
     * a los observadores cuando el evento esperado haya sido lanzado.
     */
    void notifyObservers();


}
