package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.exception.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cine {
    private final String nombreComercial;
    private final String nit;
    private final String direccion;

    private final List<Pelicula> peliculas = new ArrayList<>();
    private final List<Sala> salas = new ArrayList<>();
    private final List<Funcion> funciones = new ArrayList<>();
    private final List<Tarifa> tarifas = new ArrayList<>();
    private final List<Persona> personas = new ArrayList<>();
    private final List<Venta> ventas = new ArrayList<>();

    private int consecutivoVenta = 1;
    private int consecutivoBoleta = 1;

    public Cine(String nombreComercial, String nit, String direccion) {
        if (nombreComercial == null || nombreComercial.isBlank()) throw new IllegalArgumentException("El nombre comercial es obligatorio");
        if (nit == null || nit.isBlank()) throw new IllegalArgumentException("El NIT es obligatorio");
        if (direccion == null || direccion.isBlank()) throw new IllegalArgumentException("La dirección es obligatoria");
        this.nombreComercial = nombreComercial.trim();
        this.nit = nit.trim();
        this.direccion = direccion.trim();
    }

    public List<Funcion> obtenerFuncionesPorPelicula(String titulo) {
        List<Funcion> resultado = new ArrayList<>();
        if (titulo == null) return resultado;
        for (Funcion funcion : funciones) {
            if (funcion.getPelicula().getTitulo().equalsIgnoreCase(titulo.trim())) resultado.add(funcion);
        }
        return resultado;
    }

    public void registrarPelicula(Pelicula pelicula) {
        if (pelicula == null) throw new IllegalArgumentException("La película es obligatoria");
        if (buscarPelicula(pelicula.getCodigo()) != null) throw new PeliculaDuplicadaException("Ya existe la película " + pelicula.getCodigo());
        peliculas.add(pelicula);
    }

    public Pelicula buscarPelicula(String codigo) {
        if (codigo == null) return null;
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getCodigo().equalsIgnoreCase(codigo.trim())) return pelicula;
        }
        return null;
    }

    public List<Pelicula> obtenerPeliculasDisponibles() {
        List<Pelicula> resultado = new ArrayList<>();
        for (Pelicula pelicula : peliculas) if (pelicula.estaDisponible()) resultado.add(pelicula);
        return resultado;
    }

    public boolean modificarPelicula(String codigo, Pelicula actualizada) {
        Pelicula pelicula = buscarPelicula(codigo);
        if (pelicula == null || actualizada == null) return false;
        pelicula.actualizarDatos(actualizada);
        return true;
    }

    public boolean deshabilitarPelicula(String codigo) {
        Pelicula pelicula = buscarPelicula(codigo);
        if (pelicula == null) return false;
        pelicula.deshabilitar();
        return true;
    }

    public void registrarSala(Sala sala) {
        if (sala == null) throw new IllegalArgumentException("La sala es obligatoria");
        if (buscarSala(sala.getCodigo()) != null) throw new SalaDuplicadaException("Ya existe la sala " + sala.getCodigo());
        salas.add(sala);
    }

    public Sala buscarSala(String codigo) {
        if (codigo == null) return null;
        for (Sala sala : salas) {
            if (sala.getCodigo().equalsIgnoreCase(codigo.trim())) return sala;
        }
        return null;
    }

    public boolean modificarSala(String codigo, Sala actualizacion) {
        Sala sala = buscarSala(codigo);
        if (sala == null || actualizacion == null) return false;
        sala.actualizarDatos(actualizacion);
        return true;
    }

    public boolean deshabilitarSala(String codigo) {
        Sala sala = buscarSala(codigo);
        if (sala == null) return false;
        sala.deshabilitar();
        return true;
    }

    public void programarFuncion(Funcion funcion) {
        if (funcion == null) throw new IllegalArgumentException("La función es obligatoria");
        if (buscarFuncion(funcion.getCodigo()) != null) throw new FuncionDuplicadaException("Ya existe la función " + funcion.getCodigo());

        Pelicula peliculaRegistrada = buscarPelicula(funcion.getPelicula().getCodigo());
        Sala salaRegistrada = buscarSala(funcion.getSala().getCodigo());
        if (peliculaRegistrada == null) throw new PeliculaNoEncontradaException("La película no está registrada");
        if (salaRegistrada == null) throw new SalaNoEncontradaException("La sala no está registrada");
        if (!peliculaRegistrada.estaDisponible()) throw new EstadoInvalidoException("La película no está disponible");
        if (!salaRegistrada.estaDisponible()) throw new SalaNoDisponibleException("La sala está fuera de servicio");

        Funcion funcionRegistrada = new Funcion(
                funcion.getCodigo(), peliculaRegistrada, salaRegistrada,
                funcion.getFecha(), funcion.getHoraInicio(), funcion.getValorBase());
        funciones.add(funcionRegistrada);
    }

    public Funcion buscarFuncion(String codigo) {
        if (codigo == null) return null;
        for (Funcion funcion : funciones) {
            if (funcion.getCodigo().equalsIgnoreCase(codigo.trim())) return funcion;
        }
        return null;
    }

    public void cancelarFuncion(String codigoFuncion) {
        Funcion funcion = exigirFuncion(codigoFuncion);
        funcion.cancelar();
    }

    public List<Funcion> obtenerFuncionesProgramadas() {
        List<Funcion> resultado = new ArrayList<>();
        for (Funcion funcion : funciones) if (funcion.estaProgramada()) resultado.add(funcion);
        return resultado;
    }

    public List<Asiento> consultarDisponibilidad(String codigoFuncion) {
        return new ArrayList<>(exigirFuncion(codigoFuncion).getAsientos());
    }

    public List<Asiento> obtenerAsientosDisponibles(String codigoFuncion) {
        return exigirFuncion(codigoFuncion).obtenerAsientosDisponibles();
    }

    public void configurarTarifa(TipoEntrada tipoEntrada, double valor) {
        if (tipoEntrada == null) throw new IllegalArgumentException("El tipo de entrada es obligatorio");
        Tarifa tarifa = buscarTarifa(tipoEntrada);
        if (tarifa == null) tarifas.add(new Tarifa(tipoEntrada, valor));
        else tarifa.setValor(valor);
    }

    public Tarifa buscarTarifa(TipoEntrada tipoEntrada) {
        if (tipoEntrada == null) return null;
        for (Tarifa tarifa : tarifas) if (tarifa.getTipoEntrada() == tipoEntrada) return tarifa;
        return null;
    }

    public void registrarPersona(Persona persona) {
        if (persona == null) throw new IllegalArgumentException("La persona es obligatoria");
        for (Persona registrada : personas) {
            if (registrada.getIdentificacion().equalsIgnoreCase(persona.getIdentificacion())) {
                throw new PersonaDuplicadaException("Ya existe una persona con identificación " + persona.getIdentificacion());
            }
        }
        if (persona instanceof Operario) {
            Operario nuevo = (Operario) persona;
            if (buscarOperario(nuevo.getCodEmpleado()) != null) throw new PersonaDuplicadaException("Ya existe el código de empleado " + nuevo.getCodEmpleado());
        }
        if (persona instanceof Administrador) {
            Administrador nuevo = (Administrador) persona;
            if (buscarAdministrador(nuevo.getCodAdministrador()) != null) throw new PersonaDuplicadaException("Ya existe el código de administrador " + nuevo.getCodAdministrador());
        }
        personas.add(persona);
    }

    public Cliente buscarCliente(String identificacion) {
        if (identificacion == null) return null;
        for (Persona persona : personas) {
            if (persona instanceof Cliente) {
                Cliente cliente = (Cliente) persona;
                if (cliente.getIdentificacion().equalsIgnoreCase(identificacion.trim())) return cliente;
            }
        }
        return null;
    }

    public Operario buscarOperario(String codEmpleado) {
        if (codEmpleado == null) return null;
        for (Persona persona : personas) {
            if (persona instanceof Operario) {
                Operario operario = (Operario) persona;
                if (operario.getCodEmpleado().equalsIgnoreCase(codEmpleado.trim())) return operario;
            }
        }
        return null;
    }

    public Administrador buscarAdministrador(String codAdministrador) {
        if (codAdministrador == null) return null;
        for (Persona persona : personas) {
            if (persona instanceof Administrador) {
                Administrador administrador = (Administrador) persona;
                if (administrador.getCodAdministrador().equalsIgnoreCase(codAdministrador.trim())) return administrador;
            }
        }
        return null;
    }

    public List<Cliente> obtenerClientes() {
        List<Cliente> resultado = new ArrayList<>();
        for (Persona persona : personas) {
            if (persona instanceof Cliente) resultado.add((Cliente) persona);
        }
        return resultado;
    }

    public List<Operario> obtenerOperarios() {
        List<Operario> resultado = new ArrayList<>();
        for (Persona persona : personas) {
            if (persona instanceof Operario) resultado.add((Operario) persona);
        }
        return resultado;
    }

    public List<Administrador> obtenerAdministradores() {
        List<Administrador> resultado = new ArrayList<>();
        for (Persona persona : personas) {
            if (persona instanceof Administrador) resultado.add((Administrador) persona);
        }
        return resultado;
    }

    public void registrarMembresia(Cliente cliente, Membresia membresia) {
        if (cliente == null || membresia == null) throw new MembresiaInvalidaException("Cliente y membresía son obligatorios");
        Cliente registrado = buscarCliente(cliente.getIdentificacion());
        if (registrado == null) throw new PersonaNoEncontradaException("El cliente no está registrado");
        registrado.setMembresia(membresia);
    }

    public Membresia consultarMembresia(String identificacionCliente) {
        Cliente cliente = buscarCliente(identificacionCliente);
        return cliente == null ? null : cliente.getMembresia();
    }

    public double calcularValorEntrada(Funcion funcion, Cliente cliente, TipoEntrada tipoEntrada) {
        if (funcion == null || cliente == null || tipoEntrada == null) throw new IllegalArgumentException("Los datos para calcular la entrada son obligatorios");
        Tarifa tarifaTipo = buscarTarifa(tipoEntrada);
        Tarifa tarifaGeneral = buscarTarifa(TipoEntrada.GENERAL);
        if (tarifaTipo == null || tarifaGeneral == null) throw new TarifaNoConfiguradaException("La tarifa no está configurada");

        double ajusteFuncion = funcion.getValorBase() - tarifaGeneral.getValor();
        double valorSinDescuento = tarifaTipo.getValor() + ajusteFuncion + funcion.getSala().obtenerRecargo();
        if (valorSinDescuento < 0) valorSinDescuento = 0;

        if (cliente.tieneMembresia()) {
            valorSinDescuento -= cliente.getMembresia().calcularDescuento(valorSinDescuento);
        }
        return Math.round(valorSinDescuento * 100.0) / 100.0;
    }

    public Venta venderEntrada(Cliente cliente, Operario operario, String codigoFuncion, TipoEntrada tipoEntrada, String codigoAsiento) {
        if (cliente == null || operario == null) throw new IllegalArgumentException("Cliente y operario son obligatorios");
        Cliente clienteRegistrado = buscarCliente(cliente.getIdentificacion());
        Operario operarioRegistrado = buscarOperario(operario.getCodEmpleado());
        if (clienteRegistrado == null) throw new PersonaNoEncontradaException("El cliente no está registrado");
        if (operarioRegistrado == null) throw new PersonaNoEncontradaException("El operario no está registrado");

        Funcion funcion = exigirFuncion(codigoFuncion);
        if (!funcion.estaProgramada()) throw new EstadoInvalidoException("La función no está programada");
        if (!funcion.tieneAsientosDisponibles()) throw new SinAsientosDisponiblesException("No quedan asientos disponibles");

        Asiento asiento = funcion.buscarAsiento(codigoAsiento);
        if (!asiento.estaDisponible()) throw new AsientoOcupadoException("El asiento " + codigoAsiento + " está ocupado");

        double valorPagado = calcularValorEntrada(funcion, clienteRegistrado, tipoEntrada);
        asiento.ocupar();

        String codigoVenta = String.format("V%04d", consecutivoVenta++);
        String codigoBoleta = String.format("B%04d", consecutivoBoleta++);
        Boleta boleta = new Boleta(codigoBoleta, tipoEntrada, valorPagado);
        Venta venta = new Venta(codigoVenta, clienteRegistrado, operarioRegistrado, funcion, asiento, boleta, valorPagado);

        ventas.add(venta);
        funcion.agregarVenta(venta);
        clienteRegistrado.agregarVenta(venta);

        if (clienteRegistrado.tieneMembresia()) {
            clienteRegistrado.getMembresia().acumularPuntos((int) (valorPagado / 1000));
        }
        return venta;
    }

    public void cancelarVenta(String codigoVenta) {
        Venta venta = buscarVenta(codigoVenta);
        if (venta == null) throw new VentaNoEncontradaException("No existe la venta " + codigoVenta);
        if (venta.estaActiva()) {
            venta.cancelar();
            venta.getAsiento().liberar();
        }
    }

    public Venta buscarVenta(String codigo) {
        if (codigo == null) return null;
        for (Venta venta : ventas) if (venta.getCodigo().equalsIgnoreCase(codigo.trim())) return venta;
        return null;
    }

    public List<Venta> obtenerTodasLasVentas() { return new ArrayList<>(ventas); }

    public List<Venta> obtenerVentasActivas() {
        List<Venta> resultado = new ArrayList<>();
        for (Venta venta : ventas) if (venta.estaActiva()) resultado.add(venta);
        return resultado;
    }

    public List<Venta> obtenerVentasCanceladas() {
        List<Venta> resultado = new ArrayList<>();
        for (Venta venta : ventas) if (!venta.estaActiva()) resultado.add(venta);
        return resultado;
    }

    public int contarEntradasVendidas() { return obtenerVentasActivas().size(); }

    public double calcularTotalIngresos() {
        double total = 0;
        for (Venta venta : obtenerVentasActivas()) total += venta.getValorPagado();
        return total;
    }

    public Pelicula obtenerPeliculaMasVendida() {
        Pelicula mejor = null;
        int mayor = -1;
        for (Pelicula pelicula : peliculas) {
            int cantidad = 0;
            for (Venta venta : obtenerVentasActivas()) {
                if (venta.getFuncion().getPelicula().getCodigo().equalsIgnoreCase(pelicula.getCodigo())) cantidad++;
            }
            if (cantidad > mayor) {
                mayor = cantidad;
                mejor = pelicula;
            }
        }
        return mayor > 0 ? mejor : null;
    }

    public double calcularOcupacionFuncion(String codigoFuncion) {
        return exigirFuncion(codigoFuncion).calcularPorcentajeOcupacion();
    }

    public List<Funcion> obtenerFuncionesPorFecha(LocalDate fecha) {
        List<Funcion> resultado = new ArrayList<>();
        if (fecha == null) return resultado;
        for (Funcion funcion : funciones) if (funcion.getFecha().equals(fecha) && funcion.estaProgramada()) resultado.add(funcion);
        return resultado;
    }

    private Funcion exigirFuncion(String codigo) {
        Funcion funcion = buscarFuncion(codigo);
        if (funcion == null) throw new FuncionNoEncontradaException("No existe la función " + codigo);
        return funcion;
    }

    public String getNombreComercial() { return nombreComercial; }
    public String getNit() { return nit; }
    public String getDireccion() { return direccion; }
    public List<Pelicula> obtenerPeliculas() { return Collections.unmodifiableList(peliculas); }
    public List<Sala> obtenerSalas() { return Collections.unmodifiableList(salas); }
    public List<Funcion> obtenerFunciones() { return Collections.unmodifiableList(funciones); }
    public List<Tarifa> obtenerTarifas() { return Collections.unmodifiableList(tarifas); }
}
