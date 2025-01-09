package fastpacketfx;

import fastpacketfx.interfaces.INotificadorOperacion;
import fastpacketfx.pojo.Colaborador;
import fastpacketfx.pojo.Login;
import fastpacketfx.utilidades.Utilidades;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLMenuPrincipalController implements Initializable {

    private INotificadorOperacion observador;
    private Login login;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    @FXML
    private Button btn_Empleado;
    @FXML
    private Button btn_Unidades;
    @FXML
    private Button btn_clientes;
    @FXML
    private Button btn_paquetes;
    @FXML
    private Button btn_envios;
    @FXML
    private Pane pn_EscenarioUno;
    @FXML
    private Label lbFecha;
    @FXML
    private Label lbHora;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    LocalDateTime now = LocalDateTime.now();
                    lbFecha.setText(now.format(dateFormatter));
                    lbHora.setText(now.format(timeFormatter));
                });
            }
        }, 0, 1000); // Actualización cada segundo
    }

    public void inicializarValores(Login login){
        this.login = login;
    }

    @FXML
    private void onClickEmpleado(ActionEvent event) {
        cargarEscenaEmpleado(login);
    }

    @FXML
    private void onClickUnidad(ActionEvent event) {
        cargarEscenaUnidad();
    }

    @FXML
    private void onClickCliente(ActionEvent event) {
        cargarEscenaCliente();
    }

    @FXML
    private void onClickPaquete(ActionEvent event) {
        cargarEscenaPaquete();
    }

    @FXML
    private void onClickEnvio(ActionEvent event) {
        cargarEscenaEnvio(login);
        System.out.println(login.getColaborador().getNoPersonal());
    }
    
    private void cargarEscenaEmpleado(Login login){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXMLEscenarioEmpleados.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            pn_EscenarioUno.getChildren().clear();
            pn_EscenarioUno.getChildren().add(pane);
            FXMLEscenarioEmpleadosController e = loader.getController();
            e.inicializarValores(login);
        } catch (Exception e) {
        }
    }
    
    private void cargarEscenaUnidad(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXMLEscenarioUnidades.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            pn_EscenarioUno.getChildren().clear();
            pn_EscenarioUno.getChildren().add(pane);
            FXMLEscenarioUnidadesController u = loader.getController();
        } catch (Exception e) {
        }
    }
    
    private void cargarEscenaCliente(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXMLEscenarioClientes.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            pn_EscenarioUno.getChildren().clear();
            pn_EscenarioUno.getChildren().add(pane);
            FXMLEscenarioClientesController c = loader.getController();
        } catch (Exception e) {
        }
    }
    
    private void cargarEscenaPaquete(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXMLEscenaPaquete.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            pn_EscenarioUno.getChildren().clear();
            pn_EscenarioUno.getChildren().add(pane);
            FXMLEscenaPaqueteController p = loader.getController();
        } catch (Exception e) {
        }
    }
    
    private void cargarEscenaEnvio(Login login){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FXMLEscenarioEnvio.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            pn_EscenarioUno.getChildren().clear();
            pn_EscenarioUno.getChildren().add(pane);
            FXMLEscenarioEnvioController ee = loader.getController();
            ee.inicializarValores(login);
        } catch (Exception e) {
        }
    }
    
    private void cerrarVentana(){
        Stage escenario = (Stage)btn_Empleado.getScene().getWindow();
        escenario.close();
        ( (Stage) btn_Empleado.getScene().getWindow()).close();
    }
    

    @FXML
    private void onClickSalir(ActionEvent event) {
        boolean seElimina= Utilidades.mostrarAlertaConfirmacion("Cerrar sesión", "¿Estas seguro de que quieres cerrar sesión?");
        if(seElimina){
            try {
            // Cargar el nuevo escenario desde el archivo FXML
            Stage escenario = new Stage();
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("FXMLInicioSesion.fxml"));
            Parent vista = cargador.load();
            
            // Configurar y mostrar el nuevo escenario
            escenario.setTitle("Inicio Sesion");
            FXMLInicioSesionController controlador = cargador.getController();
            Scene escenaFormulario = new Scene(vista);
            escenario.setScene(escenaFormulario);
            cerrarVentana();
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            }
        }
    }

}
