package fastpacketfx;

import fastpacketfx.modelo.dao.ClienteDAO;
import fastpacketfx.pojo.Cliente;
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

public class FXMLEscenarioClientesController implements Initializable {

    private ObservableList<Cliente> clientes;
    @FXML
    private TextField tf_buscar;
    @FXML
    private TableView<Cliente> tbClientes;
    @FXML
    private TableColumn tcNombre;
    @FXML
    private TableColumn tcDireccion;
    @FXML
    private TableColumn tcTelefono;
    @FXML
    private TableColumn tcCorreo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarInformacionTabla();
    }

    private void configurarTabla(){
        tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        tcDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
        tcTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        tcCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
    }
    
    private void cargarInformacionTabla(){
        //no se inicializa con new sino con: FXCollections
        clientes = FXCollections.observableArrayList();
        //obtener lo que trae el dao
        System.out.print("Entrando a la lista");
        List<Cliente> listaWS = ClienteDAO.obtenerClientes();
        if(listaWS != null){
            System.out.print("Entando a la condicion de busqueda");
            clientes.addAll(listaWS);
            tbClientes.setItems(clientes);
        }else{
            Utilidades.mostrarAlertaSimple("Datos no disponible", "Lo sentimos, por el momento no se puede cargar la info de colaboradores", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onClickBuscar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioClientesController.onClickBuscar()");
    }

    @FXML
    private void onClickAgregar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioClientesController.onClickAgregar()");
    }

    @FXML
    private void onClickEditar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioClientesController.onClickEditar()");
    }

    @FXML
    private void onClickBorrar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioClientesController.onClickBorrar()");
    }
    
}
