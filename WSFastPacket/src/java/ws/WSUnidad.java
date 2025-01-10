package ws;

import com.google.gson.Gson;
import dominio.ImpUnidad;
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
import javax.ws.rs.core.MediaType;
import pojo.Mensaje;
import pojo.Unidad;

/**
 *
 * @author Jossellin
 */
@Path("unidad")
public class WSUnidad {
    
    //WS para obtener todas las unidades que no esten dadas de baja
    @Path("obtenerUnidades")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> obtenerUnidades(){
        return ImpUnidad.obtenerUnidades();
    }
    
   //WS para registrar una nueva unidad 
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
               return new Mensaje(true, "Existen campos vacios o incorrectos, se impidio registrar la unidad");
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new BadRequestException();
        }
    }
    
    //WS para buscar una unidad por su NII
    @Path("buscarUnidadNII/{nii}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> buscarUnidadNII(@PathParam("nii") String nii){
        return ImpUnidad.buscarUnidadPorNII(nii);
    }
    
    //WS para listar las unidades existentes
    @Path("listaUnidad")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> listaUnidad(){
        return ImpUnidad.listarUnidades();
    }
    
    //WS para editar una unidad existente
    @Path("editarUnidad")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarUnidad(String jsonUnidad){
        try{
            Gson gson= new Gson();
            Unidad unidad=gson.fromJson(jsonUnidad, Unidad.class);
            if(unidad.getIdUnidad()!=null){
              return ImpUnidad.editarUnidad(unidad);
            }else{
                return new Mensaje(true, "ID de la unidad vacio o incorrecto para actualizarlo");
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new BadRequestException();
        }
    }
    
    //WS para dar de baja una unidad (no la eliminara de la base de datos)
    @Path("darBaja")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje darBaja(String jsonUnidad){
        try{
            Gson gson= new Gson();
            Unidad unidad=gson.fromJson(jsonUnidad, Unidad.class);
            if(unidad.getIdUnidad()!=null){
              return ImpUnidad.darBaja(unidad);
            }else{
                return new Mensaje(true, "ID de la unidad es vacio o incorrecto para actualizarlo");
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new BadRequestException();
        }
    }
}

