package co.edu.uniquindio.poo.model;
import org.junit.jupiter.api.Test;
import java.time.*;
import static org.junit.jupiter.api.Assertions.*;
class VentaTest {
    private Venta crear(){ Cliente c=new Cliente("1","Ana","A1",TipoCliente.GENERAL); Operario o=new Operario("2","Op","OP1",LocalDate.now(),1000); Funcion f=new Funcion("F1",new Pelicula("P1","P",Genero.ACCION,100,Clasificacion.TODOS),new Sala("S1","S",4,2,2,TipoSala.TRADICIONAL),LocalDate.now(),LocalTime.NOON,15000); Asiento a=f.buscarAsiento("A1"); Boleta b=new Boleta("B1",TipoEntrada.GENERAL,15000); return new Venta("V1",c,o,f,a,b,15000); }
    @Test void ventaNuevaDebeEstarActiva(){ assertTrue(crear().estaActiva()); }
    @Test void ventaCanceladaNoDebeEstarActiva(){ Venta v=crear(); v.cancelar(); assertFalse(v.estaActiva()); }
}
