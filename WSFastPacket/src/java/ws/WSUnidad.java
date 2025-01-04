/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dominio.ImpUnidad;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Mensaje;
import pojo.Unidad;

/**
 *
 * @author Jossellin
 */
@Path("unidad")
public class WSUnidad {
    
    @Path("obtenerUnidades")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> obtenerUnidades(){
        return ImpUnidad.obtenerUnidades();
    }
    
   @Path("registrarUnidad")
   @POST
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarUnidad(String jsonColaborador){
        try{
            Gson gson = new Gson();
            Unidad unidad = gson.fromJson(jsonColaborador, Unidad.class);
            if(unidad.getVin()!= null && !unidad.getVin().isEmpty()
                    && unidad.getNii() != null && !unidad.getNii().isEmpty()
                    && unidad.getModelo() != null && !unidad.getModelo().isEmpty()
                    && unidad.getMarca() != null && !unidad.getMarca().isEmpty()
                    && unidad.getIdTipoUnidad() != null && unidad.getIdTipoUnidad() != 0
            ){
                return ImpUnidad.agregarUnidad(unidad);
            }else{
               return new Mensaje(true, "Existen campos vacios o incorrectos");
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new BadRequestException();
        }
    }
    
    @Path("eliminarUnidad/{idUnidad}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarColaborador(@PathParam("idUnidad") String idUnidad){
        
        if((idUnidad != null && !idUnidad.isEmpty()) && idUnidad.length() <= 10){
            return ImpUnidad.eliminarUnidad(idUnidad);
        }
        throw new BadRequestException();
        
    }
    
    @Path("mostrarUnidades")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> mostrarUnidades(){
        return ImpUnidad.mostrarUnidad();
    }
    
    @Path("buscarUnidadNII/{nii}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> buscarUnidadNII(@PathParam("nii") String nii){
        return ImpUnidad.buscarUnidadPorNII(nii);
    }
}

