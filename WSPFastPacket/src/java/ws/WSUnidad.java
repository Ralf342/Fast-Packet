/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dominio.ImpColaborador;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Mensaje;
import pojo.Unidad;

/**
 *
 * @author Jossellin
 */
@Path("Unidades")
public class WSUnidad {
    
   @Path("registrarUnidad")
   @POST
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarUnidad(String jsonColaborador){
        try{
            Gson gson = new Gson();
            Unidad unidad = gson.fromJson(jsonColaborador, Unidad.class);
            
            if(unidad.getVin() != null && unidad.getVin() != 0 
                    && unidad.getNii() != null && !unidad.getNii().isEmpty()
                    && unidad.getModelo() != null && !unidad.getModelo().isEmpty()
                    && unidad.getMarca() != null && !unidad.getMarca().isEmpty()
                    && unidad.getMotivo() != null && !unidad.getMotivo().isEmpty()
                    && unidad.getIdTipoUnidad() != null && unidad.getIdTipoUnidad() != 0
                    
            ){
                return ImpColaborador.registrarUnidad(unidad);
            }else{
               return new Mensaje(true, "Numero de personal y/o password faltantes o incorrectos");
            }
            
        }catch(Exception e){
            e.printStackTrace();
            throw new BadRequestException();
        }
    }
}
