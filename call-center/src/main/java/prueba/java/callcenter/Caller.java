package prueba.java.callcenter;

import prueba.java.callcenter.builder.*;
import prueba.java.callcenter.builder.concretas.DirectorBuilder;
import prueba.java.callcenter.builder.concretas.OperadorBuilder;
import prueba.java.callcenter.builder.concretas.SupervisorBuilder;
import prueba.java.callcenter.observer.observador.Dispatcher;
import prueba.java.callcenter.observer.observable.Persona;

/**
 * Clase encargada de realizar las llamadas a el dispatcher,
 * en esta clase simularemos 10 llamadas concurrentes y nuestro distpacher
 * deberá poder gestionarlas
 */
public class Caller {

    private static final Dispatcher dispatcher = Dispatcher.getSingletoonInstance();
    private static final int NUMERO_LLAMADAS_CONCURRENTES = 10;

    public boolean realizarDiezLlamadas() {

        boolean procesoExitoso = false;

        // Creacion de nuestros empleados

        PersonaDirector personaDirector = new PersonaDirector();

        personaDirector.setBuilderPerona(new OperadorBuilder());
        personaDirector.contruirPersona();
        Persona operadorUno = personaDirector.getPersona();

        personaDirector.setBuilderPerona(new OperadorBuilder());
        personaDirector.contruirPersona();
        Persona operadorDos = personaDirector.getPersona();

        personaDirector.setBuilderPerona(new OperadorBuilder());
        personaDirector.contruirPersona();
        Persona operadorTres = personaDirector.getPersona();

        personaDirector.setBuilderPerona(new SupervisorBuilder());
        personaDirector.contruirPersona();
        Persona supervisorUno = personaDirector.getPersona();

        personaDirector.setBuilderPerona(new DirectorBuilder());
        personaDirector.contruirPersona();
        Persona directoUno = personaDirector.getPersona();

        // Asignar nuestro empleados creados a la cola de empleados
        dispatcher.agregarEmpleado(operadorUno);
        dispatcher.agregarEmpleado(operadorDos);
        dispatcher.agregarEmpleado(operadorTres);
        dispatcher.agregarEmpleado(supervisorUno);
        dispatcher.agregarEmpleado(directoUno);

        // Realizar el llamado concurrente a el dispatcher
        for (int i = 0; i < NUMERO_LLAMADAS_CONCURRENTES; i++) {
            Thread llamadosConcurrentes = new Thread(new Runnable() {
                public void run() {
                    dispatcher.dispatchCall();
                }
            });

            llamadosConcurrentes.start();
        }
        procesoExitoso = true;
        return procesoExitoso;

    }

}
