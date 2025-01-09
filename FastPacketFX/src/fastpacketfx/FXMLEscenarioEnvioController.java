package fastpacketfx;

import fastpacketfx.interfaces.INotificadorOperacion;
import fastpacketfx.modelo.dao.ClienteDAO;
import fastpacketfx.modelo.dao.EnvioDAO;
import fastpacketfx.pojo.Cliente;
import fastpacketfx.pojo.Envio;
import fastpacketfx.pojo.Login;
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
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLEscenarioEnvioController implements Initializable, INotificadorOperacion {

    private INotificadorOperacion observador;
    private Envio envioEdicion;
    private Login login;
    private ObservableList<Envio> envios;
    @FXML
    private TextField tf_buscar;
    @FXML
    private TableView<Envio> tbEnvios;
    @FXML
    private TableColumn tcNumeroGuia;
    @FXML
    private TableColumn tcCiudadOrigen;
    @FXML
    private TableColumn tcEstadoOrigen;
    @FXML
    private TableColumn tcOrigen;
    @FXML
    private TableColumn tcCosto;
    @FXML
    private TableColumn tcCliente;
    @FXML
    private TableColumn tcDestino;
    @FXML
    private TableColumn tcCiudadDestino;
    @FXML
    private TableColumn tcEstadoDestino;
    @FXML
    private TableColumn tcEstatus;
    @FXML
    private TableColumn tcConductor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.observador = this;
        this.envioEdicion = envioEdicion;
        configurarTabla();
        cargarInformacionTabla();
    }
    
    public void inicializarValores(Login login){
           this.login = login;
    }

    private void configurarTabla(){
        tcNumeroGuia.setCellValueFactory(new PropertyValueFactory("numeroDeGuia"));
        tcCiudadOrigen.setCellValueFactory(new PropertyValueFactory("ciudadOrigen"));
        tcEstadoOrigen.setCellValueFactory(new PropertyValueFactory("estadoOrigen"));
        tcOrigen.setCellValueFactory(new PropertyValueFactory("origen"));
        tcCosto.setCellValueFactory(new PropertyValueFactory("costo"));
        tcCliente.setCellValueFactory(new PropertyValueFactory("cliente"));
        tcDestino.setCellValueFactory(new PropertyValueFactory("destino"));
        tcCiudadDestino.setCellValueFactory(new PropertyValueFactory("ciudad"));
        tcEstadoDestino.setCellValueFactory(new PropertyValueFactory("estado"));
        tcEstatus.setCellValueFactory(new PropertyValueFactory("estatus"));
        tcConductor.setCellValueFactory(new PropertyValueFactory("conductor"));
    }
    
    private void cargarInformacionTabla(){
        //no se inicializa con new sino con: FXCollections
        envios = FXCollections.observableArrayList();
        //obtener lo que trae el dao
        List<Envio> listaWS = EnvioDAO.obtenerEnvios();
        if(listaWS != null){
            envios.addAll(listaWS);
            tbEnvios.setItems(envios);
        }else{
            Utilidades.mostrarAlertaSimple("Datos no disponible", "Lo sentimos, por el momento no se puede cargar la info de envios", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onClickBuscar(ActionEvent event) {
        String numGuiaString=tf_buscar.getText();
        Integer numeroGuia = (numGuiaString.isEmpty()) ?  0 : Integer.valueOf(numGuiaString);
        envios = FXCollections.observableArrayList();
        //obtener lo que trae el dao
        if(numeroGuia==0 || numeroGuia<0){
            cargarInformacionTabla();
        }else{
            List<Envio> listaWS = EnvioDAO.buscarEnvio(numeroGuia);
        if(listaWS != null){
            envios.addAll(listaWS);
            tbEnvios.setItems(envios);
        }else{
            Utilidades.mostrarAlertaSimple("Datos no disponible", "Lo sentimos, por el momento no se puede cargar la info de colaboradores", Alert.AlertType.ERROR);
        }
        }
    }

    @FXML
    private void onClickAgregar(ActionEvent event) {
        agregar(this,null,login);
    }

    @FXML
    private void onClickActualizar(ActionEvent event) {
        Envio envio = tbEnvios.getSelectionModel().getSelectedItem();
        System.out.println(envio.getIdClienteDestino());
        if(envio !=null){
            agregar(this,envio,login);
        }else{
            Utilidades.mostrarAlertaSimple("Seleccionar envio de la tabla","Para poder editar debes elecir al envio en la tabla", Alert.AlertType.WARNING);
        }
    }
    
    private void agregar(INotificadorOperacion observador, Envio envio, Login login){
        try{
            Stage escenario = new Stage();
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLFormularioEnvio.fxml"));
            Parent vista = cargador.load();
            //--
            FXMLFormularioEnvioController controlador = cargador.getController();
            controlador.inicializarValores(observador, envio, login);
            //--
            Scene escenaFormulario = new Scene(vista);
            escenario.setScene(escenaFormulario);
            escenario.setTitle("Nuevo Envio");
            escenario.getIcons().add(new Image(getClass().getResourceAsStream("/fastpacketfx/recursos/LOGO.png")));
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        }catch (Exception e){
        }
    }
    @Override
    public void notificarOperacionExitosa(String tipo, String nombre) {
        System.out.println("Operacion: " + tipo);
        System.err.println("Nombre: "+nombre);
        cargarInformacionTabla();
    }
    
}
