package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class RegistroPerfilViewController {
    @FXML private Label lblTitulo, lblCodigo, lblFecha, lblSalario, lblTipoCliente;
    @FXML private TextField txtIdentificacion, txtNombre, txtCodigo, txtSalario;
    @FXML private DatePicker dpFecha;
    @FXML private ComboBox<TipoCliente> cbTipoCliente;

    private String tipo;

    @FXML
    public void initialize() {
        tipo = App.getInstance().getTipoRegistro();
        lblTitulo.setText("Registrar " + tipo.toLowerCase());
        cbTipoCliente.getItems().setAll(TipoCliente.values());
        cbTipoCliente.setValue(TipoCliente.GENERAL);
        dpFecha.setValue(LocalDate.now());
        configurarCampos();
    }

    private void configurarCampos() {
        boolean operario = "OPERARIO".equals(tipo);
        boolean cliente = "CLIENTE".equals(tipo);

        lblFecha.setVisible(operario);
        lblFecha.setManaged(operario);
        dpFecha.setVisible(operario);
        dpFecha.setManaged(operario);

        lblSalario.setVisible(operario);
        lblSalario.setManaged(operario);
        txtSalario.setVisible(operario);
        txtSalario.setManaged(operario);

        lblTipoCliente.setVisible(cliente);
        lblTipoCliente.setManaged(cliente);
        cbTipoCliente.setVisible(cliente);
        cbTipoCliente.setManaged(cliente);

        lblCodigo.setText(cliente ? "Código afiliación"
                : operario ? "Código empleado"
                : "Código administrador");
    }

    @FXML
    private void guardar() {
        try {
            String id = txtIdentificacion.getText();
            String nombre = txtNombre.getText();
            String codigo = txtCodigo.getText();

            Persona persona;

            if ("ADMINISTRADOR".equals(tipo)) {
                persona = new Administrador(id, nombre, codigo);
            } else if ("OPERARIO".equals(tipo)) {
                persona = new Operario(id, nombre, codigo,
                        dpFecha.getValue(),
                        ViewUtils.leerDouble(txtSalario.getText(), "Salario"));
            } else {
                persona = new Cliente(id, nombre, codigo,
                        cbTipoCliente.getValue());
            }

            App.getInstance().getCine().registrarPersona(persona);
            ViewUtils.info("Registro", "Perfil registrado correctamente");
            App.getInstance().volverDesdeRegistro();

        } catch (Exception e) {
            ViewUtils.error("No se pudo registrar", e.getMessage());
        }
    }

    @FXML
    private void volver() {
        App.getInstance().volverDesdeRegistro();
    }
}
