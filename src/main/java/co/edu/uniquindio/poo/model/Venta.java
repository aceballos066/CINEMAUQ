package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.exception.EstadoInvalidoException;
import java.time.LocalDateTime;

public class Venta {
    private final String codigo;
    private final LocalDateTime fechaHora;
    private final double valorPagado;
    private final Cliente cliente;
    private final Operario operario;
    private final Funcion funcion;
    private final Asiento asiento;
    private final Boleta boleta;
    private EstadoVenta estadoVenta;

    public Venta(String codigo, Cliente cliente, Operario operario, Funcion funcion, Asiento asiento, Boleta boleta, double valorPagado) {
        if (codigo == null || codigo.isBlank()) throw new IllegalArgumentException("El código es obligatorio");
        if (cliente == null || operario == null || funcion == null || asiento == null || boleta == null) throw new IllegalArgumentException("Los datos de la venta son obligatorios");
        if (valorPagado < 0) throw new IllegalArgumentException("El valor pagado no puede ser negativo");
        this.codigo = codigo.trim();
        this.cliente = cliente;
        this.operario = operario;
        this.funcion = funcion;
        this.asiento = asiento;
        this.boleta = boleta;
        this.valorPagado = valorPagado;
        this.fechaHora = LocalDateTime.now();
        this.estadoVenta = EstadoVenta.ACTIVA;
    }

    public boolean estaActiva() { return estadoVenta == EstadoVenta.ACTIVA; }
    public void cancelar() {
        if (!estaActiva()) throw new EstadoInvalidoException("La venta ya está cancelada");
        estadoVenta = EstadoVenta.CANCELADA;
    }
    public String generarInformacion() {
        return "Venta " + codigo + " | Cliente: " + cliente.getNombre() + " | " + funcion.getPelicula().getTitulo() +
                " | Asiento: " + asiento.getCodigo() + " | " + boleta.generarInformacion() + " | " + estadoVenta;
    }

    public String getCodigo() { return codigo; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public double getValorPagado() { return valorPagado; }
    public Cliente getCliente() { return cliente; }
    public Operario getOperario() { return operario; }
    public Funcion getFuncion() { return funcion; }
    public Asiento getAsiento() { return asiento; }
    public Boleta getBoleta() { return boleta; }
    public EstadoVenta getEstadoVenta() { return estadoVenta; }

    @Override
    public String toString() {
        return codigo + " - " + cliente.getNombre() + " - " + funcion.getPelicula().getTitulo() + " - " + asiento.getCodigo() + " - $" + String.format("%.0f", valorPagado) + " - " + estadoVenta;
    }
}
