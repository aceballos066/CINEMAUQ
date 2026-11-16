package co.edu.uniquindio.poo.model;

public class Tarifa {
    private final TipoEntrada tipoEntrada;
    private double valor;

    public Tarifa(TipoEntrada tipoEntrada, double valor) {
        if (tipoEntrada == null) throw new IllegalArgumentException("El tipo de entrada es obligatorio");
        this.tipoEntrada = tipoEntrada;
        setValor(valor);
    }

    public TipoEntrada getTipoEntrada() { return tipoEntrada; }
    public double getValor() { return valor; }
    public void setValor(double valor) {
        if (valor < 0) throw new IllegalArgumentException("El valor no puede ser negativo");
        this.valor = valor;
    }

    @Override
    public String toString() { return tipoEntrada + " - $" + String.format("%.0f", valor); }
}
