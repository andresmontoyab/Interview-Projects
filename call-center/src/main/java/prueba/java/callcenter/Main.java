package prueba.java.callcenter;

/**
 * Clase main, en la cual se llamará nuestra clase Caller,
 * clase encargada de realizar las llamadas al distpacher.
 */
public class Main {

    public static void main(String[] args) {

        Caller ejecutorLlamadas = new Caller();
        boolean procesoExitoso = ejecutorLlamadas.realizarDiezLlamadas();

    }
}