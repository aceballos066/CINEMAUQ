package co.edu.uniquindio.poo.model;

public class Boleta {
    private final String codigo;
    private final TipoEntrada tipoEntrada;
    private final double valorPagado;

    public Boleta(String codigo, TipoEntrada tipoEntrada, double valorPagado) {
        if (codigo == null || codigo.isBlank()) throw new IllegalArgumentException("El código es obligatorio");
        if (tipoEntrada == null) throw new IllegalArgumentException("El tipo de entrada es obligatorio");
        if (valorPagado < 0) throw new IllegalArgumentException("El valor pagado no puede ser negativo");
        this.codigo = codigo.trim();
        this.tipoEntrada = tipoEntrada;
        this.valorPagado = valorPagado;
    }

    public String generarInformacion() {
        return "Boleta " + codigo + " | " + tipoEntrada + " | $" + String.format("%.0f", valorPagado);
    }

    public String getCodigo() { return codigo; }
    public TipoEntrada getTipoEntrada() { return tipoEntrada; }
    public double getValorPagado() { return valorPagado; }
}