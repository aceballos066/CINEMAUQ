package co.edu.uniquindio.poo.model;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
class ClienteTest {
    @Test void clienteSinMembresiaDebeTenerDescuentoCero(){ Cliente c=new Cliente("1","Ana","A1",TipoCliente.GENERAL); assertEquals(0,c.obtenerDescuento(),0.001); }
    @Test void clienteConOroDebeTenerTreintaPorCiento(){ Cliente c=new Cliente("1","Ana","A1",TipoCliente.GENERAL); c.setMembresia(new Membresia("M1",LocalDate.now(),TipoMembresia.ORO)); assertEquals(30,c.obtenerDescuento(),0.001); }
}
