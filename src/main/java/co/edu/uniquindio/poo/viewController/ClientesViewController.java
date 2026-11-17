package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class ClientesViewController {

    @FXML private TextField txtIdentificacion, txtNombre, txtAfiliacion;
    @FXML private ComboBox<TipoCliente> cbTipoCliente;
    @FXML private ListView<Cliente> listaClientes;
    @FXML private ComboBox<TipoMembresia> cbMembresia;
    @FXML private Label lblEstadoMembresia;

    @FXML
    public void initialize() {
        cbTipoCliente.getItems().setAll(TipoCliente.values());
        cbTipoCliente.setValue(TipoCliente.GENERAL);

        cbMembresia.getItems().setAll(TipoMembresia.values());
        cbMembresia.setValue(TipoMembresia.PLATA);

        actualizar();
    }

    @FXML
    private void registrar() {
        try {
            App.getInstance().getCine().registrarPersona(
                    new Cliente(
                            txtIdentificacion.getText(),
                            txtNombre.getText(),
                            txtAfiliacion.getText(),
                            cbTipoCliente.getValue()
                    )
            );

            ViewUtils.info("Clientes", "Cliente registrado");
            limpiar();
            actualizar();

        } catch (Exception e) {
            ViewUtils.error("Clientes", e.getMessage());
        }
    }

    @FXML
    private void seleccionar() {
        Cliente c = listaClientes.getSelectionModel().getSelectedItem();

        if (c == null) return;

        lblEstadoMembresia.setText(
                c.tieneMembresia()
                        ? c.getMembresia().toString()
                        + " | Descuento: "
                        + (int) c.obtenerDescuento() + "%"
                        : "SIN MEMBRESÍA - descuento 0%"
        );
    }

    @FXML
    private void asignarMembresia() {
        try {
            Cliente c = listaClientes.getSelectionModel().getSelectedItem();

            if (c == null) {
                throw new IllegalArgumentException("Seleccione un cliente");
            }

            Membresia m = new Membresia(
                    "MEM-" + c.getCodAfiliacion(),
                    LocalDate.now(),
                    cbMembresia.getValue()
            );

            App.getInstance().getCine().registrarMembresia(c, m);

            seleccionar();
            actualizar();

            ViewUtils.info(
                    "Membresía",
                    "Membresía asignada a " + c.getNombre()
            );

        } catch (Exception e) {
            ViewUtils.error("Membresía", e.getMessage());
        }
    }

    @FXML
    private void quitarMembresia() {
        Cliente c = listaClientes.getSelectionModel().getSelectedItem();

        if (c == null) return;

        c.setMembresia(null);
        seleccionar();
        actualizar();
    }

    private void limpiar() {
        txtIdentificacion.clear();
        txtNombre.clear();
        txtAfiliacion.clear();
    }

    private void actualizar() {
        listaClientes.getItems().setAll(
                App.getInstance().getCine().obtenerClientes()
        );
    }

    @FXML
    private void volver() {
        App.getInstance().volverMenuActual();
    }
}

