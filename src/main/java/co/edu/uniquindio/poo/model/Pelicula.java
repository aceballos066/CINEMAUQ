package co.edu.uniquindio.poo.model;

public class Pelicula {
    private final String codigo;
    private String titulo;
    private int duracionMin;
    private Genero genero;
    private Clasificacion clasificacion;
    private EstadoPelicula estadoPelicula;

    public Pelicula(String codigo, String titulo, Genero genero, int duracionMin, Clasificacion clasificacion) {
        if (codigo == null || codigo.isBlank()) throw new IllegalArgumentException("El código es obligatorio");
        this.codigo = codigo.trim();
        setTitulo(titulo);
        setGenero(genero);
        setDuracionMin(duracionMin);
        setClasificacion(clasificacion);
        this.estadoPelicula = EstadoPelicula.DISPONIBLE;
    }

    public boolean estaDisponible() { return estadoPelicula == EstadoPelicula.DISPONIBLE; }
    public void deshabilitar() { estadoPelicula = EstadoPelicula.NO_DISPONIBLE; }

    public void actualizarDatos(Pelicula actualizada) {
        if (actualizada == null) throw new IllegalArgumentException("La película actualizada es obligatoria");
        setTitulo(actualizada.titulo);
        setGenero(actualizada.genero);
        setDuracionMin(actualizada.duracionMin);
        setClasificacion(actualizada.clasificacion);
    }

    public String getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) throw new IllegalArgumentException("El título es obligatorio");
        this.titulo = titulo.trim();
    }
    public int getDuracionMin() { return duracionMin; }
    public void setDuracionMin(int duracionMin) {
        if (duracionMin <= 0) throw new IllegalArgumentException("La duración debe ser positiva");
        this.duracionMin = duracionMin;
    }
    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) {
        if (genero == null) throw new IllegalArgumentException("El género es obligatorio");
        this.genero = genero;
    }
    public Clasificacion getClasificacion() { return clasificacion; }
    public void setClasificacion(Clasificacion clasificacion) {
        if (clasificacion == null) throw new IllegalArgumentException("La clasificación es obligatoria");
        this.clasificacion = clasificacion;
    }
    public EstadoPelicula getEstadoPelicula() { return estadoPelicula; }

    @Override
    public String toString() {
        return codigo + " - " + titulo + " | " + genero + " | " + duracionMin + " min | " + clasificacion;
    }
}

