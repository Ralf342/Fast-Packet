package fastpacketfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class FXMLEscenarioEnvioController implements Initializable {

    @FXML
    private TextField tf_buscar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickBuscar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioEnvioController.onClickBuscar()");
    }

    @FXML
    private void onClickAgregar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioEnvioController.onClickAgregar()");
    }

    @FXML
    private void onClickActualizar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioEnvioController.onClickActualizar()");
    }
    
}
