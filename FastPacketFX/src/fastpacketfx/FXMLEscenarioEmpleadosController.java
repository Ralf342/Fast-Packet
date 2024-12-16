package fastpacketfx;

import fastpacketfx.modelo.dao.ColaboradorDAO;
import fastpacketfx.pojo.Colaborador;
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

public class FXMLEscenarioEmpleadosController implements Initializable {
    
    private ObservableList<Colaborador> colaboradores;
    @FXML
    private TextField tf_Buscar;
    @FXML
    private TableColumn colPersonal;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colCorreo;
    @FXML
    private TableColumn colCurp;
    @FXML
    private TableColumn colRol;
    @FXML
    private TableView<Colaborador> tbColaboradores;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarInformacionTabla();
    }

    private void configurarTabla(){
        colPersonal.setCellValueFactory(new PropertyValueFactory("noPersonal"));
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
        colCurp.setCellValueFactory(new PropertyValueFactory("curp"));
        colRol.setCellValueFactory(new PropertyValueFactory("rol"));
    }
    
    private void cargarInformacionTabla(){
        //no se inicializa con new sino con: FXCollections
        colaboradores = FXCollections.observableArrayList();
        //obtener lo que trae el dao
        System.out.print("Entrando a la lista");
        List<Colaborador> listaWS = ColaboradorDAO.obtenerColaboradores();
        if(listaWS != null){
            System.out.print("Entando a la condicion de busqueda");
            colaboradores.addAll(listaWS);
            tbColaboradores.setItems(colaboradores);
        }else{
            Utilidades.mostrarAlertaSimple("Datos no disponible", "Lo sentimos, por el momento no se puede cargar la info de colaboradores", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onClickBuscar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioEmpleadosController.onClickBuscar()");
    }

    @FXML
    private void onClickAgregar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioEmpleadosController.onClickAgregar()");
    }

    @FXML
    private void onClickEditar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioEmpleadosController.onClickEditar()");
    }

    @FXML
    private void onClickEliminar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioEmpleadosController.onClickEliminar()");
    }
    
}
