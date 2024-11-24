package fastpacketfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FXMLInicioSesionController implements Initializable {
    
    @FXML
    private TextField tf_numeroPersonal;
    @FXML
    private PasswordField pf_password;
    @FXML
    private Label lb_numeroFaltante;
    @FXML
    private Label lb_passwordFaltante;
    @FXML
    private Button btn_Ingresar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickIngresar(ActionEvent event) {
    }
    
}
