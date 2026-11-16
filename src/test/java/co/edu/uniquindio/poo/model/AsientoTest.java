package co.edu.uniquindio.poo.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class AsientoTest {
    @Test void asientoNuevoDebeEstarDisponible(){ assertTrue(new Asiento("A1","A",1).estaDisponible()); }
    @Test void asientoOcupadoNoDebeEstarDisponible(){ Asiento a=new Asiento("A1","A",1); a.ocupar(); assertFalse(a.estaDisponible()); }
}
