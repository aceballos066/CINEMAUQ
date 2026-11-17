
package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.App;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import co.edu.uniquindio.poo.model.*;

public class PersonalViewController {

    @FXML
    private ListView<Administrador> listaAdministradores;

    @FXML
    private ListView<Operario> listaOperarios;

    @FXML
    public void initialize() {
        actualizar();
    }

    private void actualizar() {
        listaAdministradores.getItems().setAll(
                App.getInstance().getCine().obtenerAdministradores()
        );

        listaOperarios.getItems().setAll(
                App.getInstance().getCine().obtenerOperarios()
        );
    }

    @FXML
    private void registrarAdministrador() {
        App.getInstance().mostrarRegistro(
                "ADMINISTRADOR", "ADMIN"
        );
    }

    @FXML
    private void registrarOperario() {
        App.getInstance().mostrarRegistro(
                "OPERARIO", "ADMIN"
        );
    }

    @FXML
    private void volver() {
        App.getInstance().volverMenuActual();
    }
}

