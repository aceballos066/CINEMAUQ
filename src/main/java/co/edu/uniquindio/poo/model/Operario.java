package co.edu.uniquindio.poo.model;

import java.time.LocalDate;

public class Operario extends Persona {
    private String codEmpleado;
    private LocalDate fechaContrato;
    private double salario;

    public Operario(String identificacion, String nombre, String codEmpleado, LocalDate fechaContrato, double salario) {
        super(identificacion, nombre);
        setCodEmpleado(codEmpleado);
        setFechaContrato(fechaContrato);
        setSalario(salario);
    }

    public String getCodEmpleado() { return codEmpleado; }

    public void setCodEmpleado(String codEmpleado) {
        if (codEmpleado == null || codEmpleado.isBlank())
            throw new IllegalArgumentException("El código de empleado es obligatorio");
        this.codEmpleado = codEmpleado.trim();
    }

    public LocalDate getFechaContrato() { return fechaContrato; }

    public void setFechaContrato(LocalDate fechaContrato) {
        if (fechaContrato == null)
            throw new IllegalArgumentException("La fecha de contrato es obligatoria");
        this.fechaContrato = fechaContrato;
    }

    public double getSalario() { return salario; }

    public void setSalario(double salario) {
        if (salario < 0)
            throw new IllegalArgumentException("El salario no puede ser negativo");
        this.salario = salario;
    }

    @Override
    public String toString() {
        return codEmpleado + " - " + getNombre();
    }
}
