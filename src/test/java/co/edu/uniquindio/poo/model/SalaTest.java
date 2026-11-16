package co.edu.uniquindio.poo.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class SalaTest {
    @Test void salaDebeGenerarCapacidadIndicada(){ Sala s=new Sala("S1","Sala",6,2,3,TipoSala.TRADICIONAL); assertEquals(6,s.getAsientos().size()); }
    @Test void salaDeshabilitadaNoDebeEstarDisponible(){ Sala s=new Sala("S1","Sala",6,2,3,TipoSala.TRADICIONAL); s.deshabilitar(); assertFalse(s.estaDisponible()); }
}
