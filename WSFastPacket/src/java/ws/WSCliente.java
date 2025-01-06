package ws;

import com.google.gson.Gson;
import dominio.ImpCliente;
import dominio.ImpColaborador;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Cliente;
import pojo.ListaClientes;
import pojo.Mensaje;

/**
 *
 * @author Jossellin
 */
@Path("cliente")
public class WSCliente {
    @Context
    private UriInfo context;

    public WSCliente() {
    }
    
    //ws registrar cliente
    @Path("registrarCliente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarCliente(String jsonCliente){
        try{
            Gson gson = new Gson();
            Cliente cliente = gson.fromJson(jsonCliente, Cliente.class);
        if(cliente.getCorreo() !=  null && !cliente.getCorreo().isEmpty()){
            return ImpCliente.registrarCliente(cliente);
        }else{
            return new Mensaje (true,"Datos faltantes o incorrectos");
        }
        }catch(Exception e){
            e.printStackTrace();
            throw new BadRequestException();
        }
    }
    
    //WS editar cliente
    @Path("editarCliente")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarCliente(String jsonCliente){
        try{
            Gson gson= new Gson();
            Cliente cliente=gson.fromJson(jsonCliente, Cliente.class);
            if(cliente.getIdCliente()!=null){
              return ImpCliente.editarCliente(cliente);
            }else{
                return new Mensaje(true, "ID de cliente vacio o incorrecto para actualizarlo");
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new BadRequestException();
        }
    }
    
    //WS eliminar cliente
    @Path("eliminarCliente/{idCliente}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarCliente(@PathParam("idCliente") Integer idCliente){
        if(idCliente != null && idCliente <= 10){
            return ImpCliente.eliminarCliente(idCliente);
        }
        throw new BadRequestException();
    }
    
    //WS buscar cliente por correo
    @Path("buscarClientePorCorreo/{correo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Cliente> buscarClientePorCorreo(@PathParam("correo") String correo){
        return ImpCliente.buscarClientePorCorreo(correo);   
    }
    
    @Path("obtenerClientes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> obtenerColaboradores(){
        return ImpCliente.obtenerCliente();
    }
    
    @Path("obtenerListaClientes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ListaClientes> obtenerListaClientes(){
        return ImpCliente.obtenerListaClientes();
    }
    
}
