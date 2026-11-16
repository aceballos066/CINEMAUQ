
package co.edu.uniquindio.poo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cliente extends Persona {
    private String codAfiliacion;
    private TipoCliente tipoCliente;
    private Membresia membresia;
    private final List<Venta> ventas;

    public Cliente(String identificacion, String nombre, String codAfiliacion, TipoCliente tipoCliente) {
        super(identificacion, nombre);
        setCodAfiliacion(codAfiliacion);
        setTipoCliente(tipoCliente);
        this.ventas = new ArrayList<>();
    }

    public boolean tieneMembresia() {
        return membresia != null && membresia.estaActiva();
    }

    public double obtenerDescuento() {
        if (!tieneMembresia()) return 0;
        return membresia.getTipoMembresia().getPorcentajeDescuento();
    }

    public void agregarVenta(Venta venta) {
        if (venta != null && !ventas.contains(venta))
            ventas.add(venta);
    }

    public String getCodAfiliacion() { return codAfiliacion; }

    public void setCodAfiliacion(String codAfiliacion) {
        if (codAfiliacion == null || codAfiliacion.isBlank())
            throw new IllegalArgumentException("El código de afiliación es obligatorio");
        this.codAfiliacion = codAfiliacion.trim();
    }

    public TipoCliente getTipoCliente() { return tipoCliente; }

    public void setTipoCliente(TipoCliente tipoCliente) {
        if (tipoCliente == null)
            throw new IllegalArgumentException("El tipo de cliente es obligatorio");
        this.tipoCliente = tipoCliente;
    }

    public Membresia getMembresia() { return membresia; }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }

    public List<Venta> getVentas() {
        return Collections.unmodifiableList(ventas);
    }

    @Override
    public String toString() {
        String texto = getIdentificacion() + " - " + getNombre() + " - " + tipoCliente;
        return tieneMembresia()
                ? texto + " - " + membresia.getTipoMembresia().name()
                : texto + " - SIN MEMBRESÍA";
    }
}
