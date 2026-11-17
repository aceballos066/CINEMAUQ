package co.edu.uniquindio.poo.model;
import co.edu.uniquindio.poo.DatosIniciales;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CineTest {
    @Test void cineDebeIniciarConDiezPeliculas(){ assertEquals(10,DatosIniciales.crearCine().obtenerPeliculas().size()); }
    @Test void venderEntradaDebeOcuparAsiento(){ Cine cine=DatosIniciales.crearCine(); Cliente c=cine.buscarCliente("CL-001"); Operario o=cine.buscarOperario("OPE001"); cine.venderEntrada(c,o,"F004",TipoEntrada.GENERAL,"A3"); assertFalse(cine.buscarFuncion("F004").buscarAsiento("A3").estaDisponible()); }
}
