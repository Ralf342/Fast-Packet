/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import dominio.ImpLogin;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import pojo.LoginColaborador;

/**
 *
 * @author joska_
 */
@Path("login")
public class WsLogin {
    
    @Context
    private UriInfo context;
    
    @Path("colaborador")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public LoginColaborador verificarSesionColaborador(@FormParam("noPersonal") String noPersonal, @FormParam("contrasenia") String password){
        if((noPersonal !=null && !noPersonal.isEmpty()) && (password != null && !password.isEmpty()) 
           && noPersonal.length()<=10){
            return ImpLogin.validarSesionColaborador(noPersonal, password);
        }
        throw new BadRequestException();
    }
    
}
