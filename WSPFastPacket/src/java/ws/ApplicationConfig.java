/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.Set;
import javafx.application.Application;
import javafx.stage.Stage;
//OIGAN
//tengo un detalle aqui, lo checo, si algo pasa puede surgir de aca
import javax.ws.rs.*;

/**
 *
 * @author Jossellin
 */

//falta lo de API
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application{

    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ws.WSColaborador.class);
        resources.add(ws.WSUnidad.class);
        resources.add(ws.WsLogin.class);
    }

    
    
}
