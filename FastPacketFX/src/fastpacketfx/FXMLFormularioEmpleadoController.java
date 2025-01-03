package fastpacketfx;

import fastpacketfx.modelo.dao.ColaboradorDAO;
import fastpacketfx.pojo.Colaborador;
import fastpacketfx.pojo.RolEmpleado;
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
    }    

    @FXML
    private void onClickAgregar(ActionEvent event) {
        String nombre = tfNombre.getText();
        String apellidoPaterno = tfApellidoPaterno.getText();
        String apellidoMaterno = tfApellidoMaterno.getText();
        String curp = tfCURP.getText();
        String licencia = tfLicencia.getText();
        String noPersonal = tfNoPersonal.getText();
        String password = pfPassword.getText();
        String correo = tfCorreo.getText();

        Colaborador colaborador = new Colaborador();
        colaborador.setNombre(nombre);
        colaborador.setApellidoMaterno(apellidoMaterno);
        colaborador.setApellidoPaterno(apellidoPaterno);
        colaborador.setCurp(curp);
        colaborador.setL(colonia);
        colaborador.setCodigoPostal(codigoPostal);
        colaborador.setNumeroCasa(numCasa);
        colaborador.setCorreo(correo);
        colaborador.setTelefono(telefono);
        
        if(sonCamposValidos(cliente)){
          guardarDatosCliente(cliente);
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
    private void cerrarVentana(){
        Stage escenario = (Stage)tfNombre.getScene().getWindow();
        escenario.close();
        ( (Stage) tfNombre.getScene().getWindow()).close();
    }
}
