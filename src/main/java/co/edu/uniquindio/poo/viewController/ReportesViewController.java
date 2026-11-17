package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class ReportesViewController {

    @FXML
    private Label lblEntradas, lblIngresos, lblPelicula, lblOcupacion;

    @FXML
    private ComboBox<Funcion> cbFuncion;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private ListView<Funcion> listaFecha;

    @FXML
    public void initialize() {
        Cine cine = App.getInstance().getCine();

        lblEntradas.setText(
                String.valueOf(cine.contarEntradasVendidas())
        );

        lblIngresos.setText(
                String.format("$%,.0f", cine.calcularTotalIngresos())
        );

        Pelicula p = cine.obtenerPeliculaMasVendida();

        lblPelicula.setText(
                p == null ? "Sin ventas" : p.getTitulo()
        );

        cbFuncion.getItems().setAll(
                cine.obtenerFunciones()
        );

        dpFecha.setValue(LocalDate.now().plusDays(1));

        funcionesFecha();
    }

    @FXML
    private void ocupacion() {
        Funcion f = cbFuncion.getValue();

        lblOcupacion.setText(
                f == null
                        ? "Seleccione una función"
                        : String.format(
                        "%.1f%% - %d ocupados / %d disponibles",
                        f.calcularPorcentajeOcupacion(),
                        f.contarAsientosOcupados(),
                        f.contarAsientosDisponibles()
                )
        );
    }

    @FXML
    private void funcionesFecha() {
        listaFecha.getItems().setAll(
                App.getInstance().getCine()
                        .obtenerFuncionesPorFecha(dpFecha.getValue())
        );
    }

    @FXML
    private void volver() {
        App.getInstance().volverMenuActual();
    }
}

