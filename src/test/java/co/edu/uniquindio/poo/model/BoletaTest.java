package co.edu.uniquindio.poo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoletaTest {

    @Test
    void boletaDebeConservarValorPagado() {
        Boleta b = new Boleta("B1", TipoEntrada.GENERAL, 15000);
        assertEquals(15000, b.getValorPagado(), 0.001);
    }

    @Test
    void informacionDebeIncluirCodigo() {
        Boleta b = new Boleta("B1", TipoEntrada.GENERAL, 15000);
        assertTrue(b.generarInformacion().contains("B1"));
    }
}
