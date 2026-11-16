package co.edu.uniquindio.poo.model;

public enum TipoMembresia {
    PLATA(10),
    PLATINO(20),
    ORO(30);

    private final double porcentajeDescuento;

    TipoMembresia(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    @Override
    public String toString() {
        return name() + " (" + (int) porcentajeDescuento + "%)";
    }
}
