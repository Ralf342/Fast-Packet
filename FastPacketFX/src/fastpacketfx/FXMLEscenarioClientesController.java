package fastpacketfx;

import fastpacketfx.interfaces.INotificadorOperacion;
import fastpacketfx.modelo.dao.ClienteDAO;
import fastpacketfx.pojo.Cliente;
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

public class FXMLEscenarioClientesController implements Initializable, INotificadorOperacion{

    private INotificadorOperacion observador;
    private Cliente clienteEdicion;
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
    @FXML
    private TableColumn tbEstado;
    @FXML
    private TableColumn tbCiudad;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.observador = this;
        this.clienteEdicion = clienteEdicion;
        configurarTabla();
        cargarInformacionTabla();
    }

    private void configurarTabla(){
        tcNombre.setCellValueFactory(new PropertyValueFactory("nombreCompleto"));
        tcDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
        tcTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        tcCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
        tbEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        tbCiudad.setCellValueFactory(new PropertyValueFactory("ciudad"));
    }
    
    private void cargarInformacionTabla(){
        //no se inicializa con new sino con: FXCollections
        clientes = FXCollections.observableArrayList();
        //obtener lo que trae el dao
        List<Cliente> listaWS = ClienteDAO.obtenerClientes();
        if(listaWS != null){
            clientes.addAll(listaWS);
            tbClientes.setItems(clientes);
        }else{
            Utilidades.mostrarAlertaSimple("Datos no disponible", "Lo sentimos, por el momento no se puede cargar la info de colaboradores", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onClickBuscar(ActionEvent event) {
        String correo=tf_buscar.getText();
        clientes = FXCollections.observableArrayList();
        //obtener lo que trae el dao
        if(correo.isEmpty()){
            cargarInformacionTabla();
        }else{
            List<Cliente> listaWS = ClienteDAO.buscarCliente(correo);
        if(listaWS != null){
            clientes.addAll(listaWS);
            tbClientes.setItems(clientes);
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
        Cliente cliente = tbClientes.getSelectionModel().getSelectedItem();
        if(cliente !=null){
            agregar(this,cliente);
        }else{
            Utilidades.mostrarAlertaSimple("Seleccionar Colaborador de la tabla","Para poder editar debes elegir al cliente en la tabla", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onClickBorrar(ActionEvent event) {
         Cliente cliente = tbClientes.getSelectionModel().getSelectedItem();
        if(cliente !=null){
            boolean seElimina= Utilidades.mostrarAlertaConfirmacion("Eliminar", "¿Estas seguro de eliminar al cliente "+ cliente.getNombre() + "?");
            if(seElimina){
                eliminarColaborador(cliente.getIdCliente());
                observador.notificarOperacionExitosa("Eliminar", cliente.getNombre());
            }
        }else{
            Utilidades.mostrarAlertaSimple("Seleccionar Cliente","Para poder eliminar debes seleccionar al cliente de la tabla",Alert.AlertType.WARNING);
        }
    }
    
    private void agregar(INotificadorOperacion observador, Cliente cliente){
        try{
            Stage escenario = new Stage();
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLFormularioCliente.fxml"));
            Parent vista = cargador.load();
            //--
            FXMLFormularioClienteController controlador = cargador.getController();
            controlador.inicializarValores(observador, cliente);
            //--
            Scene escenaFormulario = new Scene(vista);
            escenario.setScene(escenaFormulario);
            escenario.setTitle("Nuevo Cliente");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        }catch (Exception e){
        }
    }
    
    private void eliminarColaborador(Integer idCliente){
        System.out.println("ID: "+idCliente);
        Mensaje msj = ClienteDAO.borrarCliente(idCliente);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Cliente eliminado","La información del Cliente se a borrado correctamente", Alert.AlertType.INFORMATION);
        }else{
            Utilidades.mostrarAlertaSimple("Error al borrar", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    @Override
    public void notificarOperacionExitosa(String tipo, String nombre) {
        System.out.println("Operacion: " + tipo);
        System.err.println("Nombre: "+nombre);
        cargarInformacionTabla();
    }

}
