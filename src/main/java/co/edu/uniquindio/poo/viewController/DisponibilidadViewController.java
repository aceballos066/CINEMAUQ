package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DisponibilidadViewController {

    @FXML private ComboBox<Pelicula> cbPelicula;
    @FXML private ComboBox<Funcion> cbFuncion;
    @FXML private ListView<Asiento> listaAsientos;
    @FXML private Label lblTotales;

    @FXML
    public void initialize() {
        cbPelicula.getItems().setAll(
                App.getInstance().getCine().obtenerPeliculasDisponibles()
        );
        cbFuncion.setDisable(true);
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
        lblTotales.setText("Seleccione una función");
    }

    @FXML
    private void funcion() {
        Funcion f = cbFuncion.getValue();
        if (f == null) return;

        listaAsientos.getItems().setAll(f.getAsientos());

        lblTotales.setText(
                "Total: " + f.getAsientos().size()
                        + " | Disponibles: " + f.contarAsientosDisponibles()
                        + " | Ocupados: " + f.contarAsientosOcupados()
                        + " | Ocupación: "
                        + String.format("%.1f%%",
                        f.calcularPorcentajeOcupacion())
        );
    }

    @FXML
    private void volver() {
        App.getInstance().volverMenuActual();
    }
}
