package fastpacketfx;

import fastpacketfx.modelo.dao.ColaboradorDAO;
import fastpacketfx.modelo.dao.UnidadDAO;
import fastpacketfx.pojo.Colaborador;
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
import javafx.scene.control.Label;
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
    @FXML
    private Label lbMarcaFaltante;
    @FXML
    private Label lbModeloFaltante;
    @FXML
    private Label lbAnioFaltante;
    @FXML
    private Label lbNIIFaltante;
    @FXML
    private Label lbTipoUnidadFaltante;
    @FXML
    private Label lbVINFaltante;
    @FXML
    private Label lbConductorFaltante;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTipoUnidad();
        cargarConductor();
    }    

    @FXML
    private void onClickAgregar(ActionEvent event) {
        String marca = tfMarca.getText();
        String modelo = tfModelo.getText();
        String anio = tfAnio.getText();
        String vin = tfVIN.getText();
        int tipoUnidad =(cbTipoUnidad.getSelectionModel().getSelectedItem() !=null)
               ? cbTipoUnidad.getSelectionModel().getSelectedItem().getIdTipoUnidad(): 0;
        int conductor =(cbConductor.getSelectionModel().getSelectedItem() !=null)
               ? cbConductor.getSelectionModel().getSelectedItem().getIdColaborador(): 0;
        
        System.err.println(tipoUnidad + " " + conductor);
        
    }

    @FXML
    private void onClickCancelar(ActionEvent event) {
        cerrarVentana();
    }
    
    //Para cerrar la ventana
    private void cerrarVentana(){
        Stage escenario = (Stage)tfAnio.getScene().getWindow();
        escenario.close();
        ( (Stage) tfAnio.getScene().getWindow()).close();
    }
    
    //Carga los tipos de unidad que existen
    private void cargarTipoUnidad(){
        tipos = FXCollections.observableArrayList();
        List<TipoUnidad> listaWS = UnidadDAO.obtenerTiposUnidades();
        if(listaWS !=null){
            tipos.addAll(listaWS);
            cbTipoUnidad.setItems(tipos);
        }
    }
    
    //Carga los colaboradores que poseen el rol de conductores
    private void cargarConductor(){
        conductor = FXCollections.observableArrayList();
        List<Colaborador> listaWS = ColaboradorDAO.obtenerConductores();
        if(listaWS !=null){
            conductor.addAll(listaWS);
            cbConductor.setItems(conductor);
        }
    }
    
    private void generarNumeroIdentificacionInterno() {
        String anio = tfAnio.getText();
        String vin = tfVIN.getText();
        String nii = anio + vin.substring(0, 4);
        tfNII.setText(nii);
    }
    
    //Verifica que sea un numero
    private boolean esNumerico(String cadena) {
        return cadena.matches("\\d+"); // Verifica que la cadena contenga solo dígitos
    }
}
