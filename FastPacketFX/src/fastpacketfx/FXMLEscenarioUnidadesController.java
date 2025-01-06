package fastpacketfx;

import fastpacketfx.modelo.dao.ColaboradorDAO;
import fastpacketfx.modelo.dao.UnidadDAO;
import fastpacketfx.pojo.Colaborador;
import fastpacketfx.pojo.Unidad;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLEscenarioUnidadesController implements Initializable {

    private ObservableList<Unidad> unidades;
    @FXML
    private TextField tf_buscar;
    @FXML
    private TableView<Unidad> tvUnidad;
    @FXML
    private TableColumn tcVin;
    @FXML
    private TableColumn tcNii;
    @FXML
    private TableColumn tcModelo;
    @FXML
    private TableColumn tcAnio;
    @FXML
    private TableColumn tcMarca;
    @FXML
    private TableColumn tcTipo;
    @FXML
    private TableColumn tcConductor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       configurarTabla();
       cargarInformacionTabla();
    }

    private void configurarTabla(){
        tcVin.setCellValueFactory(new PropertyValueFactory("vin"));
        tcNii.setCellValueFactory(new PropertyValueFactory("nii"));
        tcModelo.setCellValueFactory(new PropertyValueFactory("modelo"));
        tcAnio.setCellValueFactory(new PropertyValueFactory("anio"));
        tcMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        tcTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        tcConductor.setCellValueFactory(new PropertyValueFactory("conductor"));
    }
    
    private void cargarInformacionTabla(){
        //no se inicializa con new sino con: FXCollections
        unidades = FXCollections.observableArrayList();
        //obtener lo que trae el dao
        List<Unidad> listaWS = UnidadDAO.obtenerUnidades();
        if(listaWS != null){
            unidades.addAll(listaWS);
            tvUnidad.setItems(unidades);
        }else{
            Utilidades.mostrarAlertaSimple("Datos no disponible", "Lo sentimos, por el momento no se puede cargar la info de colaboradores", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onClickBuscar(ActionEvent event) {
        String nii=tf_buscar.getText();
        unidades = FXCollections.observableArrayList();
        //obtener lo que trae el dao
        if(nii.isEmpty()){
            cargarInformacionTabla();
        }else{
            List<Unidad> listaWS = UnidadDAO.buscarUnidad(nii);
        if(listaWS != null){
            unidades.addAll(listaWS);
            tvUnidad.setItems(unidades);
        }else{
            Utilidades.mostrarAlertaSimple("Datos no disponible", "Lo sentimos, por el momento no se puede cargar la info de colaboradores", Alert.AlertType.ERROR);
        }
        }
    }

    @FXML
    private void onClickAgregar(ActionEvent event) {
        try{
            Stage escenario = new Stage();
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLFormularioUnidad.fxml"));
            Parent vista = cargador.load();
            //--
            FXMLFormularioUnidadController controlador = cargador.getController();
            //--
            Scene escenaFormulario = new Scene(vista);
            escenario.setScene(escenaFormulario);
            escenario.setTitle("Agregar");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        }catch (Exception e){
        }
    }

    @FXML
    private void onClickEditar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioUnidadesController.onClickEditar()");
    }

    @FXML
    private void onClickEliminar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioUnidadesController.onClickEliminar()");
    }
    
}
