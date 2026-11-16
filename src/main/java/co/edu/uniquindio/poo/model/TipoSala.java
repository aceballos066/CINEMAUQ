package co.edu.uniquindio.poo.model;

public enum TipoSala {
    TRADICIONAL(0),
    VIP(5000),
    TRES_D(7000);

    private final double recargo;

    TipoSala(double recargo) {
        this.recargo = recargo;
    }

    public double getRecargo() {
        return recargo;
    }
}
