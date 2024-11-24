/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastpacketfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author joska_
 */
public class FXMLMenuPrincipalController implements Initializable {

    @FXML
    private Button btn_Empleado;
    @FXML
    private Button btn_Unidades;
    @FXML
    private Button btn_clientes;
    @FXML
    private Button btn_paquetes;
    @FXML
    private Button btn_envios;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
