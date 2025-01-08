package fastpacketfx;

import fastpacketfx.interfaces.INotificadorOperacion;
import fastpacketfx.modelo.dao.ColaboradorDAO;
import fastpacketfx.modelo.dao.UnidadDAO;
import fastpacketfx.pojo.Colaborador;
import fastpacketfx.pojo.Mensaje;
import fastpacketfx.pojo.TipoUnidad;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLFormularioUnidadController implements Initializable {

    private ObservableList<TipoUnidad> tipos;
    private ObservableList<Colaborador> conductor;
    
    private INotificadorOperacion observador;
    private Unidad unidadEdicion;
    private boolean modoEdicion;
    @FXML
    private TextField tfMarca;
    @FXML
    private TextField tfModelo;
    @FXML
    private TextField tfAnio;
    @FXML
    private TextField tfVIN;
    @FXML
    private ComboBox<TipoUnidad> cbTipoUnidad;
    @FXML
    private TextField tfNII;
    @FXML
    private ComboBox<Colaborador> cbConductor;
    @FXML
    private Label lbMarcaFaltante;
    @FXML
    private Label lbModeloFaltante;
    @FXML
    private Label lbAnioFaltante;
    @FXML
    private Label lbNIIFaltante;
    @FXML
    private Label lbTipoUnidadFaltante;
    @FXML
    private Label lbVINFaltante;
    @FXML
    private Label lbConductorFaltante;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTipoUnidad();
        cargarConductor();
    }    
    
    public void inicializarValores(INotificadorOperacion observador, Unidad unidadEdicion){
        this.observador = observador;
        this.unidadEdicion = unidadEdicion;
        if(unidadEdicion !=null){
            modoEdicion = true;
            cargarDatosEdicion();
        }
    }
    
    private void cargarDatosEdicion(){
        tfMarca.setText(this.unidadEdicion.getMarca());
        tfModelo.setText(this.unidadEdicion.getModelo());
        tfAnio.setText(this.unidadEdicion.getAnio());
        int tipoUnidad = obtenerTipoUnidad(this.unidadEdicion.getIdTipoUnidad());
        cbTipoUnidad.getSelectionModel().select(tipoUnidad);
        tfVIN.setText(this.unidadEdicion.getVin());
        int idConductor = obtenerConductor(this.unidadEdicion.getIdColaborador());
        cbConductor.getSelectionModel().select(idConductor);
        tfNII.setText(this.unidadEdicion.getNii());
        
        tfVIN.setDisable(true);
        
    }

    @FXML
    private void onClickAgregar(ActionEvent event) {
        String marca = tfMarca.getText();
        String modelo = tfModelo.getText();
        String anio = tfAnio.getText();
        String vin = tfVIN.getText();
        int tipoUnidad =(cbTipoUnidad.getSelectionModel().getSelectedItem() !=null)
               ? cbTipoUnidad.getSelectionModel().getSelectedItem().getIdTipoUnidad(): 0;
        int conductor =(cbConductor.getSelectionModel().getSelectedItem() !=null)
               ? cbConductor.getSelectionModel().getSelectedItem().getIdColaborador(): 0;
        
        Unidad unidad = new Unidad();
        unidad.setMarca(marca);
        unidad.setModelo(modelo);
        unidad.setAnio(anio);
        unidad.setVin(vin);
        unidad.setIdTipoUnidad(tipoUnidad);
        unidad.setIdColaborador(conductor);
        
        if(camposValidos(unidad)){
            unidad.setNii(tfNII.getText());
            if(!modoEdicion){
                guardarDatosUnidad(unidad);
            }else{
                unidad.setIdUnidad(unidadEdicion.getIdUnidad());
                editarDatosUnidad(unidad);
            }
        }else{
            Utilidades.mostrarAlertaSimple("Error al guardar","Existen algunos campos vacios necesarios para guardar la información", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onClickCancelar(ActionEvent event) {
        cerrarVentana();
    }
    
    private void guardarDatosUnidad(Unidad unidad){
        Mensaje msj = UnidadDAO.registrarUnidad(unidad);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Unidad registrada", "La unidad se registro correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            //observador.notificarOperacionExitosa("Guardar", cliente.getNombre());
        }else{
            Utilidades.mostrarAlertaSimple("Error al guardar","Ese conductor ya posee una unidad a su cargo, seleccione otro porfavor", Alert.AlertType.ERROR);
        }
    }
    
    private void editarDatosUnidad(Unidad unidad){
        Mensaje msj = UnidadDAO.editarUnidad(unidad);
        if(!msj.isError()){
            Utilidades.mostrarAlertaSimple("Unidad editada","La información de la unidad se a modificado correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            observador.notificarOperacionExitosa("Editar", unidad.getMarca().toString());
        }else{
            Utilidades.mostrarAlertaSimple("Error al editar", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }
    
    //Para cerrar la ventana
    private void cerrarVentana(){
        Stage escenario = (Stage)tfAnio.getScene().getWindow();
        escenario.close();
        ( (Stage) tfAnio.getScene().getWindow()).close();
    }
    
    //Carga los tipos de unidad que existen
    private void cargarTipoUnidad(){
        tipos = FXCollections.observableArrayList();
        List<TipoUnidad> listaWS = UnidadDAO.obtenerTiposUnidades();
        if(listaWS !=null){
            tipos.addAll(listaWS);
            cbTipoUnidad.setItems(tipos);
        }
    }
    
    //Carga los colaboradores que poseen el rol de conductores
    private void cargarConductor(){
        conductor = FXCollections.observableArrayList();
        List<Colaborador> listaWS = ColaboradorDAO.obtenerConductores();
        if(listaWS !=null){
            conductor.addAll(listaWS);
            cbConductor.setItems(conductor);
        }
    }
    
    private void generarNumeroIdentificacionInterno() {
        String anio = tfAnio.getText();
        String vin = tfVIN.getText();
        String nii = anio + vin.substring(0, 4);
        tfNII.setText(nii);
    }
    
    //Verifica que sea un numero
    private boolean esNumerico(String cadena) {
        return cadena.matches("\\d+"); // Verifica que la cadena contenga solo dígitos
    }
    
    private boolean camposValidos(Unidad unidad){
        boolean valido=true;
        boolean validacionnii = true;
        lbMarcaFaltante.setText(" ");
        lbAnioFaltante.setText(" ");
        lbConductorFaltante.setText(" ");
        lbModeloFaltante.setText(" ");
        lbNIIFaltante.setText(" ");
        lbTipoUnidadFaltante.setText(" ");
        lbVINFaltante.setText(" ");
        
        //Validacion de año
        if(!esNumerico(unidad.getAnio()) || unidad.getAnio().length()>4){
            valido=false;
            validacionnii=false;
            lbAnioFaltante.setText("formato incorrecto");
        }else if(unidad.getAnio().isEmpty()){
            valido=false;
            validacionnii=false;
            lbAnioFaltante.setText("Campo Año vacio");
        }
        //validacion de conductor
        if(unidad.getIdColaborador()==0){
            valido=false;
            lbConductorFaltante.setText("Campo incompleto");
        }
        //validacion de unidad
        if(unidad.getIdTipoUnidad() == 0){
            valido = false;
            lbTipoUnidadFaltante.setText("Campo incompleto");
        }
        //validacion marca
        if(unidad.getMarca().isEmpty()){
            valido=false;
            lbMarcaFaltante.setText("Campo incompleto");
        }
        //validacion modelo
        if(unidad.getModelo().isEmpty()){
            valido=false;
            lbModeloFaltante.setText("Campo incompleto");
        }
        //validacion año
        if(unidad.getAnio().isEmpty()){
            valido=false;
            lbAnioFaltante.setText("Campo incompleto");
        }
        //validacion vii
        if(unidad.getVin().isEmpty()){
            valido=false;
            validacionnii=false;
            lbVINFaltante.setText("Campo faltante");
        }else if(unidad.getVin().length()<4){
            valido=false;
            validacionnii=false;
            lbVINFaltante.setText("Campo incompleto");
        }
        //validacion nii
        if(validacionnii){
            tfNII.setText(unidad.getAnio() + unidad.getVin().substring(0, 4));
        }
        return valido;
    }
    
    private int obtenerTipoUnidad(Integer idTipoUnidad){
        for (int i = 0; i < tipos.size(); i++) {
            if(idTipoUnidad == tipos.get(i).getIdTipoUnidad()){
                return i;
            }
        }
        return 0;
    }
    
    private int obtenerConductor(Integer idColaborador){
        for (int i = 0; i < conductor.size(); i++) {
            if(idColaborador == conductor.get(i).getIdColaborador()){
                return i;
            }
        }
        return 0;
    }
}
