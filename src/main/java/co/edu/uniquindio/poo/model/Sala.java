
package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.exception.AsientoNoEncontradoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sala {
    private final String codigo;
    private String nombre;
    private int capacidadMax;
    private int filas;
    private int columnas;
    private TipoSala tipoSala;
    private EstadoSala estadoSala;
    private final List<Asiento> asientos;

    public Sala(String codigo, String nombre, int capacidadMax, int filas, int columnas, TipoSala tipoSala) {
        if (codigo == null || codigo.isBlank()) throw new IllegalArgumentException("El código es obligatorio");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre es obligatorio");
        if (filas <= 0 || columnas <= 0) throw new IllegalArgumentException("Filas y columnas deben ser positivas");
        if (capacidadMax <= 0 || capacidadMax > filas * columnas) throw new IllegalArgumentException("Capacidad inválida");
        if (tipoSala == null) throw new IllegalArgumentException("El tipo de sala es obligatorio");
        this.codigo = codigo.trim();
        this.nombre = nombre.trim();
        this.capacidadMax = capacidadMax;
        this.filas = filas;
        this.columnas = columnas;
        this.tipoSala = tipoSala;
        this.estadoSala = EstadoSala.DISPONIBLE;
        this.asientos = new ArrayList<>();
        generarAsientos();
    }

    public final void generarAsientos() {
        asientos.clear();
        int creados = 0;
        for (int f = 0; f < filas && creados < capacidadMax; f++) {
            String fila = String.valueOf((char)('A' + f));
            for (int n = 1; n <= columnas && creados < capacidadMax; n++) {
                asientos.add(new Asiento(fila + n, fila, n));
                creados++;
            }
        }
    }

    public List<Asiento> copiarAsientos() {
        List<Asiento> copia = new ArrayList<>();
        for (Asiento asiento : asientos) copia.add(asiento.copiar());
        return copia;
    }

    public double obtenerRecargo() { return tipoSala.getRecargo(); }
    public boolean estaDisponible() { return estadoSala == EstadoSala.DISPONIBLE; }
    public void deshabilitar() { estadoSala = EstadoSala.FUERA_DE_SERVICIO; }

    public Asiento buscarAsiento(String codigoAsiento) {
        for (Asiento asiento : asientos) {
            if (asiento.getCodigo().equalsIgnoreCase(codigoAsiento)) return asiento;
        }
        throw new AsientoNoEncontradoException("No existe el asiento " + codigoAsiento);
    }

    public void actualizarDatos(Sala actualizada) {
        if (actualizada == null) throw new IllegalArgumentException("La sala actualizada es obligatoria");
        this.nombre = actualizada.nombre;
        this.capacidadMax = actualizada.capacidadMax;
        this.filas = actualizada.filas;
        this.columnas = actualizada.columnas;
        this.tipoSala = actualizada.tipoSala;
        generarAsientos();
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getCapacidadMax() { return capacidadMax; }
    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }
    public TipoSala getTipoSala() { return tipoSala; }
    public EstadoSala getEstadoSala() { return estadoSala; }
    public List<Asiento> getAsientos() { return Collections.unmodifiableList(asientos); }

    @Override
    public String toString() { return codigo + " - " + nombre + " - " + tipoSala + " - " + estadoSala; }
}

