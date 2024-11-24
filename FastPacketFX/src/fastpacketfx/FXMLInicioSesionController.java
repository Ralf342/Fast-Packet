package fastpacketfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        if(validarCampos(tf_numeroPersonal.getText(),pf_password.getText())){
            irMenuPrincipal();
        }
    }
    
    private boolean validarCampos (String correo, String contrasenia){
        boolean camposValidos=true;
        lb_numeroFaltante.setText("");
        lb_passwordFaltante.setText("");
        if(correo.isEmpty()){
          camposValidos=false;
          lb_numeroFaltante.setText("*Número de personal obligatorio");
        }
        if(contrasenia.isEmpty()){
            camposValidos=false;
            lb_passwordFaltante.setText("*Contraseña obligatoria");
        }
        return camposValidos;
    }
    
    private void irMenuPrincipal(){
        try{
            Stage escenarioBase = (Stage) btn_Ingresar.getScene().getWindow();
            Parent menuPrincipal = FXMLLoader.load(getClass().getResource("FXMLMenuPrincipal.fxml"));
            Scene escenaPrincipal = new Scene(menuPrincipal);
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.setTitle("Menu");
            escenarioBase.show();
        }catch(IOException e){
            
        }
    }
}
