package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.exception.MembresiaInvalidaException;
import java.time.LocalDate;

public class Membresia {
    private String codigoMembresia;
    private LocalDate fechaInicio;
    private int puntos;
    private boolean activa;
    private TipoMembresia tipoMembresia;

    public Membresia(String codigoMembresia, LocalDate fechaInicio, TipoMembresia tipoMembresia) {
        if (codigoMembresia == null || codigoMembresia.isBlank()) throw new MembresiaInvalidaException("El código de membresía es obligatorio");
        if (fechaInicio == null) throw new MembresiaInvalidaException("La fecha de inicio es obligatoria");
        if (tipoMembresia == null) throw new MembresiaInvalidaException("El tipo de membresía es obligatorio");
        this.codigoMembresia = codigoMembresia.trim();
        this.fechaInicio = fechaInicio;
        this.tipoMembresia = tipoMembresia;
        this.puntos = 0;
        this.activa = true;
    }

    public double calcularDescuento(double valorEntrada) {
        if (valorEntrada < 0) throw new IllegalArgumentException("El valor no puede ser negativo");
        if (!activa) return 0;
        return valorEntrada * tipoMembresia.getPorcentajeDescuento() / 100.0;
    }

    public void acumularPuntos(int cantidad) {
        if (cantidad < 0) throw new IllegalArgumentException("Los puntos no pueden ser negativos");
        if (activa) puntos += cantidad;
    }

    public boolean estaActiva() { return activa; }
    public void desactivar() { activa = false; }

    public String getCodigoMembresia() { return codigoMembresia; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public int getPuntos() { return puntos; }
    public TipoMembresia getTipoMembresia() { return tipoMembresia; }
    public void setTipoMembresia(TipoMembresia tipoMembresia) {
        if (tipoMembresia == null) throw new IllegalArgumentException("El tipo de membresía es obligatorio");
        this.tipoMembresia = tipoMembresia;
    }

    @Override
    public String toString() {
        return tipoMembresia + " - " + puntos + " puntos" + (activa ? "" : " - INACTIVA");
    }
}
