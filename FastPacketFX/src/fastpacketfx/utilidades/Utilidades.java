package fastpacketfx.utilidades;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Utilidades {
    public static void mostrarAlertaSimple(String titulo, String contenido, Alert.AlertType tipo){
            Alert alerta = new Alert(tipo);
            alerta.setTitle(titulo);
            alerta.setHeaderText(null);
            alerta.setContentText(contenido);
            alerta.showAndWait();
    }
    public static boolean mostrarAlertaConfirmacion(String titulo, String contenido){
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle(titulo);
            alerta.setHeaderText(null);
            alerta.setContentText(contenido);
            Optional <ButtonType>btnSeleccionado = alerta.showAndWait();
            return (btnSeleccionado.get()== ButtonType.OK);
    }
}
