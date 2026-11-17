package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class SelectorViewController {
    @FXML private ComboBox<Administrador> cbAdministradores;
    @FXML private ComboBox<Operario> cbOperarios;
    @FXML private ComboBox<Cliente> cbClientes;
    @FXML private Label lblResumen;

    @FXML
    public void initialize() {
        Cine cine = App.getInstance().getCine();
        cbAdministradores.getItems().setAll(cine.obtenerAdministradores());
        cbOperarios.getItems().setAll(cine.obtenerOperarios());
        cbClientes.getItems().setAll(cine.obtenerClientes());
        if (!cbAdministradores.getItems().isEmpty()) cbAdministradores.getSelectionModel().selectFirst();
        if (!cbOperarios.getItems().isEmpty()) cbOperarios.getSelectionModel().selectFirst();
        if (!cbClientes.getItems().isEmpty()) cbClientes.getSelectionModel().selectFirst();
        lblResumen.setText("Perfiles precargados: " + cine.obtenerAdministradores().size() + " administrador, " +
                cine.obtenerOperarios().size() + " operarios y " + cine.obtenerClientes().size() + " clientes de demostración.");
    }

    @FXML private void ingresarAdministrador() {
        Administrador seleccionado = cbAdministradores.getValue();
        if (seleccionado == null) ViewUtils.error("Perfil", "Seleccione un administrador");
        else App.getInstance().entrarAdministrador(seleccionado);
    }

    @FXML private void ingresarOperario() {
        Operario seleccionado = cbOperarios.getValue();
        if (seleccionado == null) ViewUtils.error("Perfil", "Seleccione un operario");
        else App.getInstance().entrarOperario(seleccionado);
    }

    @FXML private void ingresarCliente() {
        Cliente seleccionado = cbClientes.getValue();
        if (seleccionado == null) ViewUtils.error("Perfil", "Seleccione un cliente");
        else App.getInstance().entrarCliente(seleccionado);
    }

    @FXML private void registrarAdministrador() { App.getInstance().mostrarRegistro("ADMINISTRADOR", "SELECTOR"); }
    @FXML private void registrarOperario() { App.getInstance().mostrarRegistro("OPERARIO", "SELECTOR"); }
    @FXML private void registrarCliente() { App.getInstance().mostrarRegistro("CLIENTE", "SELECTOR"); }
}
