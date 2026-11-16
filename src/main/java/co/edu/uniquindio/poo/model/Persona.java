package co.edu.uniquindio.poo.model;

import java.util.Objects;

public class Persona {
    private String identificacion;
    private String nombre;

    public Persona(String identificacion, String nombre) {
        setIdentificacion(identificacion);
        setNombre(nombre);
    }

    public String getIdentificacion() { return identificacion; }

    public void setIdentificacion(String identificacion) {
        if (identificacion == null || identificacion.isBlank()) {
            throw new IllegalArgumentException("La identificación es obligatoria");
        }
        this.identificacion = identificacion.trim();
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        this.nombre = nombre.trim();
    }

    @Override
    public String toString() {
        return identificacion + " - " + nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Persona)) return false;
        Persona otra = (Persona) obj;
        return identificacion.equalsIgnoreCase(otra.identificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacion.toLowerCase());
    }
}
