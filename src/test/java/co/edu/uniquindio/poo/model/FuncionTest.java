package co.edu.uniquindio.poo.model;

import org.junit.jupiter.api.Test;
import java.time.*;
import static org.junit.jupiter.api.Assertions.*;

class FuncionTest {

    private Funcion crear() {
        return new Funcion(
                "F1",
                new Pelicula("P1", "P", Genero.ACCION, 100, Clasificacion.TODOS),
                new Sala("S1", "S", 4, 2, 2, TipoSala.TRADICIONAL),
                LocalDate.now(),
                LocalTime.of(18, 0),
                15000
        );
    }

    @Test
    void funcionNuevaDebeEstarProgramada() {
        assertTrue(crear().estaProgramada());
    }

    @Test
    void ocuparUnAsientoDebeReducirDisponibles() {
        Funcion f = crear();
        f.buscarAsiento("A1").ocupar();
        assertEquals(3, f.contarAsientosDisponibles());
    }
}

