package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class VentaViewController {

    @FXML private ComboBox<Cliente> cbCliente;
    @FXML private ComboBox<Pelicula> cbPelicula;
    @FXML private ComboBox<Funcion> cbFuncion;
    @FXML private ComboBox<Asiento> cbAsiento;
    @FXML private ComboBox<TipoEntrada> cbTipoEntrada;

    @FXML private Label lblCliente, lblPelicula, lblFuncion;
    @FXML private Label lblMembresia, lblPrecioOriginal;
    @FXML private Label lblDescuento, lblTotal;

    @FXML private Button btnConfirmar;

    @FXML
    public void initialize() {
        cbCliente.getItems().setAll(
                App.getInstance().getCine().obtenerClientes()
        );

        cbPelicula.getItems().setAll(
                App.getInstance().getCine().obtenerPeliculasDisponibles()
        );

        cbTipoEntrada.getItems().setAll(TipoEntrada.values());

        cbPelicula.setDisable(true);
        cbFuncion.setDisable(true);
        cbAsiento.setDisable(true);
        cbTipoEntrada.setDisable(true);
        btnConfirmar.setDisable(true);

        limpiarResumen();
    }

    @FXML
    private void seleccionarCliente() {
        Cliente c = cbCliente.getValue();
        if (c == null) return;

        lblCliente.setText(
                c.getNombre() + " - " + c.getTipoCliente()
        );

        lblMembresia.setText(
                c.tieneMembresia()
                        ? c.getMembresia().getTipoMembresia().name()
                        + " - " + (int) c.obtenerDescuento() + "%"
                        : "SIN MEMBRESÍA - 0%"
        );

        cbPelicula.setDisable(false);
        cbFuncion.setDisable(true);
        cbAsiento.setDisable(true);
        cbTipoEntrada.setDisable(true);
        btnConfirmar.setDisable(true);

        cbFuncion.getItems().clear();
        cbAsiento.getItems().clear();
    }

    @FXML
    private void seleccionarPelicula() {
        Pelicula p = cbPelicula.getValue();
        if (p == null) return;

        lblPelicula.setText(
                p.getTitulo() + " | " + p.getGenero()
                        + " | " + p.getDuracionMin() + " min | "
                        + p.getClasificacion()
        );

        cbFuncion.getItems().clear();

        for (Funcion funcion :
                App.getInstance().getCine()
                        .obtenerFuncionesPorPelicula(p.getTitulo())) {

            if (funcion.estaProgramada()) {
                cbFuncion.getItems().add(funcion);
            }
        }

        cbFuncion.setDisable(cbFuncion.getItems().isEmpty());
        cbAsiento.setDisable(true);
        cbTipoEntrada.setDisable(true);
        btnConfirmar.setDisable(true);
    }

    @FXML
    private void seleccionarFuncion() {
        Funcion f = cbFuncion.getValue();
        if (f == null) return;

        lblFuncion.setText(
                f.getFecha() + " " + f.getHoraInicio()
                        + " | " + f.getSala().getNombre()
                        + " | Disponibles: "
                        + f.contarAsientosDisponibles()
        );

        cbAsiento.getItems().setAll(
                f.obtenerAsientosDisponibles()
        );

        cbAsiento.setDisable(false);
        cbTipoEntrada.setDisable(true);
        btnConfirmar.setDisable(true);
    }

    @FXML
    private void seleccionarAsiento() {
        if (cbAsiento.getValue() == null) return;

        cbTipoEntrada.setDisable(false);

        Cliente c = cbCliente.getValue();

        if (c != null) {
            try {
                cbTipoEntrada.setValue(
                        TipoEntrada.valueOf(
                                c.getTipoCliente().name()
                        )
                );
            } catch (Exception ignored) {
                cbTipoEntrada.setValue(TipoEntrada.GENERAL);
            }

            calcularPrecio();
        }
    }

    @FXML
    private void calcularPrecio() {
        Cliente c = cbCliente.getValue();
        Funcion f = cbFuncion.getValue();
        TipoEntrada tipo = cbTipoEntrada.getValue();
        Asiento asiento = cbAsiento.getValue();

        if (c == null || f == null || tipo == null
                || asiento == null) {
            btnConfirmar.setDisable(true);
            return;
        }

        try {
            Tarifa tarifa = App.getInstance().getCine()
                    .buscarTarifa(tipo);

            Tarifa general = App.getInstance().getCine()
                    .buscarTarifa(TipoEntrada.GENERAL);

            double ajuste =
                    f.getValorBase() - general.getValor();

            double original =
                    tarifa.getValor()
                            + ajuste
                            + f.getSala().obtenerRecargo();

            double total = App.getInstance().getCine()
                    .calcularValorEntrada(f, c, tipo);

            lblPrecioOriginal.setText(
                    String.format("$%,.0f", original)
            );

            lblDescuento.setText(
                    String.format("$%,.0f", original - total)
            );

            lblTotal.setText(
                    String.format("$%,.0f", total)
            );

            btnConfirmar.setDisable(false);

        } catch (Exception e) {
            ViewUtils.error("Precio", e.getMessage());
            btnConfirmar.setDisable(true);
        }
    }

    @FXML
    private void confirmar() {
        try {
            Operario op = (Operario)
                    App.getInstance().getUsuarioActual();

            Venta venta = App.getInstance().getCine()
                    .venderEntrada(
                            cbCliente.getValue(),
                            op,
                            cbFuncion.getValue().getCodigo(),
                            cbTipoEntrada.getValue(),
                            cbAsiento.getValue().getCodigo()
                    );

            ViewUtils.info(
                    "Venta registrada",
                    venta.generarInformacion()
            );

            cbFuncion.getItems().clear();

            for (Funcion funcion :
                    App.getInstance().getCine()
                            .obtenerFuncionesPorPelicula(
                                    cbPelicula.getValue().getTitulo()
                            )) {

                if (funcion.estaProgramada()) {
                    cbFuncion.getItems().add(funcion);
                }
            }

            seleccionarFuncion();
            btnConfirmar.setDisable(true);

        } catch (Exception e) {
            ViewUtils.error("Venta", e.getMessage());
        }
    }

    private void limpiarResumen() {
        lblCliente.setText("Seleccione un cliente");
        lblPelicula.setText("Seleccione una película");
        lblFuncion.setText("Seleccione una función");
        lblMembresia.setText("-");
        lblPrecioOriginal.setText("$0");
        lblDescuento.setText("$0");
        lblTotal.setText("$0");
    }

    @FXML
    private void volver() {
        App.getInstance().volverMenuActual();
    }
}
