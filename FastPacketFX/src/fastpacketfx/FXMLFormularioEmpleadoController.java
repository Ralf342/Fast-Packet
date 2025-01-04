package fastpacketfx;

import fastpacketfx.modelo.ConexionWS;
import fastpacketfx.modelo.dao.ClienteDAO;
import fastpacketfx.modelo.dao.ColaboradorDAO;
import fastpacketfx.pojo.Cliente;
import fastpacketfx.pojo.Colaborador;
import fastpacketfx.pojo.Mensaje;
import fastpacketfx.pojo.RespuestaHTTP;
import fastpacketfx.pojo.RolEmpleado;
import fastpacketfx.utilidades.Utilidades;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLFormularioEmpleadoController implements Initializable {

    private ObservableList<RolEmpleado> roles;
    @FXML
    private ComboBox<RolEmpleado> cbRol;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCURP;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfLicencia;
    @FXML
    private TextField tfNoPersonal;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Button btnFoto;
    @FXML
    private ImageView ivLicencia;
    @FXML
    private Label lbRolFaltante;
    @FXML
    private Label lbNombreFaltante;
    @FXML
    private Label lbApellidoPaternoFaltante;
    @FXML
    private Label lbApellidoMaternoFaltante;
    @FXML
    private Label lbNumLicencia;
    @FXML
    private Label lbCorreo;
    @FXML
    private Label lbCURPFaltante;
    @FXML
    private Label lbNumPersonalFaltante;
    @FXML
    private Label lbContraseniaFaltante;
    private Integer idColaborador;
    private String fotoBase64;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarRolesEmpleados();
        configurarCambioDeRol();
    }    

    @FXML
    private void onClickAgregar(ActionEvent event) {
        String nombre = tfNombre.getText();
        String apellidoPaterno = tfApellidoPaterno.getText();
        String apellidoMaterno = tfApellidoMaterno.getText();
        String curp = tfCURP.getText();
        String licencia = tfLicencia.getText().isEmpty() ? null: tfLicencia.getText();
        String numPer = tfNoPersonal.getText();
        Integer numPersonal = (numPer.isEmpty() || !esNumerico(numPer)) ?  0 : Integer.valueOf(numPer);
        String password = pfPassword.getText();
        String correo = tfCorreo.getText();
        int idRol =(cbRol.getSelectionModel().getSelectedItem() !=null)
                ? cbRol.getSelectionModel().getSelectedItem().getIdRol(): 0;

        Colaborador colaborador = new Colaborador();
        colaborador.setNombre(nombre);
        colaborador.setApellidoMaterno(apellidoMaterno);
        colaborador.setApellidoPaterno(apellidoPaterno);
        colaborador.setCurp(curp);
        colaborador.setNumLicencia(licencia);
        colaborador.setNoPersonal(numPersonal);
        colaborador.setContrasenia(password);
        colaborador.setCorreo(correo);
        colaborador.setIdRol(idRol);
        
        if(sonCamposValidos(colaborador)){
            try{
                guardarDatosColaborador(colaborador);
            }catch(Exception e){
                System.out.println(e);   
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error al guardar","Existen algunos campos vacios necesarios para guardar la información", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onClickCancelar(ActionEvent event) {
        cerrarVentana();
    }

    @FXML
    private void onClickSubir(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagenes", "*.png", "*.jpg", "*.jpeg"));
        File archivoSeleccionado = fileChooser.showOpenDialog(null);
        
        if(archivoSeleccionado != null){
            try{
                byte[] imageBytes = Files.readAllBytes(archivoSeleccionado.toPath());
                String fotoBase64 = Base64.getEncoder().encodeToString(imageBytes);

                Image imagen = new Image(new ByteArrayInputStream(imageBytes));
                ivLicencia.setImage(imagen);  
                //envia la foto al server
                String url = "http://localhost:8084/WSFastPacket/api/colaborador/subirFoto/" + idColaborador;
                RespuestaHTTP respuesta = ConexionWS.peticionPUTImg(url, imageBytes);  // Usar los bytes para la petición PUT

                if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
                System.out.println("Foto guardada exitosamente");
            } else {
                System.out.println("Error al guardar la foto: " + respuesta.getContenido());
            } 
                
            // Aqui se llama a l método para subir la foto al servidor
            ColaboradorDAO.subirFotoColaborador(idColaborador, fotoBase64); 
            
            }catch(IOException e){
                e.printStackTrace();
                Utilidades.mostrarAlertaSimple("Error al cargar imagen", "Hubo un problema al cargar la imagen", Alert.AlertType.ERROR);
            }
           
        }else{
            Utilidades.mostrarAlertaSimple("Sin imagen", "No se seleccionó ninguna imagen", Alert.AlertType.WARNING);
        }
       // cargarFoto(idColaborador);
    }
    
    private void cargarRolesEmpleados() {
        roles = FXCollections.observableArrayList();
        List<RolEmpleado> listaWS = ColaboradorDAO.obtenerRolesColaborador();
        if(listaWS !=null){
            roles.addAll(listaWS);
            cbRol.setItems(roles);
        }
    }
    
    private void guardarDatosColaborador(Colaborador colaborador){
        Mensaje msj = ColaboradorDAO.registrarColaborador(colaborador);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Colaborador registrado", "La información del colaborador "+colaborador.getNombre() + ", "+"se registro correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            //observador.notificarOperacionExitosa("Guardar", cliente.getNombre());
        }else{
            Utilidades.mostrarAlertaSimple("Error al guardar",msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void cerrarVentana(){
        Stage escenario = (Stage)tfNombre.getScene().getWindow();
        escenario.close();
        ( (Stage) tfNombre.getScene().getWindow()).close();
    }
    
    private boolean esNumerico(String cadena) {
        return cadena.matches("\\d+"); // Verifica que la cadena contenga solo dígitos
    }
    
    private void configurarCambioDeRol() {
    cbRol.valueProperty().addListener((observable, valorAntiguo, valorNuevo) -> {
        if (valorNuevo != null) {
            // Si el rol es de conductor (ID == 3), habilita el campo de licencia, si no, desactívalo
            tfLicencia.setDisable(valorNuevo.getIdRol() != 3);
            if (valorNuevo.getIdRol() != 3) {
                tfLicencia.clear(); // Limpia el campo si no se requiere
            }
        }else {
            tfLicencia.setDisable(true); // Por defecto, desactiva si no hay selección
            }
        });
    }
    
    private boolean sonCamposValidos(Colaborador colaborador){
        boolean camposValidos = true;
        lbApellidoMaternoFaltante.setText(" ");
        lbApellidoPaternoFaltante.setText(" ");
        lbNombreFaltante.setText(" ");
        lbCURPFaltante.setText(" ");
        lbContraseniaFaltante.setText(" ");
        lbCorreo.setText(" ");
        lbNumLicencia.setText(" ");
        lbNumPersonalFaltante.setText(" ");
        lbRolFaltante.setText(" ");
        
        //Validacion de nombre completo
        if(colaborador.getNombre().isEmpty()){
            camposValidos=false;
            lbNombreFaltante.setText("*Nombre faltante");
        }
        if(colaborador.getApellidoMaterno().isEmpty()){
            camposValidos=false;
            lbApellidoMaternoFaltante.setText("*Apellido Materno faltante");
        }
        if(colaborador.getApellidoPaterno().isEmpty()){
            camposValidos=false;
            lbApellidoPaternoFaltante.setText("*Apellido Paterno faltante");
        }
        //Validacion del curp
        if(colaborador.getCurp().isEmpty()){
            camposValidos=false;
            lbCURPFaltante.setText("*CURP Necesaria");
        }
        //Validacion de Contraseña
        if(colaborador.getContrasenia().isEmpty()){
            camposValidos=false;
            lbContraseniaFaltante.setText("*Contraseña faltante");
        }
        //Validacion de rol
        if(colaborador.getIdRol() == 0){
            camposValidos=false;
            lbRolFaltante.setText("*Rol necesario");
        }
        //Validacion del numero de personal
        if(colaborador.getNoPersonal() == 0){
            camposValidos=false;
            lbNumPersonalFaltante.setText("*Numero de personal faltante");
        }else if(!tfNoPersonal.getText().isEmpty() && !tfNoPersonal.getText().matches("\\d+")){
            camposValidos=false;
            lbNumPersonalFaltante.setText("*Formato Incorrecto");
        }
        //Validacion Correo
        if(colaborador.getCorreo().isEmpty()){
            camposValidos=false;
            lbCorreo.setText("*Correo necesario");
        }else if(!colaborador.getCorreo().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")){
            camposValidos=false;
            lbCorreo.setText("*Formato incorrecto");
        }
        //Validacion del numero de licencia en caso de conductores
        if (colaborador.getIdRol() == 3) {
            if (colaborador.getNumLicencia() == null || colaborador.getNumLicencia().isEmpty()) {
                camposValidos = false;
                lbNumLicencia.setText("*Para conductores se requiere el número de licencia de manejo");
            } else {
                lbNumLicencia.setText(""); // Limpia el error si el número de licencia es válido
            }
        } else {
            lbNumLicencia.setText(""); // Limpia el error si el rol no es de conductor
        }
        return camposValidos;
    }

    //para a foto
    private void cargarFoto(Integer idColaborador) {
        String fotoBase = ColaboradorDAO.subirFotoColaborador(idColaborador, fotoBase64);
        if(fotoBase != null && !fotoBase.isEmpty()){
            Image imagen = decodificarAFotoBase64(fotoBase);
            
            if (imagen != null) {
            ivLicencia.setImage(imagen);
            } else {
                Utilidades.mostrarAlertaSimple("Error de Decodificación", "No se pudo decodificar la imagen correctamente.", Alert.AlertType.ERROR);
            }

        }else{
            Utilidades.mostrarAlertaSimple("Sin foto", "Cargue una imagen", Alert.AlertType.WARNING);
        }
    }
    
    
    private Image decodificarAFotoBase64(String fotoBase64){
        try{
            //para eliminar los espacios en blanco si hay en la cadena base64
            String base64decodificada = fotoBase64.replaceAll("\\s", "");
            byte[] imageBytes = Base64.getDecoder().decode(base64decodificada);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);
            return new Image(byteArrayInputStream); // crea la imagen
        }catch(IllegalArgumentException e){
            e.printStackTrace();
            System.out.println("Error al decodificar la imagen" +e.getMessage());
            return null;
        }
        
    }
    

    
}
