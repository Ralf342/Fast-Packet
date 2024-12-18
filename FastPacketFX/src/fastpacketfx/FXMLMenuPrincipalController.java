package fastpacketfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
    @FXML
    private Pane pn_EscenarioUno;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void onClickEmpleado(ActionEvent event) {
        cargarEscenaEmpleado();
    }

    @FXML
    private void onClickUnidad(ActionEvent event) {
        cargarEscenaUnidad();
    }

    @FXML
    private void onClickCliente(ActionEvent event) {
        cargarEscenaCliente();
    }

    @FXML
    private void onClickPaquete(ActionEvent event) {
        cargarEscenaPaquete();
    }

    @FXML
    private void onClickEnvio(ActionEvent event) {
        cargarEscenaEnvio();
    }
    
    
    
    
    private void cargarEscenaEmpleado(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXMLEscenarioEmpleados.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            pn_EscenarioUno.getChildren().clear();
            pn_EscenarioUno.getChildren().add(pane);
            FXMLEscenarioEmpleadosController e = loader.getController();
        } catch (Exception e) {
        }
    }
    
    private void cargarEscenaUnidad(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXMLEscenarioUnidades.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            pn_EscenarioUno.getChildren().clear();
            pn_EscenarioUno.getChildren().add(pane);
            FXMLEscenarioUnidadesController u = loader.getController();
        } catch (Exception e) {
        }
    }
    
    private void cargarEscenaCliente(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXMLEscenarioClientes.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            pn_EscenarioUno.getChildren().clear();
            pn_EscenarioUno.getChildren().add(pane);
            FXMLEscenarioClientesController c = loader.getController();
        } catch (Exception e) {
        }
    }
    
    private void cargarEscenaPaquete(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXMLEscenaPaquete.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            pn_EscenarioUno.getChildren().clear();
            pn_EscenarioUno.getChildren().add(pane);
            FXMLEscenaPaqueteController p = loader.getController();
        } catch (Exception e) {
        }
    }
    
    private void cargarEscenaEnvio(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXMLEscenarioEnvio.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            pn_EscenarioUno.getChildren().clear();
            pn_EscenarioUno.getChildren().add(pane);
            FXMLEscenarioEnvioController ee = loader.getController();
        } catch (Exception e) {
        }
    }
}
