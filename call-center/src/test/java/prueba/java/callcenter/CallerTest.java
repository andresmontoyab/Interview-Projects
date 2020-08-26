package prueba.java.callcenter;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Clase de test para nuestro clase Caller
 */
public class CallerTest {

    private static final int TIEMPO_A_DORMIR = 25000;

    /**
     * Se realiza prueba unitaría para la implementacion de el metodo
     * realizarDiezLlamadas(), en el cual simulará 10 llamadas simultaneas
     */
    @Test
    public void realizarDiezLlamdasTest() {
        //given, when
        Caller mockCaller = Mockito.spy(new Caller());

        boolean terminoEjecucionbien = mockCaller.realizarDiezLlamadas();
        try{
            Thread.sleep(TIEMPO_A_DORMIR);
        }catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        //then
        assertEquals(true, terminoEjecucionbien);

    }

}