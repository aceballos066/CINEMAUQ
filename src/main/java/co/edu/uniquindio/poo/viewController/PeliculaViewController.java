package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PeliculaViewController {

    @FXML private TextField txtCodigo, txtTitulo, txtDuracion;
    @FXML private ComboBox<Genero> cbGenero;
    @FXML private ComboBox<Clasificacion> cbClasificacion;
    @FXML private ListView<Pelicula> listaPeliculas;

    @FXML
    public void initialize() {
        cbGenero.getItems().setAll(Genero.values());
        cbClasificacion.getItems().setAll(Clasificacion.values());
        cbGenero.setValue(Genero.ACCION);
        cbClasificacion.setValue(Clasificacion.TODOS);
        actualizar();
    }

    @FXML
    private void seleccionar() {
        Pelicula p = listaPeliculas.getSelectionModel().getSelectedItem();
        if (p == null) return;

        txtCodigo.setText(p.getCodigo());
        txtCodigo.setDisable(true);
        txtTitulo.setText(p.getTitulo());
        txtDuracion.setText(String.valueOf(p.getDuracionMin()));
        cbGenero.setValue(p.getGenero());
        cbClasificacion.setValue(p.getClasificacion());
    }

    @FXML
    private void registrar() {
        try {
            App.getInstance().getCine().registrarPelicula(
                    new Pelicula(
                            txtCodigo.getText(),
                            txtTitulo.getText(),
                            cbGenero.getValue(),
                            ViewUtils.leerInt(txtDuracion.getText(), "Duración"),
                            cbClasificacion.getValue()
                    )
            );

            ViewUtils.info("Películas", "Película registrada");
            limpiar();
            actualizar();

        } catch (Exception e) {
            ViewUtils.error("Películas", e.getMessage());
        }
    }

    @FXML
    private void modificar() {
        try {
            boolean ok = App.getInstance().getCine().modificarPelicula(
                    txtCodigo.getText(),
                    new Pelicula(
                            "TMP",
                            txtTitulo.getText(),
                            cbGenero.getValue(),
                            ViewUtils.leerInt(txtDuracion.getText(), "Duración"),
                            cbClasificacion.getValue()
                    )
            );

            if (!ok) {
                throw new IllegalArgumentException(
                        "Seleccione una película existente"
                );
            }

            ViewUtils.info("Películas", "Película modificada");
            limpiar();
            actualizar();

        } catch (Exception e) {
            ViewUtils.error("Películas", e.getMessage());
        }
    }

    @FXML
    private void deshabilitar() {
        if (App.getInstance().getCine()
                .deshabilitarPelicula(txtCodigo.getText())) {

            ViewUtils.info("Películas", "Película deshabilitada");
            limpiar();
            actualizar();

        } else {
            ViewUtils.error("Películas", "Seleccione una película");
        }
    }

    @FXML
    private void limpiar() {
        txtCodigo.clear();
        txtTitulo.clear();
        txtDuracion.clear();
        txtCodigo.setDisable(false);
        listaPeliculas.getSelectionModel().clearSelection();
    }

    private void actualizar() {
        listaPeliculas.getItems().setAll(
                App.getInstance().getCine().obtenerPeliculas()
        );
    }

    @FXML
    private void volver() {
        App.getInstance().volverMenuActual();
    }
}
