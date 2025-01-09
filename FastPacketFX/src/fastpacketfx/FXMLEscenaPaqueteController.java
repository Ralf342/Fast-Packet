package fastpacketfx;

import fastpacketfx.interfaces.INotificadorOperacion;
import fastpacketfx.modelo.dao.PaqueteDAO;
import fastpacketfx.pojo.Mensaje;
import fastpacketfx.pojo.Paquete;
import fastpacketfx.utilidades.Utilidades;
import java.io.IOException;
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

public class FXMLEscenaPaqueteController implements Initializable, INotificadorOperacion {

    private INotificadorOperacion observador;
    private Paquete paqueteEdicion;
    private ObservableList<Paquete> paquetes;
    @FXML
    private TextField tf_buscar;
    @FXML
    private TableColumn tcNumeroGuia;
    @FXML
    private TableColumn tcAlto;
    @FXML
    private TableColumn tcAncho;
    @FXML
    private TableColumn tcPeso;
    @FXML
    private TableColumn tcDescripcion;
    @FXML
    private TableView<Paquete> tbPaquetes;
    @FXML
    private TableColumn tcProfundidad;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.observador = this;
        this.paqueteEdicion = paqueteEdicion;
        configurarTabla();
        cargarInformacionTabla();
    }

    private void configurarTabla(){
        tcNumeroGuia.setCellValueFactory(new PropertyValueFactory("numeroDeGuia"));
        tcAlto.setCellValueFactory(new PropertyValueFactory("alto"));
        tcAncho.setCellValueFactory(new PropertyValueFactory("ancho"));
        tcPeso.setCellValueFactory(new PropertyValueFactory("peso"));
        tcProfundidad.setCellValueFactory(new PropertyValueFactory("profundidad"));
        tcDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
    }

    
    private void cargarInformacionTabla(){
        //no se inicializa con new sino con: FXCollections
        paquetes = FXCollections.observableArrayList();
        //obtener lo que trae el dao
        List<Paquete> listaWS = PaqueteDAO.obtenerPaquetes();
        if(listaWS != null){
            paquetes.addAll(listaWS);
            tbPaquetes.setItems(paquetes);
        }else{
            Utilidades.mostrarAlertaSimple("Datos no disponible", "Lo sentimos, por el momento no se puede cargar la info de colaboradores", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onClickBuscar(ActionEvent event) {
        String numGuiaString=tf_buscar.getText();
        Integer numGuia = (numGuiaString.isEmpty()) ?  0 : Integer.valueOf(numGuiaString);
        paquetes = FXCollections.observableArrayList();
        //obtener lo que trae el dao
        if(numGuia==0 || numGuia<0){
            cargarInformacionTabla();
        }else{
            List<Paquete> listaWS = PaqueteDAO.buscarPaquete(numGuia);
        if(listaWS != null){
            paquetes.addAll(listaWS);
            tbPaquetes.setItems(paquetes);
        }else{
            Utilidades.mostrarAlertaSimple("Datos no disponible", "Lo sentimos, por el momento no se puede cargar la info de colaboradores", Alert.AlertType.ERROR);
        }
        }
    }

    @FXML
    private void onClickAgregar(ActionEvent event) {
        agregar(this,null);
    }

    @FXML
    private void onClickEditar(ActionEvent event) {
        Paquete paquete = tbPaquetes.getSelectionModel().getSelectedItem();
        if(paquete !=null){
            agregar(this,paquete);
        }else{
            Utilidades.mostrarAlertaSimple("Seleccionar paquete de la tabla","Para poder editar debes elecir al paquete en la tabla", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onClickEliminar(ActionEvent event) {
        Paquete paquete = tbPaquetes.getSelectionModel().getSelectedItem();
        if(paquete !=null){
            boolean seElimina= Utilidades.mostrarAlertaConfirmacion("Eliminar", "¿Estas seguro de eliminar al paquete?");
            if(seElimina){
                eliminarPaquete(paquete.getIdPaquete());
                observador.notificarOperacionExitosa("Eliminar", paquete.getDescripcion());
            }
        }else{
            Utilidades.mostrarAlertaSimple("Seleccionar Paquete","Para poder eliminar debes seleccionar al paquete de la tabla",Alert.AlertType.WARNING);
        }
    }
    
    private void eliminarPaquete(Integer idPaquete){
        System.out.println("ID: "+idPaquete);
        Mensaje msj = PaqueteDAO.borrarPaquete(idPaquete);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Colaborador eliminado","La información del colaborador se a borrado correctamente", Alert.AlertType.INFORMATION);
            observador.notificarOperacionExitosa("Eliminar", null);
        }else{
            Utilidades.mostrarAlertaSimple("Error al editar", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void agregar(INotificadorOperacion observador, Paquete paquete){       
        try{
            Stage escenario = new Stage();
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLFormularioPaquete.fxml"));
            Parent vista = cargador.load();
            //--
            FXMLFormularioPaqueteController controlador = cargador.getController();
            controlador.inicializarValores(observador, paquete);
            //--
            Scene escenaFormulario = new Scene(vista);
            escenario.setScene(escenaFormulario);
            escenario.setTitle("Agregar");
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
