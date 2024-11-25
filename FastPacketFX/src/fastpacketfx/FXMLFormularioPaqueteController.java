/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastpacketfx;

import java.io.IOException;
import static java.lang.Compiler.disable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joska_
 */
public class FXMLFormularioPaqueteController implements Initializable {

    @FXML
    private TextArea ta_Descripcion;
    @FXML
    private TextField tf_alto;
    @FXML
    private TextField tf_ancho;
    @FXML
    private TextField tf_profundidad;

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
