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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    }
    
    private void cargarInformacionTabla(){
        //no se inicializa con new sino con: FXCollections
        unidades = FXCollections.observableArrayList();
        //obtener lo que trae el dao
        System.out.print("Entrando a la lista");
        List<Unidad> listaWS = UnidadDAO.obtenerColaboradores();
        if(listaWS != null){
            System.out.print("Entando a la condicion de busqueda");
            unidades.addAll(listaWS);
            tvUnidad.setItems(unidades);
        }else{
            Utilidades.mostrarAlertaSimple("Datos no disponible", "Lo sentimos, por el momento no se puede cargar la info de colaboradores", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onClickBuscar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioUnidadesController.onClickBuscar()");
    }

    @FXML
    private void onClickAgregar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioUnidadesController.onClickAgregar()");
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
