package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SalaViewController {
    @FXML private TextField txtCodigo, txtNombre, txtCapacidad, txtFilas, txtColumnas;
    @FXML private ComboBox<TipoSala> cbTipo;
    @FXML private ListView<Sala> listaSalas;
    @FXML private ListView<Asiento> listaAsientos;

    @FXML
    public void initialize() {
        cbTipo.getItems().setAll(TipoSala.values());
        cbTipo.setValue(TipoSala.TRADICIONAL);
        actualizar();
    }

    @FXML
    private void seleccionar() {
        Sala s = listaSalas.getSelectionModel().getSelectedItem();
        if (s == null) return;

        txtCodigo.setText(s.getCodigo());
        txtCodigo.setDisable(true);
        txtNombre.setText(s.getNombre());
        txtCapacidad.setText(String.valueOf(s.getCapacidadMax()));
        txtFilas.setText(String.valueOf(s.getFilas()));
        txtColumnas.setText(String.valueOf(s.getColumnas()));
        cbTipo.setValue(s.getTipoSala());
        listaAsientos.getItems().setAll(s.getAsientos());
    }

    private Sala construir(String codigo) {
        return new Sala(
                codigo,
                txtNombre.getText(),
                ViewUtils.leerInt(txtCapacidad.getText(), "Capacidad"),
                ViewUtils.leerInt(txtFilas.getText(), "Filas"),
                ViewUtils.leerInt(txtColumnas.getText(), "Columnas"),
                cbTipo.getValue()
        );
    }

    @FXML
    private void registrar() {
        try {
            App.getInstance().getCine().registrarSala(
                    construir(txtCodigo.getText())
            );
            ViewUtils.info("Salas", "Sala registrada");
            limpiar();
            actualizar();
        } catch (Exception e) {
            ViewUtils.error("Salas", e.getMessage());
        }
    }

    @FXML
    private void modificar() {
        try {
            boolean ok = App.getInstance().getCine().modificarSala(
                    txtCodigo.getText(), construir("TMP")
            );
            if (!ok) {
                throw new IllegalArgumentException("Seleccione una sala");
            }
            ViewUtils.info("Salas", "Sala modificada");
            limpiar();
            actualizar();
        } catch (Exception e) {
            ViewUtils.error("Salas", e.getMessage());
        }
    }

    @FXML
    private void deshabilitar() {
        if (App.getInstance().getCine()
                .deshabilitarSala(txtCodigo.getText())) {
            ViewUtils.info("Salas", "Sala fuera de servicio");
            limpiar();
            actualizar();
        } else {
            ViewUtils.error("Salas", "Seleccione una sala");
        }
    }

    @FXML
    private void limpiar() {
        txtCodigo.clear();
        txtNombre.clear();
        txtCapacidad.clear();
        txtFilas.clear();
        txtColumnas.clear();
        txtCodigo.setDisable(false);
        listaAsientos.getItems().clear();
    }

    private void actualizar() {
        listaSalas.getItems().setAll(
                App.getInstance().getCine().obtenerSalas()
        );
    }

    @FXML
    private void volver() {
        App.getInstance().volverMenuActual();
    }
}
