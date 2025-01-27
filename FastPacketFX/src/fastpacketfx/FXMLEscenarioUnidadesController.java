package fastpacketfx;

import fastpacketfx.interfaces.INotificadorOperacion;
import fastpacketfx.modelo.dao.ColaboradorDAO;
import fastpacketfx.modelo.dao.UnidadDAO;
import fastpacketfx.pojo.Colaborador;
import fastpacketfx.pojo.Mensaje;
import fastpacketfx.pojo.Unidad;
import fastpacketfx.utilidades.Utilidades;
import java.net.URL;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLEscenarioUnidadesController implements Initializable,INotificadorOperacion {

    private INotificadorOperacion observador;
    private Unidad unidadEdicion;
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
        this.observador = this;
        this.unidadEdicion = unidadEdicion;
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
        agregar(this,null);
    }

    @FXML
    private void onClickEditar(ActionEvent event) {
       Unidad unidad = tvUnidad.getSelectionModel().getSelectedItem();
        if(unidad !=null){
            agregar(this,unidad);
        }else{
            Utilidades.mostrarAlertaSimple("Seleccionar unidad de la tabla","Para poder editar debes elecir la unidad en la tabla", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onClickEliminar(ActionEvent event) {
        Unidad unidad = tvUnidad.getSelectionModel().getSelectedItem();
        if (unidad != null) {
            TextInputDialog dialogoTexto = new TextInputDialog();
            dialogoTexto.setTitle("Dar de baja Unidad");
            dialogoTexto.setHeaderText("Motivo de la baja");
            dialogoTexto.setContentText("Por favor, ingresa el motivo de la baja:");
            Optional<String> resultado = dialogoTexto.showAndWait();

            if (resultado.isPresent() && !resultado.get().isEmpty()) {
                String motivo = resultado.get();
                unidad.setMotivo(motivo);
                unidad.setBaja(2);
                boolean baja = Utilidades.mostrarAlertaConfirmacion("Baja", "¿Estás seguro de quieres dar de baja la unidad?");
                if (baja) {
                    UnidadDAO.darBaja(unidad);
                    observador.notificarOperacionExitosa("Baja", unidad.getVin());
                    Utilidades.mostrarAlertaSimple("Unidad dada de baja", "La unidad ha sido dada de baja. Motivo: " + motivo, Alert.AlertType.INFORMATION);
                }
            } else {
                Utilidades.mostrarAlertaSimple("Acción cancelada", "No se proporcionó un motivo, fue cancelado.", Alert.AlertType.WARNING);
            }
        } else {
            Utilidades.mostrarAlertaSimple("Seleccionar Unidad", "Para poder dar de baja debes seleccionar una unidad de la tabla.", Alert.AlertType.WARNING);
        }
    }
    
    private void agregar(INotificadorOperacion observador, Unidad unidad){
        try{
            Stage escenario = new Stage();
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLFormularioUnidad.fxml"));
            Parent vista = cargador.load();
            //--
            FXMLFormularioUnidadController controlador = cargador.getController();
            controlador.inicializarValores(observador, unidad);
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
