package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ClienteMenuViewController {

    @FXML
    private Label lblUsuario, lblMembresia;

    @FXML
    public void initialize() {
        Cliente cliente = (Cliente) App.getInstance().getUsuarioActual();

        lblUsuario.setText(cliente.getNombre());

        lblMembresia.setText(
                cliente.tieneMembresia()
                        ? cliente.getMembresia().getTipoMembresia().toString()
                        : "SIN MEMBRESÍA"
        );
    }

    @FXML
    private void cartelera() {
        App.getInstance().mostrarClientePortal("CARTELERA");
    }

    @FXML
    private void funciones() {
        App.getInstance().mostrarClientePortal("FUNCIONES");
    }

    @FXML
    private void disponibilidad() {
        App.getInstance().mostrarClientePortal("DISPONIBILIDAD");
    }

    @FXML
    private void tarifas() {
        App.getInstance().mostrarClientePortal("TARIFAS");
    }

    @FXML
    private void membresia() {
        App.getInstance().mostrarClientePortal("MEMBRESIA");
    }

    @FXML
    private void compras() {
        App.getInstance().mostrarClientePortal("COMPRAS");
    }

    @FXML
    private void inicio() {
        App.getInstance().mostrarSelector();
    }
}
