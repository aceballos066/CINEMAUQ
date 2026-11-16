
package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.exception.AsientoNoEncontradoException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Funcion {
    private final String codigo;
    private final Pelicula pelicula;
    private final Sala sala;
    private final LocalDate fecha;
    private final LocalTime horaInicio;
    private final double valorBase;
    private EstadoFuncion estadoFuncion;
    private final List<Asiento> asientos;
    private final List<Venta> ventas;

    public Funcion(String codigo, Pelicula pelicula, Sala sala, LocalDate fecha, LocalTime horaInicio, double valorBase) {
        if (codigo == null || codigo.isBlank()) throw new IllegalArgumentException("El código es obligatorio");
        if (pelicula == null || sala == null || fecha == null || horaInicio == null) throw new IllegalArgumentException("Los datos de la función son obligatorios");
        if (valorBase < 0) throw new IllegalArgumentException("El valor base no puede ser negativo");
        this.codigo = codigo.trim();
        this.pelicula = pelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.valorBase = valorBase;
        this.estadoFuncion = EstadoFuncion.PROGRAMADA;
        this.asientos = new ArrayList<>(sala.copiarAsientos());
        this.ventas = new ArrayList<>();
    }

    public Asiento buscarAsiento(String codigoAsiento) {
        for (Asiento asiento : asientos) {
            if (asiento.getCodigo().equalsIgnoreCase(codigoAsiento)) return asiento;
        }
        throw new AsientoNoEncontradoException("No existe el asiento " + codigoAsiento + " en la función " + codigo);
    }

    public boolean tieneAsientosDisponibles() { return contarAsientosDisponibles() > 0; }

    public int contarAsientosDisponibles() {
        int cantidad = 0;
        for (Asiento asiento : asientos) if (asiento.estaDisponible()) cantidad++;
        return cantidad;
    }

    public int contarAsientosOcupados() { return asientos.size() - contarAsientosDisponibles(); }

    public double calcularPorcentajeOcupacion() {
        if (asientos.isEmpty()) return 0;
        return contarAsientosOcupados() * 100.0 / asientos.size();
    }

    public boolean estaProgramada() { return estadoFuncion == EstadoFuncion.PROGRAMADA; }
    public void cancelar() { estadoFuncion = EstadoFuncion.CANCELADA; }
    public void finalizar() { estadoFuncion = EstadoFuncion.FINALIZADA; }
    public void agregarVenta(Venta venta) { if (venta != null && !ventas.contains(venta)) ventas.add(venta); }

    public List<Venta> obtenerVentasActivas() {
        List<Venta> activas = new ArrayList<>();
        for (Venta venta : ventas) if (venta.estaActiva()) activas.add(venta);
        return activas;
    }

    public int contarEntradasVendidas() { return obtenerVentasActivas().size(); }

    public double calcularIngresos() {
        double total = 0;
        for (Venta venta : obtenerVentasActivas()) total += venta.getValorPagado();
        return total;
    }

    public List<Asiento> obtenerAsientosDisponibles() {
        List<Asiento> lista = new ArrayList<>();
        for (Asiento asiento : asientos) if (asiento.estaDisponible()) lista.add(asiento);
        return lista;
    }

    public List<Asiento> obtenerAsientosOcupados() {
        List<Asiento> lista = new ArrayList<>();
        for (Asiento asiento : asientos) if (!asiento.estaDisponible()) lista.add(asiento);
        return lista;
    }

    public String getCodigo() { return codigo; }
    public Pelicula getPelicula() { return pelicula; }
    public Sala getSala() { return sala; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public double getValorBase() { return valorBase; }
    public EstadoFuncion getEstadoFuncion() { return estadoFuncion; }
    public List<Asiento> getAsientos() { return Collections.unmodifiableList(asientos); }
    public List<Venta> getVentas() { return Collections.unmodifiableList(ventas); }

    @Override
    public String toString() {
        return codigo + " - " + pelicula.getTitulo() + " - " + fecha + " " + horaInicio + " - " + sala.getNombre();
    }
}

