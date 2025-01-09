package fastpacketfx;

import fastpacketfx.interfaces.INotificadorOperacion;
import fastpacketfx.modelo.dao.EnvioDAO;
import fastpacketfx.modelo.dao.PaqueteDAO;
import fastpacketfx.pojo.Envio;
import fastpacketfx.pojo.Mensaje;
import fastpacketfx.pojo.Paquete;
import fastpacketfx.utilidades.Utilidades;
import java.io.IOException;
import static java.lang.Compiler.disable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joska_
 */
public class FXMLFormularioPaqueteController implements Initializable {
    
    private INotificadorOperacion observador;
    private Paquete paqueteEdicion;
    private boolean modoEdicion;

    private ObservableList<Envio> envios;
    @FXML
    private TextArea ta_Descripcion;
    @FXML
    private TextField tf_alto;
    @FXML
    private TextField tf_ancho;
    @FXML
    private TextField tf_profundidad;
    @FXML
    private TextField tf_peso;
    @FXML
    private Label lbDescripcionFaltante;
    @FXML
    private Label lbAlturaFaltante;
    @FXML
    private Label lbAnchoFaltante;
    @FXML
    private Label lbProfundidadFaltante;
    @FXML
    private Label lbPesoFaltante;
    @FXML
    private Label lbNumeroGuiaFaltante;
    @FXML
    private ComboBox<Envio> cbNumeroGuia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarNumeroGuia();
    }    
    
    public void inicializarValores(INotificadorOperacion observador, Paquete paqueteEdicion){
        this.observador = observador;
        this.paqueteEdicion = paqueteEdicion;
        if(paqueteEdicion !=null){
            modoEdicion = true;
            cargarDatosEdicion();
        }
    }
    
    private void cargarDatosEdicion(){
        ta_Descripcion.setText(this.paqueteEdicion.getDescripcion());
        tf_alto.setText(Float.toString(this.paqueteEdicion.getAlto()));
        tf_ancho.setText(Float.toString(this.paqueteEdicion.getAncho()));
        tf_profundidad.setText(Float.toString(this.paqueteEdicion.getProfundidad()));
        tf_peso.setText(Float.toString(this.paqueteEdicion.getPeso()));
        int numeroGuia = obtenerNumeroGuia(this.paqueteEdicion.getNumeroDeGuia());
        cbNumeroGuia.getSelectionModel().select(numeroGuia);
        
        cbNumeroGuia.setDisable(true);
        
    }

    @FXML
    private void onClickAgregar(ActionEvent event) {
        
        String descripcion = ta_Descripcion.getText();
        String altoString = tf_alto.getText();
        Float alto = (altoString.isEmpty() || !esNumerico(altoString)) ?  0 : Float.valueOf(altoString);
        String anchoString = tf_ancho.getText();
        Float ancho = (anchoString.isEmpty() || !esNumerico(anchoString)) ?  0 : Float.valueOf(anchoString);
        String profundidadString = tf_profundidad.getText();
        Float profundidad = (profundidadString.isEmpty() || !esNumerico(profundidadString)) ?  0 : Float.valueOf(profundidadString);
        String pesoString = tf_peso.getText();
        Float peso = (pesoString.isEmpty() || !esNumerico(pesoString)) ?  0 : Float.valueOf(pesoString);
        int numeroGuia =(cbNumeroGuia.getSelectionModel().getSelectedItem() !=null)
               ? cbNumeroGuia.getSelectionModel().getSelectedItem().getNumeroDeGuia(): 0;
        
        Paquete paquete = new Paquete();
        paquete.setAlto(alto);
        paquete.setDescripcion(descripcion);
        paquete.setAncho(ancho);
        paquete.setProfundidad(profundidad);
        paquete.setPeso(peso);
        paquete.setNumeroDeGuia(numeroGuia);
        
        if(camposValidos(paquete)){
            if(!modoEdicion){
                guardarDatosPaquetes(paquete);
            }else{
                paquete.setIdPaquete(paqueteEdicion.getIdPaquete());
                editarDatosPaquete(paquete);
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error al guardar","Existen algunos campos vacios necesarios para guardar la información", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onClickCancelar(ActionEvent event) {
        cerrarVentana();
    }
    
    private void guardarDatosPaquetes(Paquete paquete){
        Mensaje msj = PaqueteDAO.registrarPaquete(paquete);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Paquete registrado registrado", "El paquete se registro correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            observador.notificarOperacionExitosa("Guardar", paquete.getDescripcion());
        }else{
            Utilidades.mostrarAlertaSimple("Error al guardar",msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void editarDatosPaquete(Paquete paquete){
        Mensaje msj = PaqueteDAO.editarPaquete(paquete);
        System.out.println("Datos del colaborador: " + paquete);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Paquete editado","La información del paquete " +paquete.getDescripcion()+" se a modificado correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            observador.notificarOperacionExitosa("Editar", paquete.getDescripcion());
        }else{
            Utilidades.mostrarAlertaSimple("Error al editar", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void cargarNumeroGuia(){
        envios = FXCollections.observableArrayList();
        List<Envio> listaWS = EnvioDAO.obtenerNumeroGuia();
        if(listaWS !=null){
            envios.addAll(listaWS);
            cbNumeroGuia.setItems(envios);
        }
    }
    
    private boolean esNumerico(String cadena) {
        return cadena.matches("\\d+(\\.\\d+)?");
    }   
    
    private void cerrarVentana(){
        Stage escenario = (Stage)ta_Descripcion.getScene().getWindow();
        escenario.close();
        ( (Stage) ta_Descripcion.getScene().getWindow()).close();
    }
    
    private boolean camposValidos(Paquete paquete){
        boolean valido=true;
        lbDescripcionFaltante.setText(" ");
        lbAlturaFaltante.setText(" ");
        lbAnchoFaltante.setText(" ");
        lbNumeroGuiaFaltante.setText(" ");
        lbPesoFaltante.setText(" ");
        lbProfundidadFaltante.setText(" ");
        
        //validacion alto
        if(!tf_alto.getText().isEmpty() && !tf_alto.getText().matches("\\d+(\\.\\d+)?")) {
            valido = false;
            lbAlturaFaltante.setText("*Valor invalido");
        }else if(paquete.getAlto()==0){
            valido=false;
            lbAlturaFaltante.setText("*Valor faltante");
        }
        //validacion Ancho
        if(!tf_ancho.getText().isEmpty() && !tf_ancho.getText().matches("\\d+(\\.\\d+)?")) {
            valido = false;
            lbAnchoFaltante.setText("*Valor invalido");
        }else if(paquete.getAncho()==0){
            valido=false;
            lbAnchoFaltante.setText("*Valor faltante");
        }
        //validacion peso
        if(!tf_peso.getText().isEmpty() && !tf_peso.getText().matches("\\d+(\\.\\d+)?")) {
            valido = false;
            lbPesoFaltante.setText("*Valor invalido");
        }else if(paquete.getPeso()==0){
            valido=false;
            lbPesoFaltante.setText("*Valor faltante");
        }
        //validacion de profundidad
        if(!tf_profundidad.getText().isEmpty() && !tf_profundidad.getText().matches("\\d+(\\.\\d+)?")) {
            valido = false;
            lbProfundidadFaltante.setText("*Valor invalido");
        }else if(paquete.getProfundidad()==0){
            valido=false;
            lbProfundidadFaltante.setText("*Valor faltante");
        }
        //validacion descripcion
        if(paquete.getDescripcion().isEmpty()){
            valido=false;
            lbDescripcionFaltante.setText("*Valor faltante");
        }
        if(paquete.getNumeroDeGuia()==0){
            valido=false;
            lbNumeroGuiaFaltante.setText("*Valor faltante");
        }
        return valido;
    }
    
        private int obtenerNumeroGuia(Integer numeGuia){
        for (int i = 0; i < envios.size(); i++) {
            if(numeGuia == envios.get(i).getNumeroDeGuia()){
                return i;
            }
        }
        return 0;
    }
    
}
