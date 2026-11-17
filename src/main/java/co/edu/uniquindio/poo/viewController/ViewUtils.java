
package co.edu.uniquindio.poo.viewController;

import javafx.scene.control.Alert;

public final class ViewUtils {
    private ViewUtils() { }

    public static void info(String titulo, String mensaje) { mostrar(Alert.AlertType.INFORMATION, titulo, mensaje); }
    public static void error(String titulo, String mensaje) { mostrar(Alert.AlertType.ERROR, titulo, mensaje); }

    private static void mostrar(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static double leerDouble(String texto, String campo) {
        try { return Double.parseDouble(texto.trim()); }
        catch (Exception e) { throw new IllegalArgumentException(campo + " debe ser numérico"); }
    }

    public static int leerInt(String texto, String campo) {
        try { return Integer.parseInt(texto.trim()); }
        catch (Exception e) { throw new IllegalArgumentException(campo + " debe ser un número entero"); }
    }
}
