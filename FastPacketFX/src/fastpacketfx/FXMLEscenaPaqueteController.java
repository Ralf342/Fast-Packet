package fastpacketfx;

import fastpacketfx.modelo.dao.PaqueteDAO;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLEscenaPaqueteController implements Initializable {

    private ObservableList<Paquete> paquetes;
    @FXML
    private TextField tf_buscar;
    @FXML
    private TableColumn<?, ?> tcNumeroGuia;
    @FXML
    private TableColumn<?, ?> tcAlto;
    @FXML
    private TableColumn<?, ?> tcAncho;
    @FXML
    private TableColumn<?, ?> tcPeso;
    @FXML
    private TableColumn<?, ?> tcDescripcion;
    @FXML
    private TableView<Paquete> tbPaquetes;
    @FXML
    private TableColumn<?, ?> tcProfundidad;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        System.out.print("Entrando a la lista");
        List<Paquete> listaWS = PaqueteDAO.obtenerPaquetes();
        if(listaWS != null){
            System.out.print("Entando a la condicion de busqueda");
            paquetes.addAll(listaWS);
            tbPaquetes.setItems(paquetes);
        }else{
            Utilidades.mostrarAlertaSimple("Datos no disponible", "Lo sentimos, por el momento no se puede cargar la info de colaboradores", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onClickBuscar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenaPaqueteController.onClickBuscar()");
    }

    @FXML
    private void onClickAgregar(ActionEvent event) {
        agregar();
        System.out.println("fastpacketfx.FXMLEscenaPaqueteController.onClickAgregar()");
    }

    @FXML
    private void onClickEditar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenaPaqueteController.onClickEditar()");
    }

    @FXML
    private void onClickEliminar(ActionEvent event) {
        System.out.println("fastpacketfx.FXMLEscenaPaqueteController.onClickEliminar()");
    }
    
    private void agregar(){
        /*try {
            Stage escenario = new Stage();
            FXMLLoader cargador = FXMLLoader.load(getClass().getResource("FXMLFormularioPaquete.fxml"));
            Parent vista = cargador.load();
            FXMLFormularioPaqueteController controlador = cargador.getController();
            
            Scene escenaPrincipal = new Scene(vista);
            escenario.setScene(escenaPrincipal);
            escenario.setTitle("Agregar");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        }catch(IOException e){
        }*/
        
        try{
            Stage escenario = new Stage();
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLFormularioPaquete.fxml"));
            Parent vista = cargador.load();
            //--
            FXMLFormularioPaqueteController controlador = cargador.getController();
            //--
            Scene escenaFormulario = new Scene(vista);
            escenario.setScene(escenaFormulario);
            escenario.setTitle("Agregar");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        }catch (Exception e){
        }
        }
    }
