package ws;

import com.google.gson.Gson;
import dominio.ImpColaborador;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import pojo.Colaborador;
import pojo.Mensaje;

@Path("colaborador")
public class WSColaborador {
    
    @Context
    private UriInfo context;
    
    public WSColaborador(){
    }
    
    //WS para obtener a todos los colaboradores
    @Path("obtenerColaboradores")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerColaboradores(){
        return ImpColaborador.obtenerColaboradores();
    }
    
    //WS para registrar un nuevo colaborador
    @Path("registro")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarColaborador(String jsonColaborador){
        try{
        Gson gson = new Gson();
        Colaborador colaborador = gson.fromJson(jsonColaborador, Colaborador.class);
        if(colaborador.getNoPersonal() !=null && !colaborador.getNoPersonal().toString().isEmpty()
                && colaborador.getContrasenia() != null && !colaborador.getContrasenia().isEmpty() 
                && colaborador.getIdRol()!=null){
            return ImpColaborador.agregarColaborador(colaborador);
        }else{
            return new Mensaje (true,"Numero de personal, contraseña o Rol faltante o incorrectos");
        }
        }catch(Exception e){
            e.printStackTrace();
            throw new BadRequestException();
        }
      }
    
    //WS para eliminar un colaborador
    @Path("eliminarColaborador/{idColaborador}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarColaborador(@PathParam("idColaborador") String idColaborador){
        if((idColaborador != null && !idColaborador.isEmpty())){
            return ImpColaborador.eliminarColaborador(idColaborador);
        }
        throw new BadRequestException();
    }
    
    //WS para editar un colaborador existente
    @Path("editarColaborador")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarColaborador(String jsonColaborador){
        try{
            Gson gson = new Gson();
            Colaborador colaborador = gson.fromJson(jsonColaborador, Colaborador.class);
            return ImpColaborador.editarColaborador(colaborador);
        }catch (Exception e){
            System.out.println(e);
            throw new BadRequestException();
        }
    }
    
    //WS para buscar un colaborador por su numero de personal
    @Path("buscarNoPersonalColaborador/{noPersonal}")
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public  List<Colaborador> buscarNombreColaborador(@PathParam("noPersonal") String noPersonal){
        return ImpColaborador.buscarNoPersonalColaborador(noPersonal);
    }
    
    //WS para obtener los colaboradores que poseen el rol de conductores
    @Path("obtener-conductores")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerConductores(){
        return ImpColaborador.obtenerConductores();
    }
    
    //WS para subir la foto de un colaborador
    @Path("subirFoto/{idColaborador}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje subirFoto(@PathParam("idColaborador")Integer idColaborador, byte[] foto){
        if(idColaborador != null && idColaborador > 0 && foto.length > 0){
            return ImpColaborador.guardarFoto(idColaborador, foto);
        }
        throw new BadRequestException();
    }
    
    //WS para obtener la foto de un colaborador
    @Path("obtenerFoto/{idColaborador}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Colaborador obtenerFoto(@PathParam ("idColaborador") Integer idColaborador){
        if(idColaborador != null && idColaborador >0){
            return ImpColaborador.obtenerFoto(idColaborador);
        }
        throw new BadRequestException();
    }
    
}
