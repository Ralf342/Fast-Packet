package fastpacketfx;

import fastpacketfx.pojo.Colaborador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLFormularioUnidadController implements Initializable {

    @FXML
    private TextField tfMarca;
    @FXML
    private TextField tfModelo;
    @FXML
    private TextField tfAnio;
    @FXML
    private TextField tfVIN;
    @FXML
    private ComboBox<TipoUnidad> cbTipoUnidad;
    @FXML
    private TextField tfNII;
    @FXML
    private ComboBox<Colaborador> cbConductor;

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
    
    private void cerrarVentana(){
        Stage escenario = (Stage)tfAnio.getScene().getWindow();
        escenario.close();
        ( (Stage) tfAnio.getScene().getWindow()).close();
    }
    
}
