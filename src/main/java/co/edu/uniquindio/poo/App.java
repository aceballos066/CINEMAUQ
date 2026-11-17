
package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static App instance;
    private Stage stage;
    private Cine cine;
    private Persona usuarioActual;
    private String tipoRegistro;
    private String retornoRegistro;
    private String seccionCliente = "CARTELERA";

    @Override
    public void start(Stage stage) {
        instance = this;
        this.stage = stage;
        this.cine = DatosIniciales.crearCine();
        stage.setMinWidth(1040);
        stage.setMinHeight(700);
        mostrarSelector();
        stage.show();
    }

    public static App getInstance() { return instance; }
    public Cine getCine() { return cine; }
    public Persona getUsuarioActual() { return usuarioActual; }
    public String getTipoRegistro() { return tipoRegistro; }
    public String getSeccionCliente() { return seccionCliente; }

    public void mostrarSelector() {
        usuarioActual = null;
        cargarVista("selector.fxml", "CINEMAUQ - Selección de perfil");
    }

    public void entrarAdministrador(Administrador administrador) {
        usuarioActual = administrador;
        cargarVista("admin_menu.fxml", "CINEMAUQ - Administrador");
    }

    public void entrarOperario(Operario operario) {
        usuarioActual = operario;
        cargarVista("operario_menu.fxml", "CINEMAUQ - Operario");
    }

    public void entrarCliente(Cliente cliente) {
        usuarioActual = cliente;
        cargarVista("cliente_menu.fxml", "CINEMAUQ - Cliente");
    }

    public void volverMenuActual() {
        if (usuarioActual instanceof Administrador)
            entrarAdministrador((Administrador) usuarioActual);
        else if (usuarioActual instanceof Operario)
            entrarOperario((Operario) usuarioActual);
        else if (usuarioActual instanceof Cliente)
            entrarCliente((Cliente) usuarioActual);
        else
            mostrarSelector();
    }

    public void mostrarRegistro(String tipo, String retorno) {
        this.tipoRegistro = tipo;
        this.retornoRegistro = retorno;
        cargarVista("registro_perfil.fxml",
                "CINEMAUQ - Registrar " + tipo.toLowerCase());
    }

    public void volverDesdeRegistro() {
        if ("ADMIN".equals(retornoRegistro)
                && usuarioActual instanceof Administrador)
            entrarAdministrador((Administrador) usuarioActual);
        else if ("OPERARIO".equals(retornoRegistro)
                && usuarioActual instanceof Operario)
            entrarOperario((Operario) usuarioActual);
        else
            mostrarSelector();
    }

    public void mostrarPeliculas() {
        cargarVista("peliculas.fxml", "CINEMAUQ - Películas");
    }

    public void mostrarSalas() {
        cargarVista("salas.fxml", "CINEMAUQ - Salas");
    }

    public void mostrarFunciones() {
        cargarVista("funciones.fxml", "CINEMAUQ - Funciones");
    }

    public void mostrarTarifas() {
        cargarVista("tarifas.fxml", "CINEMAUQ - Tarifas");
    }

    public void mostrarPersonal() {
        cargarVista("personal.fxml", "CINEMAUQ - Personal");
    }

    public void mostrarMembresiasAdmin() {
        cargarVista("membresias_admin.fxml", "CINEMAUQ - Membresías");
    }

    public void mostrarClientes() {
        cargarVista("clientes.fxml", "CINEMAUQ - Clientes");
    }

    public void mostrarVenta() {
        cargarVista("venta.fxml", "CINEMAUQ - Nueva venta");
    }

    public void mostrarVentas() {
        cargarVista("ventas.fxml", "CINEMAUQ - Ventas");
    }

    public void mostrarDisponibilidad() {
        cargarVista("disponibilidad.fxml", "CINEMAUQ - Disponibilidad");
    }

    public void mostrarReportes() {
        cargarVista("reportes.fxml", "CINEMAUQ - Reportes");
    }

    public void mostrarClientePortal() {
        mostrarClientePortal("CARTELERA");
    }

    public void mostrarClientePortal(String seccion) {
        this.seccionCliente = seccion;
        cargarVista("cliente_portal.fxml",
                "CINEMAUQ - Portal del cliente");
    }

    private void cargarVista(String archivo, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    App.class.getResource(
                            "/co/edu/uniquindio/poo/" + archivo));

            Parent root = loader.load();
            Scene scene = new Scene(root, 1180, 760);

            String css = App.class.getResource(
                    "/co/edu/uniquindio/poo/styles.css").toExternalForm();

            scene.getStylesheets().add(css);
            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (IOException e) {
            throw new RuntimeException(
                    "No fue posible cargar la vista " + archivo, e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
