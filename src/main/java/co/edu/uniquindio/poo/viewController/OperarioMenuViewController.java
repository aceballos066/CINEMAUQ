
package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.model.Operario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OperarioMenuViewController {

    @FXML
    private Label lblUsuario;

    @FXML
    public void initialize() {
        lblUsuario.setText(
                ((Operario) App.getInstance().getUsuarioActual()).getNombre()
        );
    }

    @FXML
    private void clientes() {
        App.getInstance().mostrarClientes();
    }

    @FXML
    private void nuevaVenta() {
        App.getInstance().mostrarVenta();
    }

    @FXML
    private void ventas() {
        App.getInstance().mostrarVentas();
    }

    @FXML
    private void disponibilidad() {
        App.getInstance().mostrarDisponibilidad();
    }

    @FXML
    private void reportes() {
        App.getInstance().mostrarReportes();
    }

    @FXML
    private void inicio() {
        App.getInstance().mostrarSelector();
    }
}
