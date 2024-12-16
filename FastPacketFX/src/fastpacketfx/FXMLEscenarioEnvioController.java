package fastpacketfx;

import fastpacketfx.modelo.dao.ClienteDAO;
import fastpacketfx.modelo.dao.EnvioDAO;
import fastpacketfx.pojo.Cliente;
import fastpacketfx.pojo.Envio;
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

public class FXMLEscenarioEnvioController implements Initializable {

    private ObservableList<Envio> envios;
    @FXML
    private TextField tf_buscar;
    @FXML
    private TableView<Envio> tbEnvios;
    @FXML
    private TableColumn tcNumeroGuia;
    @FXML
    private TableColumn tcCliente;
    @FXML
    private TableColumn tcDestino;
    @FXML
    private TableColumn tcCiudad;
    @FXML
    private TableColumn tcEstado;
    @FXML
    private TableColumn tcCalle;
    @FXML
    private TableColumn tcColonia;
    @FXML
    private TableColumn tcNumeroCasa;
    @FXML
    private TableColumn tcCodigoPostal;
    @FXML
    private TableColumn tcEstatus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarInformacionTabla();
    }

    private void configurarTabla(){
        tcNumeroGuia.setCellValueFactory(new PropertyValueFactory("numeroDeGuia"));
        tcCliente.setCellValueFactory(new PropertyValueFactory("cliente"));
        tcDestino.setCellValueFactory(new PropertyValueFactory("destino"));
        tcCiudad.setCellValueFactory(new PropertyValueFactory("ciudad"));
        tcEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        tcCalle.setCellValueFactory(new PropertyValueFactory("calle"));
        tcColonia.setCellValueFactory(new PropertyValueFactory("colonia"));
        tcNumeroCasa.setCellValueFactory(new PropertyValueFactory("numero"));
        tcCodigoPostal.setCellValueFactory(new PropertyValueFactory("codigoPostal"));
        tcEstatus.setCellValueFactory(new PropertyValueFactory("estatus"));
    }
    
    private void cargarInformacionTabla(){
        //no se inicializa con new sino con: FXCollections
        envios = FXCollections.observableArrayList();
        //obtener lo que trae el dao
        System.out.print("Entrando a la lista");
        List<Envio> listaWS = EnvioDAO.obtenerEnvios();
        if(listaWS != null){
            System.out.print("Entando a la condicion de busqueda");
            envios.addAll(listaWS);
            tbEnvios.setItems(envios);
        }else{
            Utilidades.mostrarAlertaSimple("Datos no disponible", "Lo sentimos, por el momento no se puede cargar la info de envios", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onClickBuscar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioEnvioController.onClickBuscar()");
    }

    @FXML
    private void onClickAgregar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioEnvioController.onClickAgregar()");
    }

    @FXML
    private void onClickActualizar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioEnvioController.onClickActualizar()");
    }
    
}
