package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.model.Administrador;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdminMenuViewController {

    @FXML
    private Label lblUsuario;

    @FXML
    public void initialize() {
        lblUsuario.setText(
                ((Administrador) App.getInstance()
                        .getUsuarioActual()).getNombre()
        );
    }

    @FXML
    private void peliculas() {
        App.getInstance().mostrarPeliculas();
    }

    @FXML
    private void salas() {
        App.getInstance().mostrarSalas();
    }

    @FXML
    private void funciones() {
        App.getInstance().mostrarFunciones();
    }

    @FXML
    private void tarifas() {
        App.getInstance().mostrarTarifas();
    }

    @FXML
    private void personal() {
        App.getInstance().mostrarPersonal();
    }

    @FXML
    private void membresias() {
        App.getInstance().mostrarMembresiasAdmin();
    }

    @FXML
    private void inicio() {
        App.getInstance().mostrarSelector();
    }
}
