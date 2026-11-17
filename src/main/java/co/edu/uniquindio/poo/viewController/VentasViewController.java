package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.model.Venta;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class VentasViewController {

    @FXML
    private ListView<Venta> listaVentas;

    @FXML
    private TextArea txtDetalle;

    @FXML
    public void initialize() {
        actualizar();
    }

    @FXML
    private void seleccionar() {
        Venta v = listaVentas.getSelectionModel().getSelectedItem();

        txtDetalle.setText(
                v == null ? "" : v.generarInformacion()
        );
    }

    @FXML
    private void cancelar() {
        Venta v = listaVentas.getSelectionModel().getSelectedItem();

        if (v == null) {
            ViewUtils.error("Ventas", "Seleccione una venta");
            return;
        }

        try {
            App.getInstance().getCine()
                    .cancelarVenta(v.getCodigo());

            ViewUtils.info(
                    "Ventas",
                    "Venta cancelada y asiento liberado"
            );

            actualizar();
            txtDetalle.clear();

        } catch (Exception e) {
            ViewUtils.error("Ventas", e.getMessage());
        }
    }

    private void actualizar() {
        listaVentas.getItems().setAll(
                App.getInstance().getCine().obtenerTodasLasVentas()
        );
    }

    @FXML
    private void volver() {
        App.getInstance().volverMenuActual();
    }
}

