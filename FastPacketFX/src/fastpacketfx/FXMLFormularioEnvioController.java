package fastpacketfx;

import fastpacketfx.pojo.Cliente;
import fastpacketfx.pojo.Estatus;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private ComboBox<Cliente> cbCliente;
    @FXML
    private TextField tfNumGuia;
    @FXML
    private TextField tfEstadoDestino;
    @FXML
    private Label lbClienteFaltante;
    @FXML
    private Label lbCalleFaltante;
    @FXML
    private Label lbColoniaFaltante;
    @FXML
    private Label lbCodigoPostalFaltante;
    @FXML
    private Label lbCiudadFaltante;
    @FXML
    private Label lbEstadoFaltante;
    @FXML
    private Label lbCostoFaltante;
    @FXML
    private Label lbNumeroGuiaFaltante;
    @FXML
    private Label lbEstatusFaltante;
    @FXML
    private ComboBox<Estatus> cbEstatus;

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
        cerrarVentana();
    }
    
    private void cerrarVentana(){
        Stage escenario = (Stage)tfNumGuia.getScene().getWindow();
        escenario.close();
        ( (Stage) tfNumGuia.getScene().getWindow()).close();
    }
    
}
