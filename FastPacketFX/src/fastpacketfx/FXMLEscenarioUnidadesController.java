package fastpacketfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class FXMLEscenarioUnidadesController implements Initializable {

    @FXML
    private TextField tf_buscar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickBuscar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioUnidadesController.onClickBuscar()");
    }

    @FXML
    private void onClickAgregar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioUnidadesController.onClickAgregar()");
    }

    @FXML
    private void onClickEditar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioUnidadesController.onClickEditar()");
    }

    @FXML
    private void onClickEliminar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioUnidadesController.onClickEliminar()");
    }
    
}
