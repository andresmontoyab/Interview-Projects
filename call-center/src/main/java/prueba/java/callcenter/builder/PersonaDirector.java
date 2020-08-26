package prueba.java.callcenter.builder;

import prueba.java.callcenter.observer.observable.Persona;

/**
 * PersonaDirector se encargará de construir un objeto con base a el tipo de objeto y
 * a la clase abstracta BuilderPersona.
 */
public class PersonaDirector {

    private BuilderPersona builderPersona;

    /**
     * Se construyen las porpiedades necesarias para
     * el objeto con base en la clase BuilderPersona
     */
    public void contruirPersona() {
        builderPersona.crearPersona();
        builderPersona.construirCargo();
    }

    public void setBuilderPerona(BuilderPersona builder) {
        builderPersona = builder;
    }

    public Persona getPersona() {
        return builderPersona.getPersona();
    }
}
