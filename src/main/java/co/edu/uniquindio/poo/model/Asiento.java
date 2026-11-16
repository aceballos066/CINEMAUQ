package co.edu.uniquindio.poo.model;

public class Asiento {
    private final String codigo;
    private final String fila;
    private final int numero;
    private EstadoAsiento estadoAsiento;

    public Asiento(String codigo, String fila, int numero) {
        if (codigo == null || codigo.isBlank()) throw new IllegalArgumentException("El código es obligatorio");
        if (fila == null || fila.isBlank()) throw new IllegalArgumentException("La fila es obligatoria");
        if (numero <= 0) throw new IllegalArgumentException("El número debe ser positivo");
        this.codigo = codigo.trim();
        this.fila = fila.trim();
        this.numero = numero;
        this.estadoAsiento = EstadoAsiento.DISPONIBLE;
    }

    public void ocupar() { estadoAsiento = EstadoAsiento.OCUPADO; }
    public void liberar() { estadoAsiento = EstadoAsiento.DISPONIBLE; }
    public boolean estaDisponible() { return estadoAsiento == EstadoAsiento.DISPONIBLE; }
    public Asiento copiar() { return new Asiento(codigo, fila, numero); }

    public String getCodigo() { return codigo; }
    public String getFila() { return fila; }
    public int getNumero() { return numero; }
    public EstadoAsiento getEstadoAsiento() { return estadoAsiento; }

    @Override
    public String toString() { return codigo + " - " + estadoAsiento; }
}
