
package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MembresiasAdminViewController {

    @FXML private TextArea txtNiveles;
    @FXML private ListView<Cliente> listaClientes;
    @FXML private Label lblDetalle;

    @FXML
    public void initialize() {
        StringBuilder sb = new StringBuilder();

        for (TipoMembresia tipo : TipoMembresia.values()) {
            sb.append(tipo.name())
                    .append(" → ")
                    .append((int) tipo.getPorcentajeDescuento())
                    .append("% de descuento\n");
        }

        txtNiveles.setText(sb.toString());

        listaClientes.getItems().setAll(
                App.getInstance().getCine().obtenerClientes()
        );
    }

    @FXML
    private void seleccionar() {
        Cliente c = listaClientes.getSelectionModel().getSelectedItem();

        if (c == null) return;

        lblDetalle.setText(
                c.tieneMembresia()
                        ? c.getNombre() + ": " + c.getMembresia()
                        : c.getNombre() + ": SIN MEMBRESÍA"
        );
    }

    @FXML
    private void volver() {
        App.getInstance().volverMenuActual();
    }
}
