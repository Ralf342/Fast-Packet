package fastpacketfx;

import fastpacketfx.interfaces.INotificadorOperacion;
import fastpacketfx.modelo.dao.ClienteDAO;
import fastpacketfx.modelo.dao.EnvioDAO;
import fastpacketfx.modelo.dao.UnidadDAO;
import fastpacketfx.pojo.Cliente;
import fastpacketfx.pojo.Envio;
import fastpacketfx.pojo.Estatus;
import fastpacketfx.pojo.ListaClientes;
import fastpacketfx.pojo.Login;
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
    
    private INotificadorOperacion observador;
    private Envio envioEdicion;
    private Login login;
    private boolean modoEdicion;

    private ObservableList<Estatus> estatus;
    private ObservableList<ListaClientes> cliente;
    private ObservableList<Unidad> unidad;
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
    private ComboBox<ListaClientes> cbCliente;
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
        cargarCliente();
        cargarUnidades();
        configurarComboBoxCliente();
    }

    public void inicializarValores(INotificadorOperacion observador, Envio envioEdicion, Login login){
        this.observador = observador;
        this.envioEdicion = envioEdicion;
        this.login = login;
        System.out.println(login.getColaborador().getIdColaborador());
        if(envioEdicion !=null){
            modoEdicion = true;
            cargarDatosEdicion();
        }
    }
    
    private void cargarDatosEdicion(){
        tfCalleOrigen.setText(this.envioEdicion.getCalleOrigen());
        tfColoniaOrigen.setText(this.envioEdicion.getColoniaOrigen());
        tfCPOrigen.setText(this.envioEdicion.getCodigoPostalOrigen().toString());
        tfCiudadOrigen.setText(this.envioEdicion.getCiudadOrigen());
        tfEstadoOrigen.setText(this.envioEdicion.getEstadoOrigen());
        tfCosto.setText(this.envioEdicion.getCosto().toString());
        int idCliente = obtenerIdCliente(this.envioEdicion.getIdClienteDestino());
        cbCliente.getSelectionModel().select(idCliente);
        tfNumGuia.setText(this.envioEdicion.getNumeroDeGuia().toString());
        int idEstatus = obtenerEstatus(this.envioEdicion.getIdEstatus());
        cbEstatus.getSelectionModel().select(idEstatus);
        int idUnidad = obtenerUnidad(this.envioEdicion.getIdUnidad());
        cbUnidad.getSelectionModel().select(idUnidad);
        
        tfNumGuia.setDisable(true);
        
    }

    @FXML
    private void onClickAgregar(ActionEvent event) {
        String calleOrigen = tfCalleOrigen.getText();
        String coloniaOrigen = tfColoniaOrigen.getText();
        String codigoPostalOrigen = tfCPOrigen.getText();
        Integer codigoPostal = (codigoPostalOrigen.isEmpty() || !esNumerico(codigoPostalOrigen)) ? 0: Integer.valueOf(codigoPostalOrigen);
        String ciudadOrigen = tfCiudadOrigen.getText();
        String estadoOrigen = tfEstadoOrigen.getText();
        String numGuia = tfNumGuia.getText();
        Integer numeroDeGuia = (numGuia.isEmpty() || !esNumerico(numGuia)) ?  0 : Integer.valueOf(numGuia);
        String costoString = tfCosto.getText();
        Float costo = (costoString.isEmpty() || !esDecimal(costoString)) ?  0 : Float.valueOf(costoString);
        int idCliente =(cbCliente.getSelectionModel().getSelectedItem() !=null)
               ? cbCliente.getSelectionModel().getSelectedItem().getIdCliente(): 0;
        int idestatus =(cbEstatus.getSelectionModel().getSelectedItem() !=null)
               ? cbEstatus.getSelectionModel().getSelectedItem().getIdEstatus(): 0;
        int idUnidad = (cbUnidad.getSelectionModel().getSelectedItem() !=null)
               ? cbUnidad.getSelectionModel().getSelectedItem().getIdUnidad(): 0;
        
        Envio envio = new Envio();
        envio.setCalleOrigen(calleOrigen);
        envio.setColoniaOrigen(coloniaOrigen);
        envio.setCodigoPostalOrigen(codigoPostal);
        envio.setCiudadOrigen(ciudadOrigen);
        envio.setEstadoOrigen(estadoOrigen);
        envio.setCosto(costo);
        envio.setNumeroDeGuia(numeroDeGuia);
        envio.setIdClienteDestino(idCliente);
        envio.setIdEstatus(idestatus);
        envio.setIdUnidad(idUnidad);
        envio.setIdColaboradorModificacion(login.getColaborador().getIdColaborador());
        
        if(sonCamposValidos(envio)){
            if(!modoEdicion){
                guardarDatosEnvio(envio);
            }else{
                envio.setNumeroDeGuia(envioEdicion.getNumeroDeGuia());
                editarDatosEnvio(envio);
            }
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
            observador.notificarOperacionExitosa("Guardar", envio.getNumeroDeGuia().toString());
        }else{
            System.out.println(msj.getMensaje());
            Utilidades.mostrarAlertaSimple("Error al guardar",msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void editarDatosEnvio(Envio envio){
        Mensaje msj = EnvioDAO.editarEnvio(envio);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Envio editado","La información del envio " +envio.getNumeroDeGuia()+" se a modificado correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            observador.notificarOperacionExitosa("Editar", envio.getNumeroDeGuia().toString());
        }else{
            Utilidades.mostrarAlertaSimple("Error al editar", msj.getMensaje(), Alert.AlertType.ERROR);
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
    
    private void cargarCliente(){
        cliente = FXCollections.observableArrayList();
        List<ListaClientes> listaWS = ClienteDAO.obtenerListaClientes();
        if(listaWS !=null){
            cliente.addAll(listaWS);
            cbCliente.setItems(cliente);
        }
    }
    
    private void cargarUnidades(){
        unidad = FXCollections.observableArrayList();
        List<Unidad> listaWS = UnidadDAO.listarUnidades();
        if(listaWS !=null){
            unidad.addAll(listaWS);
            cbUnidad.setItems(unidad);
        }
    }
    
    public boolean validarNombre(String texto) {
        // La expresión regular permite letras, espacios y acentos.
        String valido = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+$";
        return texto.matches(valido);
    }
    public boolean validarTexto(String texto) {
        // La expresión regular permite letras, espacios y acentos.
        String valido = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\.\\s]+$";
        return texto.matches(valido);
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
        
        //validacion de la cale
        if(envio.getCalleOrigen().isEmpty()){
            camposValidos=false;
            lbCalleFaltante.setText("*Campo obligatorio");
        }else if(!tfCalleOrigen.getText().isEmpty() && !validarNombre(tfCalleOrigen.getText())){
            camposValidos=false;
            lbCalleFaltante.setText("*Formato incorrecto");
        }
        //
        if(envio.getIdClienteDestino() == 0){
            camposValidos=false;
            lbClienteFaltante.setText("*Campo obligatorio");
        }
        //
        if(!tfCPOrigen.getText().isEmpty() && !esNumerico(tfCPOrigen.getText())){
            camposValidos=false;
            lbCodigoPostalFaltante.setText("*Formato incorrecto");
        }else if(envio.getCodigoPostalOrigen()==0){
            camposValidos=false;
            lbCodigoPostalFaltante.setText("*Campo obligatorio");
        }
        // validacion de la colonia
        if(envio.getColoniaOrigen().isEmpty()){
            camposValidos=false;
            lbColoniaFaltante.setText("*Campo obligatorio");
        }else if(!tfColoniaOrigen.getText().isEmpty() && !validarTexto(tfColoniaOrigen.getText())){
            camposValidos=false;
            lbColoniaFaltante.setText("*Formato incorrecto");
        }
        //validar ciudad
        if(envio.getCiudadOrigen().isEmpty()){
            camposValidos=false;
            lbCiudadFaltante.setText("*Campo obligatorio");
        }else if(!tfCiudadOrigen.getText().isEmpty() && !validarNombre(tfCiudadOrigen.getText())){
            camposValidos=false;
            lbCiudadFaltante.setText("*Formato incorrecto");
        } 
        //validar estado
        if(envio.getEstadoOrigen().isEmpty()){
            camposValidos=false;
            lbEstadoFaltante.setText("*Campo obligatorio");
        }else if(!tfEstadoOrigen.getText().isEmpty() && !validarNombre(tfEstadoOrigen.getText())){
            camposValidos=false;
            lbEstadoFaltante.setText("*Formato incorrecto");
        }
        //validacion costo
        if(!tfCosto.getText().isEmpty() && !esDecimal(tfCosto.getText())){
            camposValidos=false;
            lbCostoFaltante.setText("*Formato incorrecto");
        }else if(envio.getCosto()==0){
            camposValidos=false;
            lbCostoFaltante.setText("*Campo obligatorio");
        }
        //validacion numero de guia
        if(!tfNumGuia.getText().isEmpty() && !esNumerico(tfNumGuia.getText())){
            camposValidos=false;
            lbNumeroGuiaFaltante.setText("*Formato incorrecto");
        }else if(envio.getNumeroDeGuia()== 0){
            camposValidos=false;
            lbNumeroGuiaFaltante.setText("*Campo obligatorio");
        }
        //validacion del estatus
        if(envio.getIdEstatus() == 0){
            camposValidos=false;
            lbEstatusFaltante.setText("*Campo obligatorio");
        }
        //validacion de unidad
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
    
    private void configurarComboBoxCliente() {
        // Listener para cambios en el ComboBox de cliente
        cbCliente.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                llenarCamposDestinoConCliente(newValue);
            }
        });
    }

    private void llenarCamposDestinoConCliente(ListaClientes cliente) {
        // Llena los campos destino con la información del cliente seleccionado
        tfCalleDestino.setText(cliente.getCalle());
        tfColoniaDestino.setText(cliente.getColonia());
        tfCPDestino.setText(String.valueOf(cliente.getCodigoPostal()));
        tfCiudadDestino.setText(cliente.getCiudad());
        tfEstadoDestino.setText(cliente.getEstado());
    }
    
    private int obtenerIdCliente(Integer idCliente){
        for (int i = 0; i < cliente.size(); i++) {
            if(idCliente == cliente.get(i).getIdCliente()){
                return i;
            }
        }
        return 0;
    }
    
    private int obtenerEstatus(Integer idEstatus){
        for (int i = 0; i < estatus.size(); i++) {
            if(idEstatus == estatus.get(i).getIdEstatus()){
                return i;
            }
        }
        return 0;
    }
    
    private int obtenerUnidad(Integer idUnidad){
        for (int i = 0; i < unidad.size(); i++) {
            if(idUnidad == unidad.get(i).getIdTipoUnidad()){
                return i;
            }
        }
        return 0;
    }
    
}
