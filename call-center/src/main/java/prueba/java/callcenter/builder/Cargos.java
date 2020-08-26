package prueba.java.callcenter.builder;

/**
 * Este enum contendrá los tres posibles cargos para los empleados del  callcenter.
 * Adicionalmente cada cargo tendrá asignado un numero con el cual calcularemos quien deberá contestar primero
 */
public enum Cargos {
    OPERADOR(1),
    SUPERVISOR(2),
    DIRECTOR(3);

    private int categoriaCargo;

    Cargos(int categoriaCargo) {
        this.categoriaCargo = categoriaCargo;
    }

    public int getCategoriaCargo() {
        return categoriaCargo;
    }
}
