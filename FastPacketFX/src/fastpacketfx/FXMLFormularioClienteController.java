package fastpacketfx;

import fastpacketfx.interfaces.INotificadorOperacion;
import fastpacketfx.modelo.dao.ClienteDAO;
import fastpacketfx.pojo.Cliente;
import fastpacketfx.pojo.Mensaje;
import fastpacketfx.utilidades.Utilidades;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLFormularioClienteController implements Initializable {

    private INotificadorOperacion observador;
    private Cliente clienteEdicion;
    private boolean modoEdicion;
    
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCodigoPostal;
    @FXML
    private TextField tfColonia;
    @FXML
    private TextField tfCalle;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfNumCasa;
    @FXML
    private Label lbNombreFaltante;
    @FXML
    private Label lbApellidoPaternoFaltante;
    @FXML
    private Label lbApellidoMaternoFaltante;
    @FXML
    private Label lbCodigoPostalFaltante;
    @FXML
    private Label lbColoniaFaltante;
    @FXML
    private Label lbCalleFaltante;
    @FXML
    private Label lbTelefonoFaltante;
    @FXML
    private Label lbCorreoFaltante;
    @FXML
    private Label lbNumeroCasaFaltante;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfEstado;
    @FXML
    private Label lbCiudadFaltante;
    @FXML
    private Label lbEstadoFaltante;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void inicializarValores(INotificadorOperacion observador, Cliente clienteEdicion){
        this.observador = observador;
        this.clienteEdicion = clienteEdicion;
        if(clienteEdicion !=null){
            modoEdicion = true;
            cargarDatosEdicion();
        }
    }
    
    private void cargarDatosEdicion(){
        tfNombre.setText(this.clienteEdicion.getNombre());
        tfApellidoPaterno.setText(this.clienteEdicion.getApellidoPaterno());
        tfApellidoMaterno.setText(this.clienteEdicion.getApellidoMaterno());
        tfCiudad.setText(this.clienteEdicion.getCiudad());
        tfEstado.setText(this.clienteEdicion.getEstado());
        tfCodigoPostal.setText(this.clienteEdicion.getCodigoPostal().toString());
        tfCalle.setText(this.clienteEdicion.getCalle());
        tfNumCasa.setText(this.clienteEdicion.getNumeroCasa() != null ? this.clienteEdicion.getNumeroCasa().toString() : "");
        tfColonia.setText(this.clienteEdicion.getColonia());
        tfCorreo.setText(this.clienteEdicion.getCorreo());
        tfTelefono.setText(this.clienteEdicion.getTelefono());
        
    }

    @FXML
    private void onClickCancelar(ActionEvent event) {
        cerrarVentana();
    }

    @FXML
    private void onClickAgregar(ActionEvent event) {
    
        String nombre = tfNombre.getText();
        String apellidoPaterno = tfApellidoPaterno.getText();
        String apellidoMaterno = tfApellidoMaterno.getText();
        String cp = tfCodigoPostal.getText();
        Integer codigoPostal = (cp.isEmpty() || !esNumerico(cp)) ?  0 : Integer.valueOf(cp);
        String colonia = tfColonia.getText();
        String calle = tfCalle.getText();
        String telefono = tfTelefono.getText();
        String correo = tfCorreo.getText();
        String numeroCasa = tfNumCasa.getText();
        Integer numCasa = (numeroCasa.isEmpty() || !esNumerico(numeroCasa)) ? null : Integer.valueOf(numeroCasa);
        String ciudad = tfCiudad.getText();
        String estado = tfEstado.getText();
        
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellidoMaterno(apellidoMaterno);
        cliente.setApellidoPaterno(apellidoPaterno);
        cliente.setCalle(calle);
        cliente.setColonia(colonia);
        cliente.setCodigoPostal(codigoPostal);
        cliente.setNumeroCasa(numCasa);
        cliente.setCorreo(correo);
        cliente.setTelefono(telefono);
        cliente.setCiudad(ciudad);
        cliente.setEstado(estado);
        
        if(sonCamposValidos(cliente)){
            if(!modoEdicion){
                guardarDatosCliente(cliente);
            }else{
                cliente.setIdCliente(clienteEdicion.getIdCliente());
                editarDatosColaborador(cliente);
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error al guardar","Existen algunos campos vacios necesarios para guardar la información", Alert.AlertType.ERROR);
        }
    }
    
    private void guardarDatosCliente(Cliente cliente){
        Mensaje msj = ClienteDAO.registrarClientes(cliente);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Cliente registrado", "La información del cliente "+cliente.getNombre() + ", "+"se registro correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            observador.notificarOperacionExitosa("Guardar", cliente.getNombre());
        }else{
            Utilidades.mostrarAlertaSimple("Error al guardar",msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void editarDatosColaborador(Cliente cliente){
        Mensaje msj = ClienteDAO.editarCliente(cliente);
        System.out.println("Datos del colaborador: " + cliente);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Cliente editado","La información del cliente " +cliente.getNombre() +" se a modificado correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            observador.notificarOperacionExitosa("Editar", cliente.getNombre());
        }else{
            Utilidades.mostrarAlertaSimple("Error al editaar", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private boolean sonCamposValidos(Cliente cliente){
        boolean camposValidos=true;
        lbApellidoMaternoFaltante.setText(" ");
        lbApellidoPaternoFaltante.setText(" ");
        lbCalleFaltante.setText(" ");
        lbCodigoPostalFaltante.setText(" ");
        lbColoniaFaltante.setText(" ");
        lbCorreoFaltante.setText(" ");
        lbNombreFaltante.setText(" ");
        lbTelefonoFaltante.setText(" ");
        lbNumeroCasaFaltante.setText(" ");
        lbCiudadFaltante.setText(" ");
        lbEstadoFaltante.setText(" ");
        
        //validacion del nombre 
        if(cliente.getNombre().isEmpty()){
          camposValidos=false;
          lbNombreFaltante.setText("*Nombre faltante");
        }else if(!cliente.getNombre().isEmpty() &&!validarNombre(cliente.getNombre())){
            camposValidos=false;
            lbNombreFaltante.setText("*Contiene caracteres extraños");
        }
        //Validacion de apellido paterno
        if(cliente.getApellidoPaterno().isEmpty()){
            camposValidos=false;
            lbApellidoPaternoFaltante.setText("*Apellido faltante");
        }else if(!cliente.getApellidoPaterno().isEmpty() &&!validarNombre(cliente.getApellidoPaterno())){
            camposValidos=false;
            lbApellidoPaternoFaltante.setText("*Contiene caracteres extraños");
        }
        //Validacion de apellido materno
        if(cliente.getApellidoMaterno().isEmpty()){
            camposValidos=false;
            lbApellidoMaternoFaltante.setText("*Apellido faltante");
        }else if(!cliente.getApellidoMaterno().isEmpty() &&!validarNombre(cliente.getApellidoMaterno())){
            camposValidos=false;
            lbApellidoMaternoFaltante.setText("*Contiene caracteres extraños");
        }
        //Validacion de la direccion
        if(cliente.getCalle().isEmpty()){
            camposValidos=false;
            lbCalleFaltante.setText("*Calle obligatoria");
        }else if(!cliente.getCalle().isEmpty() &&!validarTexto(cliente.getCalle())){
            camposValidos=false;
            lbCalleFaltante.setText("*Contiene caracteres extraños");
        }
        //Validacion de la colonia
        if(cliente.getColonia().isEmpty()){
            camposValidos=false;
            lbColoniaFaltante.setText("*Colonia obligatoria");
        }else if(!cliente.getColonia().isEmpty() &&!validarTexto(cliente.getColonia())){
            camposValidos=false;
            lbColoniaFaltante.setText("*Contiene caracteres extraños");
        }
        //Validacion del codigo postal
        if(!tfCodigoPostal.getText().isEmpty() && !tfCodigoPostal.getText().matches("\\d+")){
            camposValidos=false;
            lbCodigoPostalFaltante.setText("*Formato Incorrecto");
        }else if(cliente.getCodigoPostal()==0){
            camposValidos=false;
            lbCodigoPostalFaltante.setText("*Código Postal necesario");
        }
        //validacion del numero de casa
        if (!tfNumCasa.getText().isEmpty() && !tfNumCasa.getText().matches("\\d")) {
            camposValidos = false;
            lbNumeroCasaFaltante.setText("*Número de casa inválido");
        }
        //Validacion de telefono
        if(cliente.getTelefono().isEmpty()){
            camposValidos=false;
            lbTelefonoFaltante.setText("*Telefono obligatorio");
        }
        //Validacion de la ciudad
        if(cliente.getCiudad().isEmpty()){
            camposValidos=false;
            lbCiudadFaltante.setText("*Ciudad obligatoria");
        }else if(!cliente.getCiudad().isEmpty() &&!validarTexto(cliente.getCiudad())){
            camposValidos=false;
            lbCiudadFaltante.setText("*Contiene caracteres extraños");
        }
        //validacion del estado
        if(cliente.getEstado().isEmpty()){
            camposValidos=false;
            lbEstadoFaltante.setText("*Estado obligatorio");
        }else if(!cliente.getEstado().isEmpty() &&!validarTexto(cliente.getEstado())){
            camposValidos=false;
            lbEstadoFaltante.setText("*Contiene caracteres extraños");
        }
        //validacion del Correo
        if(cliente.getCorreo().isEmpty()){
            camposValidos=false;
            lbCorreoFaltante.setText("*Correo necesario");
        }else if(!cliente.getCorreo().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
            camposValidos=false;
            lbCorreoFaltante.setText("*Formato incorrecto");
        }
        return camposValidos;
    }
    
    private boolean esNumerico(String cadena) {
        return cadena.matches("\\d+"); // Verifica que la cadena contenga solo dígitos
    }
    
    public boolean validarTexto(String texto) {
        // La expresión regular permite letras, espacios y acentos.
        String valido = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\.\\s]+$";
        return texto.matches(valido);
    }
    
    public boolean validarNombre(String texto) {
        // La expresión regular permite letras, espacios y acentos.
        String valido = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+$";
        return texto.matches(valido);
    }
    
     private void cerrarVentana(){
        Stage escenario = (Stage)tfNombre.getScene().getWindow();
        escenario.close();
        ( (Stage) tfNombre.getScene().getWindow()).close();
    }
    
}
