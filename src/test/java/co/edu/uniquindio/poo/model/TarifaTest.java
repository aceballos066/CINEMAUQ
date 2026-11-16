package co.edu.uniquindio.poo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TarifaTest {

    @Test
    void tarifaDebeConservarValor() {
        Tarifa t = new Tarifa(TipoEntrada.ESTUDIANTE, 12000);
        assertEquals(12000, t.getValor(), 0.001);
    }

    @Test
    void tarifaDebeConservarTipo() {
        Tarifa t = new Tarifa(TipoEntrada.NINO, 10000);
        assertEquals(TipoEntrada.NINO, t.getTipoEntrada());
    }
}
