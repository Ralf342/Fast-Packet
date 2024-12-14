package ws;

import dominio.ImpLogin;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import javax.ws.rs.core.UriInfo;
import pojo.LoginColaborador;

@Path("Login")
public class WSLogin {
    
    @Context
    private UriInfo context;
    
    public WSLogin(){
        
    }
    
    @Path("ProbarConexion")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public boolean probarConexion(){
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        return (conexionBD!=null);
    }
    
    @Path("colaborador")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public LoginColaborador verificarSesionColaborador(@FormParam("noPersonal") String noPersonal, @FormParam("contrasenia") String contrasenia){
        if((noPersonal !=null && !noPersonal.isEmpty()) && (contrasenia != null && !contrasenia.isEmpty())){
            return ImpLogin.validarSesionColaborador(noPersonal, contrasenia);
        }
        throw new BadRequestException();
    }
}
