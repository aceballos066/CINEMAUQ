package co.edu.uniquindio.poo.model;

public enum TipoCliente {
    GENERAL, ESTUDIANTE, NINO, ADULTO_MAYOR;

    @Override
    public String toString() {
        return this == NINO ? "NIÑO" : name();
    }
}