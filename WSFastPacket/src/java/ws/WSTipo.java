package ws;

import dominio.ImpTipos;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.RolEmpleado;
import pojo.TipoUnidad;

@Path("tipo")
public class WSTipo {
    
    @Path("obtener-roles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RolEmpleado> obtenerRoles(){
        return ImpTipos.obtenerRoles();
    }
    
    @Path("obtener-tipos-vehiculos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoUnidad> obtenerUnidades(){
        return ImpTipos.obtenerUnidades();
    }
    
}
