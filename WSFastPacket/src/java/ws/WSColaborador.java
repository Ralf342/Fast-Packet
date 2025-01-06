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
    
    @Path("registro")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarColaborador(String jsonColaborador){
        try{
        Gson gson = new Gson();
        Colaborador colaborador = gson.fromJson(jsonColaborador, Colaborador.class);
        if(colaborador.getNoPersonal() !=null && !colaborador.getNoPersonal().toString().isEmpty()
                && colaborador.getContrasenia() != null && !colaborador.getContrasenia().isEmpty()){
            return ImpColaborador.agregarColaborador(colaborador);
        }else{
            return new Mensaje (true,"Numero de personal y/O contraseña faltante o incorrectos");
        }
        }catch(Exception e){
            e.printStackTrace();
            throw new BadRequestException();
        }
      }
    
    @Path("eliminarColaborador/{idColaborador}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarColaborador(@PathParam("idColaborador") String idColaborador){
        if((idColaborador != null && !idColaborador.isEmpty()) && idColaborador.length() <= 10){
            return ImpColaborador.eliminarColaborador(idColaborador);
        }
        throw new BadRequestException();
    }
    
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
    
    @Path("obtenerColaboradores")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerColaboradores(){
        return ImpColaborador.obtenerColaboradores();
    }
    
    @Path("buscarNoPersonalColaborador/{noPersonal}")
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public  List<Colaborador> buscarNombreColaborador(@PathParam("noPersonal") String noPersonal){
        return ImpColaborador.buscarNoPersonalColaborador(noPersonal);
    }
    
    @Path("subirFoto/{idColaborador}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje subirFoto(@PathParam("idColaborador")Integer idColaborador, byte[] foto){
        if(idColaborador != null && idColaborador > 0 && foto.length > 0){
            return ImpColaborador.guardarFoto(idColaborador, foto);
        }
        throw new BadRequestException();
    }
    
    @Path("obtenerFoto/{idColaborador}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Colaborador obtenerFoto(@PathParam ("idColaborador") Integer idColaborador){
        if(idColaborador != null && idColaborador >0){
            return ImpColaborador.obtenerFoto(idColaborador);
        }
        throw new BadRequestException();
    }
    
    @Path("obtener-conductores")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerConductores(){
        return ImpColaborador.obtenerConductores();
    
    }
}
