package fastpacketfx;

import fastpacketfx.modelo.dao.LoginDAO;
import fastpacketfx.pojo.Login;
import fastpacketfx.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
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
        String noPersonal = tf_numeroPersonal.getText();
        String contrasenia = pf_password.getText();
        if(validarCampos(noPersonal,contrasenia)){
            verificaCredencialesAcceso(noPersonal,contrasenia);
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
    
    private void verificaCredencialesAcceso(String noPersonal, String contrasenia){
        Login respuestaLogin = LoginDAO.iniciarSesion(noPersonal, contrasenia);
        System.out.println("Que me esta mandando esto? "+noPersonal+contrasenia);
        System.out.println("Aqui esta mandando este mensaje: "+respuestaLogin.getMensaje());
        if(!respuestaLogin.isError()){
            Utilidades.mostrarAlertaSimple("Credenciales correctas","Bienvenid@ al programa "+respuestaLogin.getColaborador().getNombre() +
                        " "+respuestaLogin.getColaborador().getApellidoMaterno()+ " "+
                    respuestaLogin.getColaborador().getApellidoPaterno(),Alert.AlertType.INFORMATION);
            tf_numeroPersonal.setText("");
            pf_password.setText("");
            irMenuPrincipal();
        }else{
            Utilidades.mostrarAlertaSimple("Credenciales incorrectas","Favor de verificar sus credenciales e intentarlo de nuevo",Alert.AlertType.ERROR);
            pf_password.setText("");
        }
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