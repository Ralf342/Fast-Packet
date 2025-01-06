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
        Integer codigoPostal = (codigoPostalOrigen.isEmpty() || !esNumerico(codigoPostalOrigen)) ? 0: Integer.valueOf(codigoPostalOrigen);
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
        
        Envio envio = new Envio();
        envio.setCalle(calleOrigen);
        envio.setColoniaOrigen(coloniaOrigen);
        envio.setCodigoPostalOrigen(codigoPostal);
        envio.setCiudadOrigen(ciudadOrigen);
        envio.setEstadoOrigen(estadoOrigen);
        envio.setCosto(costo);
        envio.setIdClienteDestino(idCliente);
        envio.setIdEstatus(idestatus);
        envio.setIdUnidad(idUnidad);
        
        if(sonCamposValidos(envio)){
            guardarDatosEnvio(envio);
        }else{
            Utilidades.mostrarAlertaSimple("Datos faltantes", "Existen ciertos campos que deben ser llenados, favor de verificar", Alert.AlertType.INFORMATION);
        }
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
    
    private boolean sonCamposValidos(Envio envio){
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
        lbUnidadFaltante.setText(" ");
        
        if(envio.getCalleOrigen().isEmpty()){
            camposValidos=false;
            lbCalleFaltante.setText("*Campo obligatorio");
        }else if(!tfCiudadOrigen.getText().isEmpty() && !soloLetras(tfCiudadOrigen.getText())){
            camposValidos=false;
            lbCalleFaltante.setText("*Formato incorrecto");
        }
        //
        if(envio.getIdClienteDestino() == 0){
            camposValidos=false;
            lbClienteFaltante.setText("*Campo obligatorio");
        }
        //
        if(envio.getCodigoPostalOrigen()==0){
            camposValidos=false;
            lbCodigoPostalFaltante.setText("*Campo obligatorio");
        }else if(!tfCPOrigen.getText().isEmpty() && !esNumerico(tfCPOrigen.getText())){
            camposValidos=false;
            lbCodigoPostalFaltante.setText("*Formato incorrecto");
        }
        //
        if(envio.getColonia().isEmpty()){
            camposValidos=false;
            lbColoniaFaltante.setText("*Campo obligatorio");
        }else if(!tfColoniaOrigen.getText().isEmpty() && !soloLetras(tfColoniaOrigen.getText())){
            camposValidos=false;
            lbColoniaFaltante.setText("*Formato incorrecto");
        }
        //
        if(envio.getCiudadOrigen().isEmpty()){
            camposValidos=false;
            lbCiudadFaltante.setText("*Campo obligatorio");
        }else if(!tfCiudadOrigen.getText().isEmpty() && !soloLetras(tfCiudadOrigen.getText())){
            camposValidos=false;
            lbCiudadFaltante.setText("*Formato incorrecto");
        }
        //
        if(envio.getEstadoOrigen().isEmpty()){
            camposValidos=false;
            lbEstadoFaltante.setText("*Campo obligatorio");
        }else if(!tfEstadoOrigen.getText().isEmpty() && !soloLetras(tfEstadoOrigen.getText())){
            camposValidos=false;
            lbEstadoFaltante.setText("*Formato incorrecto");
        }
        //
        if(envio.getCosto()==0){
            camposValidos=false;
            lbCostoFaltante.setText("*Campo obligatorio");
        }else if(!tfCosto.getText().isEmpty() && !esDecimal(tfCosto.getText())){
            camposValidos=false;
            lbCostoFaltante.setText("*Formato incorrecto");
        }
        //
        if(envio.getNumeroDeGuia()== 0){
            camposValidos=false;
            lbNumeroGuiaFaltante.setText("*Campo obligatorio");
        }else if(!tfNumGuia.getText().isEmpty() && !esNumerico(tfNumGuia.getText())){
            camposValidos=false;
            lbNumeroGuiaFaltante.setText("*Formato incorrecto");
        }
        if(envio.getIdEstatus() == 0){
            camposValidos=false;
            lbEstatusFaltante.setText("*Campo obligatorio");
        }
        if(envio.getIdUnidad()== 0){
            camposValidos=false;
            lbUnidadFaltante.setText("*Campo obligatorio");
        }
        return camposValidos;
    }
    
    private void cerrarVentana(){
        Stage escenario = (Stage)tfNumGuia.getScene().getWindow();
        escenario.close();
        ( (Stage) tfNumGuia.getScene().getWindow()).close();
    }
    
}
