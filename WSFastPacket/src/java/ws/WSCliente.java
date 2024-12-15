/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dominio.ImpCliente;
import dominio.ImpColaborador;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Cliente;
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
            if(cliente.getNoPersonal()!=null && !cliente.getNoPersonal().isEmpty() 
                    && cliente.getContrasenia() != null && !cliente.getContrasenia().isEmpty()){
                return ImpCliente.registrarCliente(cliente);
            }else{
               return new Mensaje(true, "Datos faltantes o incorrectos");
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
    public Mensaje editarCliente(String jsonCliente) {
        try {
            Gson gson = new Gson();
            Cliente cliente = gson.fromJson(jsonCliente, Cliente.class);
            return ImpCliente.editarCliente(cliente);
        
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }
}
