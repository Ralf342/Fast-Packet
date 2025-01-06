package fastpacketfx;

import fastpacketfx.modelo.dao.ColaboradorDAO;
import fastpacketfx.pojo.Colaborador;
import fastpacketfx.pojo.Mensaje;
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
        List<Colaborador> listaWS = ColaboradorDAO.obtenerColaboradores();
        if(listaWS != null){
            colaboradores.addAll(listaWS);
            tbColaboradores.setItems(colaboradores);
        }else{
            Utilidades.mostrarAlertaSimple("Datos no disponible", "Lo sentimos, por el momento no se puede cargar la info de colaboradores", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onClickBuscar(ActionEvent event) {
        String numPersonalString=tf_Buscar.getText();
        Integer numPersonal = (numPersonalString.isEmpty()) ?  0 : Integer.valueOf(numPersonalString);
        colaboradores = FXCollections.observableArrayList();
        //obtener lo que trae el dao
        if(numPersonal==0 || numPersonal<0){
            cargarInformacionTabla();
        }else{
            List<Colaborador> listaWS = ColaboradorDAO.buscarColaborador(numPersonal);
        if(listaWS != null){
            colaboradores.addAll(listaWS);
            tbColaboradores.setItems(colaboradores);
        }else{
            Utilidades.mostrarAlertaSimple("Datos no disponible", "Lo sentimos, por el momento no se puede cargar la info de colaboradores", Alert.AlertType.ERROR);
        }
        }
    }

    @FXML
    private void onClickAgregar(ActionEvent event) {
        agregar();
    }

    @FXML
    private void onClickEditar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenarioEmpleadosController.onClickEditar()");
    }

    @FXML
    private void onClickEliminar(ActionEvent event) {
        Colaborador colaborador = tbColaboradores.getSelectionModel().getSelectedItem();
        if(colaborador !=null){
            boolean seElimina= Utilidades.mostrarAlertaConfirmacion("Eliminar", "¿Estas seguro de eliminar al colaborador "+ colaborador.getNombre() + "?");
            if(seElimina){
                eliminarColaborador(colaborador.getIdColaborador());
            }
        }else{
            Utilidades.mostrarAlertaSimple("Seleccionar Colaborador","Para poder eliminar debes seleccionar al colaborador de la tabla",Alert.AlertType.WARNING);
        }
    }
    
    private void eliminarColaborador(Integer idColaborador){
        System.out.println("ID: "+idColaborador);
        Mensaje msj = ColaboradorDAO.borrarColaborador(idColaborador);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Colaborador eliminado","La información del Colaborador se a borrado correctamente", Alert.AlertType.INFORMATION);
            //observador.notificarOperacionExitosa("Eliminar", null);
        }else{
            Utilidades.mostrarAlertaSimple("Error al borrar", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    private void agregar(){      
        try{
            Stage escenario = new Stage();
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLFormularioEmpleado.fxml"));
            Parent vista = cargador.load();
            //--
            FXMLFormularioEmpleadoController controlador = cargador.getController();
            //--
            Scene escenaFormulario = new Scene(vista);
            escenario.setScene(escenaFormulario);
            escenario.setTitle("Nuevo Colaborador");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        }catch (Exception e){
        }
        }
}
