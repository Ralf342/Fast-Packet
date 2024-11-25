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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLEscenaPaqueteController implements Initializable {

    @FXML
    private TextField tf_buscar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickBuscar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenaPaqueteController.onClickBuscar()");
    }

    @FXML
    private void onClickAgregar(ActionEvent event) {
        agregar();
        System.out.println("fastpacketfx.FXMLEscenaPaqueteController.onClickAgregar()");
    }

    @FXML
    private void onClickEditar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenaPaqueteController.onClickEditar()");
    }

    @FXML
    private void onClickEliminar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenaPaqueteController.onClickEliminar()");
    }
    
    private void agregar(){
        /*try {
            Stage escenario = new Stage();
            FXMLLoader cargador = FXMLLoader.load(getClass().getResource("FXMLFormularioPaquete.fxml"));
            Parent vista = cargador.load();
            FXMLFormularioPaqueteController controlador = cargador.getController();
            
            Scene escenaPrincipal = new Scene(vista);
            escenario.setScene(escenaPrincipal);
            escenario.setTitle("Agregar");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        }catch(IOException e){
        }*/
        
        try{
            Stage escenario = new Stage();
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLFormularioPaquete.fxml"));
            Parent vista = cargador.load();
            //--
            FXMLFormularioPaqueteController controlador = cargador.getController();
            //--
            Scene escenaFormulario = new Scene(vista);
            escenario.setScene(escenaFormulario);
            escenario.setTitle("Agregar");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        }catch (Exception e){
        }
        
        
        }
    }
