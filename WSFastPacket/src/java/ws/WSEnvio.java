package ws;

import com.google.gson.Gson;
import dominio.ImpEnvio;
import java.util.List;
import java.util.Map;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import pojo.Envio;
import pojo.Mensaje;


@Path("envio")
public class WSEnvio {
    
    @Context
    private UriInfo context;

    public WSEnvio() {
    }
    
    //WS para obtener todos los envios
    @Path("obtenerEnvios")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Envio> obtenerEnvio(){
        return ImpEnvio.obtenerEnvios();
    }
    
    //WS para registrar un nuevo envio
   @Path("registrarEnvio")
   @POST
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarEnvio(String jsonEnvio){
        try{
            Gson gson = new Gson();
            Envio envio = gson.fromJson(jsonEnvio, Envio.class);
            
            if(envio.getCosto() > 0 &&  
                envio.getCiudadOrigen()!= null && !envio.getCiudadOrigen().isEmpty() &&    
                envio.getEstadoOrigen()!= null && !envio.getEstadoOrigen().isEmpty() &&     
                envio.getCalleOrigen()!= null && !envio.getCalleOrigen().isEmpty() &&       
                envio.getColoniaOrigen()!= null && !envio.getColoniaOrigen().isEmpty() &&   
                envio.getCodigoPostalOrigen()!= null && envio.getIdClienteDestino()!= null
                && envio.getIdEstatus()!= null){
                return ImpEnvio.registrarEnvio(envio);
            }else{
               return new Mensaje(true, "El envio se encuentra con algunos faltantes o incorrectos, no se pudo registrar");
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new BadRequestException();
        }
    }
    
    //WS para obtener los envios por su numero de guia
    @Path("obtenerEnviosPorNumeroGuia/{numeroDeGuia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Envio> obtenerEnviosInfo(@PathParam("numeroDeGuia") Integer numeroDeGuia){
        return ImpEnvio.buscarEnvioPorNumeroGuia(numeroDeGuia);   
    }
    
    //WS para actualizar un envio existente
    @Path("actualizarEnvio")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje actualizarStatusEnvio(String jsonEnvio){
        try{
            Gson gson= new Gson();
            Envio envio = gson.fromJson(jsonEnvio, Envio.class);
            if(envio.getNumeroDeGuia() != null){
              return ImpEnvio.actualizarEnvio(envio);
            }else{
                return new Mensaje(true, "ID del envio es vacio o incorrecto para actualizarlo");
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new BadRequestException();
        }
    }
    
    //WS para actualizar el estatus del envio (Movil)
    @Path("actualizarEstatusEnvio")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje actualizarEstatusEnvio(String jsonEnvio){
        try{
            Gson gson= new Gson();
            Envio envio = gson.fromJson(jsonEnvio, Envio.class);
            if(envio.getNumeroDeGuia() != null && envio.getIdEstatus()!= null && envio.getMotivoModificacion()!= null 
                    && envio.getIdColaboradorModificacion()!=null){
              return ImpEnvio.actualizarEstatusEnvio(envio);
            }else{
                return new Mensaje(true,"El envio no tiene nada de datos: "+envio+"");
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new BadRequestException();
        }
    }
    
    //WS para obtener la informacion de un envio por su numero de guia (Web)
    @Path("buscarEnviosInfo/{numeroDeGuia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Envio> buscarClientePorCorreo(@PathParam("numeroDeGuia") Integer numeroDeGuia){
        return ImpEnvio.obtenerEnviosInfo(numeroDeGuia);   
    }
    
    //WS para obtener los numeros de guia existentes
    @Path("numeroGuia")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Envio> obtenerConductores(){
        return ImpEnvio.obtenerNumeroDeGuia();
    
    }
    
    //WS para movil
    //WS para listar la lista de envios
    @Path("listaEnviosAsignados")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Envio> obtenerListaEnviosAsignados() {
        return ImpEnvio.obtenerListaEnviosAsignados();
    }
    
    //WS para obtener los detalles de los envios 
    @Path("detalleEnvios/{numeroDeGuia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Envio> obtenerDetalleEnvios(@PathParam ("numeroDeGuia") Integer numeroDeGuia) {
        return ImpEnvio.obtenerDetalleEnvios(numeroDeGuia);
    }
    
    //WS para buscar los envios asignados a un conductor
    @Path("buscarEnviosConductores/{idColaborador}")
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Envio> buscarEnviosColaboradores(@PathParam("idColaborador") Integer idColaborador){
        return ImpEnvio.obtenerEnviosConductores(idColaborador);   
    }
    
}
