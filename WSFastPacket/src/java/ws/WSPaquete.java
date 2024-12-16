/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dominio.ImpPaquete;
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
import pojo.Mensaje;
import pojo.Paquete;

/**
 *
 * @author Jossellin
 */
@Path("paquete")
public class WSPaquete {

    @Context
    private UriInfo context;
    
    public WSPaquete() {
    }
    
    //obtener paquetes
    @Path("obtenerPaquetes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> obtenerPaquetes(){
        return ImpPaquete.obtenerPaquetes();
    }
    
    //WS registrar paquete
    @Path("registrarPaquete")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarPaquete(String jsonPaquete){
        try{
            Gson gson = new Gson();
            Paquete paquete = gson.fromJson(jsonPaquete, Paquete.class);
            
        if(paquete.getPeso() > 0 && paquete.getDimensiones() > 0 &&
           paquete.getDescripcion() != null && !paquete.getDescripcion().isEmpty() &&
           paquete.getNumeroDeGuia() != null && paquete.getIdUnidad() != null
        ){
            return ImpPaquete.registrarPaquete(paquete);
        }else{
            return new Mensaje (true,"Datos faltantes o incorrectos");
        }
        }catch(Exception e){
            e.printStackTrace();
            throw new BadRequestException();
        }
    }
    
    
    //WS consultar paquete por envio
    @Path("consultarPaquetePorEnvio/{numeroDeGuia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Paquete> consultarPaquetePorEnvio(@PathParam("numeroDeGuia") Integer numeroDeGuia){
        return ImpPaquete.consultarPaquetePorEnvio(numeroDeGuia);   
    }

    //WS actualizar paquete
    @Path("actualizarPaquete")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje actualizarPaquete(String jsonPaquete){
        try{
            Gson gson= new Gson();
            Paquete paquete = gson.fromJson(jsonPaquete, Paquete.class);
            if(paquete.getIdPaquete()!=null){
              return ImpPaquete.actualizarPaquete(paquete);
            }else{
                return new Mensaje(true, "ID del paquete es vacio o incorrecto para actualizarlo");
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new BadRequestException();
        }
    }
    
    
    //WS eliminar paquete
    @Path("eliminarPaquete/{idPaquete}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarPaquete(@PathParam("idPaquete") Integer idPaquete){
        if(idPaquete != null && idPaquete <= 10){
            return ImpPaquete.eliminarPaquete(idPaquete);
        }
        throw new BadRequestException();
    }
}
