package co.edu.uniquindio.poo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PeliculaTest {

    @Test
    void peliculaNuevaDebeEstarDisponible() {
        Pelicula p = new Pelicula("P1", "Prueba", Genero.DRAMA, 100, Clasificacion.TODOS);
        assertTrue(p.estaDisponible());
    }

    @Test
    void peliculaDeshabilitadaNoDebeEstarDisponible() {
        Pelicula p = new Pelicula("P1", "Prueba", Genero.DRAMA, 100, Clasificacion.TODOS);
        p.deshabilitar();
        assertFalse(p.estaDisponible());
    }
}
