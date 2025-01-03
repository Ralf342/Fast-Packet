package fastpacketfx;

import fastpacketfx.modelo.dao.ColaboradorDAO;
import fastpacketfx.modelo.dao.UnidadDAO;
import fastpacketfx.pojo.Colaborador;
import fastpacketfx.pojo.RolEmpleado;
import fastpacketfx.pojo.TipoUnidad;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLFormularioUnidadController implements Initializable {

    private ObservableList<TipoUnidad> tipos;
    private ObservableList<Colaborador> conductor;
    @FXML
    private TextField tfMarca;
    @FXML
    private TextField tfModelo;
    @FXML
    private TextField tfAnio;
    @FXML
    private TextField tfVIN;
    @FXML
    private ComboBox<TipoUnidad> cbTipoUnidad;
    @FXML
    private TextField tfNII;
    @FXML
    private ComboBox<Colaborador> cbConductor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTipoUnidad();
        cargarConductor();
    }    

    @FXML
    private void onClickAgregar(ActionEvent event) {
    }

    @FXML
    private void onClickCancelar(ActionEvent event) {
        cerrarVentana();
    }
    
    private void cerrarVentana(){
        Stage escenario = (Stage)tfAnio.getScene().getWindow();
        escenario.close();
        ( (Stage) tfAnio.getScene().getWindow()).close();
    }
    
    private void cargarTipoUnidad(){
        tipos = FXCollections.observableArrayList();
        List<TipoUnidad> listaWS = UnidadDAO.obtenerTiposUnidades();
        if(listaWS !=null){
            tipos.addAll(listaWS);
            cbTipoUnidad.setItems(tipos);
        }
    }
    
    private void cargarConductor(){
        conductor = FXCollections.observableArrayList();
        List<Colaborador> listaWS = ColaboradorDAO.obtenerConductores();
        if(listaWS !=null){
            conductor.addAll(listaWS);
            cbConductor.setItems(conductor);
        }
    }
}
