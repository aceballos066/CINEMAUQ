package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TarifaViewController {

    @FXML private ComboBox<TipoEntrada> cbTipo;
    @FXML private TextField txtValor;
    @FXML private ListView<Tarifa> listaTarifas;

    @FXML
    public void initialize() {
        cbTipo.getItems().setAll(TipoEntrada.values());
        cbTipo.setValue(TipoEntrada.GENERAL);
        cargarValor();
        actualizar();
    }

    @FXML
    private void cargarValor() {
        Tarifa t = App.getInstance().getCine()
                .buscarTarifa(cbTipo.getValue());

        txtValor.setText(t == null
                ? ""
                : String.valueOf((int) t.getValor()));
    }

    @FXML
    private void guardar() {
        try {
            App.getInstance().getCine().configurarTarifa(
                    cbTipo.getValue(),
                    ViewUtils.leerDouble(txtValor.getText(), "Valor")
            );

            ViewUtils.info("Tarifas", "Tarifa actualizada");
            actualizar();

        } catch (Exception e) {
            ViewUtils.error("Tarifas", e.getMessage());
        }
    }

    private void actualizar() {
        listaTarifas.getItems().setAll(
                App.getInstance().getCine().obtenerTarifas()
        );
    }

    @FXML
    private void volver() {
        App.getInstance().volverMenuActual();
    }
}
