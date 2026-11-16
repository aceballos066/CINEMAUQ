package co.edu.uniquindio.poo.model;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
class MembresiaTest {
    @Test void platinoDebeDescontarVeintePorCiento(){ Membresia m=new Membresia("M1",LocalDate.now(),TipoMembresia.PLATINO); assertEquals(2000,m.calcularDescuento(10000),0.001); }
    @Test void membresiaNuevaDebeEstarActiva(){ assertTrue(new Membresia("M1",LocalDate.now(),TipoMembresia.PLATA).estaActiva()); }
}
