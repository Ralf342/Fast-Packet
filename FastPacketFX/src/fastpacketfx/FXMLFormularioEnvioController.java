package fastpacketfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class FXMLFormularioEnvioController implements Initializable {

    @FXML
    private TextField tfCalleOrigen;
    @FXML
    private TextField tfColoniaOrigen;
    @FXML
    private TextField tfCPOrigen;
    @FXML
    private TextField tfCiudadOrigen;
    @FXML
    private TextField tfEstadoOrigen;
    @FXML
    private TextField tfCosto;
    @FXML
    private TextField tfCiudadDestino;
    @FXML
    private TextField tfCPDestino;
    @FXML
    private TextField tfColoniaDestino;
    @FXML
    private TextField tfCalleDestino;
    @FXML
    private ComboBox<?> cbCliente;
    @FXML
    private ComboBox<?> cbEnvio;
    @FXML
    private TextField tfNumGuia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickAgregar(ActionEvent event) {
    }

    @FXML
    private void onClickCancelar(ActionEvent event) {
    }
    
}
