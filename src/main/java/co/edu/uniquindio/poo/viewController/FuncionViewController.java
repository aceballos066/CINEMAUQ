package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalTime;

public class FuncionViewController {
    @FXML private TextField txtCodigo, txtHora, txtValorBase;
    @FXML private DatePicker dpFecha;
    @FXML private ComboBox<Pelicula> cbPelicula;
    @FXML private ComboBox<Sala> cbSala;
    @FXML private ListView<Funcion> listaFunciones;

    @FXML
    public void initialize() {
        cbPelicula.getItems().setAll(
                App.getInstance().getCine().obtenerPeliculasDisponibles()
        );

        cbSala.getItems().setAll(
                App.getInstance().getCine().obtenerSalas()
        );

        dpFecha.setValue(java.time.LocalDate.now().plusDays(1));
        txtHora.setText("18:30");

        Tarifa general = App.getInstance().getCine()
                .buscarTarifa(TipoEntrada.GENERAL);

        txtValorBase.setText(
                general == null
                        ? "15000"
                        : String.valueOf((int) general.getValor())
        );

        actualizar();
    }

    @FXML
    private void programar() {
        try {
            LocalTime hora = LocalTime.parse(txtHora.getText().trim());

            Funcion f = new Funcion(
                    txtCodigo.getText(),
                    cbPelicula.getValue(),
                    cbSala.getValue(),
                    dpFecha.getValue(),
                    hora,
                    ViewUtils.leerDouble(
                            txtValorBase.getText(), "Valor base")
            );

            App.getInstance().getCine().programarFuncion(f);

            ViewUtils.info("Funciones", "Función programada");
            actualizar();

        } catch (Exception e) {
            ViewUtils.error(
                    "Funciones",
                    e.getMessage() + ". Use hora HH:mm"
            );
        }
    }

    @FXML
    private void cancelar() {
        cambiarEstado(false);
    }

    @FXML
    private void finalizar() {
        Funcion f = listaFunciones.getSelectionModel().getSelectedItem();

        if (f == null) {
            ViewUtils.error("Funciones", "Seleccione una función");
            return;
        }

        f.finalizar();
        actualizar();
    }

    private void cambiarEstado(boolean ignorado) {
        Funcion f = listaFunciones.getSelectionModel().getSelectedItem();

        if (f == null) {
            ViewUtils.error("Funciones", "Seleccione una función");
            return;
        }

        App.getInstance().getCine().cancelarFuncion(f.getCodigo());
        actualizar();
    }

    private void actualizar() {
        listaFunciones.getItems().setAll(
                App.getInstance().getCine().obtenerFunciones()
        );
    }

    @FXML
    private void volver() {
        App.getInstance().volverMenuActual();
    }
}

