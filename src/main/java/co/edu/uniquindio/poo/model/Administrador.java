package co.edu.uniquindio.poo.model;

public class Administrador extends Persona {
    private String codAdministrador;

    public Administrador(String identificacion, String nombre, String codAdministrador) {
        super(identificacion, nombre);
        setCodAdministrador(codAdministrador);
    }

    public String getCodAdministrador() { return codAdministrador; }

    public void setCodAdministrador(String codAdministrador) {
        if (codAdministrador == null || codAdministrador.isBlank()) {
            throw new IllegalArgumentException("El código de administrador es obligatorio");
        }
        this.codAdministrador = codAdministrador.trim();
    }

    @Override
    public String toString() {
        return codAdministrador + " - " + getNombre();
    }
}
