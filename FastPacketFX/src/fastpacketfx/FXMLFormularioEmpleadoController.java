package fastpacketfx;

import fastpacketfx.modelo.dao.ClienteDAO;
import fastpacketfx.modelo.dao.ColaboradorDAO;
import fastpacketfx.pojo.Cliente;
import fastpacketfx.pojo.Colaborador;
import fastpacketfx.pojo.Mensaje;
import fastpacketfx.pojo.RolEmpleado;
import fastpacketfx.utilidades.Utilidades;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
    }
    
    private void cargarRolesEmpleados(){
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
        if(colaborador.getCorreo().isEmpty()){
            camposValidos=false;
            lbCorreo.setText("*Correo faltante");
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
}
