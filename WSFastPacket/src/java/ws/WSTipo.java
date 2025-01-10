package ws;

import dominio.ImpTipos;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Estatus;
import pojo.RolEmpleado;
import pojo.TipoUnidad;

@Path("tipo")
public class WSTipo {
    
    //WS para el catalogo de los tipos de roles de los colaboradores
    @Path("obtener-roles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RolEmpleado> obtenerRoles(){
        return ImpTipos.obtenerRoles();
    }
    
    //WS para el catalogo de los tipos de unidades
    @Path("obtener-tipos-vehiculos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoUnidad> obtenerUnidades(){
        return ImpTipos.obtenerUnidades();
    }
    
    //WS para el catalogo de los estados del estatus
    @Path("obtener-estatus")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estatus> obtenerEstatus(){
        return ImpTipos.obtenerEstatus();
    }
    
}
