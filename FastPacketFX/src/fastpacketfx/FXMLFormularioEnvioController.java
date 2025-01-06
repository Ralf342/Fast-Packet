package fastpacketfx;

import fastpacketfx.modelo.dao.EnvioDAO;
import fastpacketfx.pojo.Cliente;
import fastpacketfx.pojo.Envio;
import fastpacketfx.pojo.Estatus;
import fastpacketfx.pojo.Mensaje;
import fastpacketfx.pojo.Unidad;
import fastpacketfx.utilidades.Utilidades;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLFormularioEnvioController implements Initializable {

    private ObservableList<Estatus> estatus;
    private ObservableList<Cliente> cliente;
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
    @FXML
    private ComboBox<Unidad> cbUnidad;
    @FXML
    private Label lbUnidadFaltante;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarEstatus();
    }    

    @FXML
    private void onClickAgregar(ActionEvent event) {
        String calleOrigen = tfCalleOrigen.getText();
        String coloniaOrigen = tfColoniaOrigen.getText();
        String codigoPostalOrigen = tfCPOrigen.getText();
        String ciudadOrigen = tfCiudadOrigen.getText();
        String estadoOrigen = tfEstadoOrigen.getText();
        String costoString = tfCosto.getText();
        Float costo = (costoString.isEmpty() || !esDecimal(costoString)) ?  0 : Float.valueOf(costoString);
        int idCliente =(cbCliente.getSelectionModel().getSelectedItem() !=null)
               ? cbCliente.getSelectionModel().getSelectedItem().getIdCliente(): 0;
        int idestatus =(cbEstatus.getSelectionModel().getSelectedItem() !=null)
               ? cbEstatus.getSelectionModel().getSelectedItem().getIdEstatus(): 0;
        int idUnidad = (cbUnidad.getSelectionModel().getSelectedItem() !=null)
               ? cbUnidad.getSelectionModel().getSelectedItem().getIdUnidad(): 0;
        
    }

    @FXML
    private void onClickCancelar(ActionEvent event) {
        cerrarVentana();
    }
    
    private void guardarDatosEnvio(Envio envio){
        Mensaje msj = EnvioDAO.registrarEnvio(envio);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Envio registrado", "El envio se registro correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            //observador.notificarOperacionExitosa("Guardar", cliente.getNombre());
        }else{
            Utilidades.mostrarAlertaSimple("Error al guardar",msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void cargarEstatus(){
        estatus = FXCollections.observableArrayList();
        List<Estatus> listaWS = EnvioDAO.obtenerEstatus();
        if(listaWS !=null){
            estatus.addAll(listaWS);
            cbEstatus.setItems(estatus);
        }
    }
    
    private boolean soloLetras(String cadena) {
        return cadena.matches("[a-zA-Z ]+"); // Permite letras y espacios
    }
    
    //Verifica que sea un numero
    private boolean esNumerico(String cadena) {
        return cadena.matches("\\d+"); // Verifica que la cadena contenga solo dígitos
    }
    
    private boolean esDecimal(String cadena) {
        return cadena.matches("\\d+(\\.\\d+)?"); // Permite enteros y decimales positivos
    }
    
    private boolean sonCamposValidos(Cliente cliente){
        boolean camposValidos=true;
        lbClienteFaltante.setText(" ");
        lbCalleFaltante.setText(" ");
        lbCodigoPostalFaltante.setText(" ");
        lbColoniaFaltante.setText(" ");
        lbCiudadFaltante.setText(" ");
        lbEstadoFaltante.setText(" ");
        lbCostoFaltante.setText(" ");
        lbNumeroGuiaFaltante.setText(" ");
        lbEstatusFaltante.setText(" ");
        
        return camposValidos;
    }
    
    private void cerrarVentana(){
        Stage escenario = (Stage)tfNumGuia.getScene().getWindow();
        escenario.close();
        ( (Stage) tfNumGuia.getScene().getWindow()).close();
    }
    
}
