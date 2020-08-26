package prueba.java.callcenter.builder.concretas;

import prueba.java.callcenter.builder.BuilderPersona;
import prueba.java.callcenter.builder.Cargos;

/**
 * Con base en el patron builder esta clase es la definicion de una de las clases concretas a crear,
 * en este caso la clase concreta es la del OperadorBuilder
 *  Adicionalmente esta clase deberá implementar los metodos definidos por la clase abstracta BuilderPersona
 */
public class OperadorBuilder extends BuilderPersona {

    /**
     * Asignar el cargo de OPERADOR
     */
    public void construirCargo() {
        persona.setCargo(Cargos.OPERADOR);
    }
}
