/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import pojo.LoginColaborador;
import pojo.Mensaje;

/**
 *
 * @author Jossellin
 */

@Path("colaboradores")
public class WSColaborador {
    
    @Context
    private UriInfo context;
    
    public WSColaborador(){
    }
    
    
    /*editarColaborador de tipo PUT
    /Hecho por Jossellin Herrera Rodriguez*/
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
    
     @Path("obtenerColaboradores")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerColaboradores(){
        return ImpColaborador.obtenerColaboradores();
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
    
    /*buscarNombreColaborador
    Hecho por Jossellin Herrera Rodriguez*/
    @Path("buscarNombreColaborador/{nombre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public LoginColaborador buscarNombreColaborador(@PathParam("nombre") String nombre){
        // TODO Validaciones
        if((nombre !=null && !nombre.isEmpty()) && nombre.length() <=10){
            return ImpColaborador.buscarNombreColaborador(nombre);
        }
        throw new BadRequestException();
    }
    
    /*buscarRolColaborador 
    Hecho por Jossellin Herrera Rodriguez*/
    @Path("buscarRolColaborador/{rol}")
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    //checar implementacion
    public LoginColaborador buscarRolColaborador(@PathParam("rol") String nombre){
        // TODO Validaciones
        if((nombre !=null && !nombre.isEmpty()) && nombre.length() <=10){
            return ImpColaborador.buscarNombreColaborador(nombre);
        }
        throw new BadRequestException();
    }
    
}
