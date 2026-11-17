package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ClientePortalViewController {

    @FXML private Label lblCliente, lblMembresia;
    @FXML private TabPane tabPane;
    @FXML private TextArea txtCartelera, txtFunciones, txtTarifas, txtCompras;
    @FXML private ComboBox<Pelicula> cbPelicula;
    @FXML private ComboBox<Funcion> cbFuncion;
    @FXML private ListView<Asiento> listaAsientos;

    @FXML
    public void initialize() {
        Cliente cliente = (Cliente) App.getInstance().getUsuarioActual();
        Cine cine = App.getInstance().getCine();

        lblCliente.setText(cliente.getNombre() + " - " + cliente.getTipoCliente());

        lblMembresia.setText(
                cliente.tieneMembresia()
                        ? cliente.getMembresia().toString()
                        + " | Descuento " + (int) cliente.obtenerDescuento() + "%"
                        : "SIN MEMBRESÍA - solicítela con un operario"
        );

        StringBuilder peliculas = new StringBuilder();
        for (Pelicula p : cine.obtenerPeliculasDisponibles()) {
            peliculas.append(p).append('\n');
        }
        txtCartelera.setText(peliculas.toString());

        StringBuilder funciones = new StringBuilder();
        for (Funcion f : cine.obtenerFuncionesProgramadas()) {
            funciones.append(f)
                    .append(" | Disponibles: ")
                    .append(f.contarAsientosDisponibles())
                    .append('\n');
        }
        txtFunciones.setText(funciones.toString());

        StringBuilder tarifas = new StringBuilder();
        for (Tarifa t : cine.obtenerTarifas()) {
            tarifas.append(t).append('\n');
        }
        txtTarifas.setText(tarifas.toString());

        StringBuilder compras = new StringBuilder();
        for (Venta v : cliente.getVentas()) {
            compras.append(v.generarInformacion()).append('\n');
        }
        txtCompras.setText(
                compras.length() == 0
                        ? "Aún no tiene compras registradas."
                        : compras.toString()
        );

        cbPelicula.getItems().setAll(cine.obtenerPeliculasDisponibles());
        cbFuncion.setDisable(true);

        seleccionarSeccionInicial();
    }

    private void seleccionarSeccionInicial() {
        String seccion = App.getInstance().getSeccionCliente();
        int indice = 0;

        if ("FUNCIONES".equals(seccion)) indice = 1;
        else if ("DISPONIBILIDAD".equals(seccion)) indice = 2;
        else if ("TARIFAS".equals(seccion)) indice = 3;
        else if ("MEMBRESIA".equals(seccion)) indice = 4;
        else if ("COMPRAS".equals(seccion)) indice = 5;

        tabPane.getSelectionModel().select(indice);
    }

    @FXML
    private void pelicula() {
        Pelicula p = cbPelicula.getValue();
        if (p == null) return;

        cbFuncion.getItems().setAll(
                App.getInstance().getCine()
                        .obtenerFuncionesPorPelicula(p.getTitulo())
        );
        cbFuncion.setDisable(false);
        listaAsientos.getItems().clear();
    }

    @FXML
    private void funcion() {
        Funcion f = cbFuncion.getValue();
        if (f != null) {
            listaAsientos.getItems().setAll(f.obtenerAsientosDisponibles());
        }
    }

    @FXML
    private void volver() {
        App.getInstance().volverMenuActual();
    }
}
