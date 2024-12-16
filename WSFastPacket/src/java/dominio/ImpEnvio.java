/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Envio;
import pojo.Mensaje;


/**
 *
 * @author Jossellin
 */
public class ImpEnvio {
    //obtener envios
    public static List<Envio> obtenerEnvios(){
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        List<Envio> envio = conexionBD.selectList("envio.obtenerEnvios");
        return envio;
    }
    
    //registrar envio
    public static Mensaje registrarEnvio(Envio envio){
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD!=null){
            try{
                int filasAfectadas=conexionBD.insert("envio.registrarEnvio", envio);
                conexionBD.commit();
                if(filasAfectadas>0){
                    msj.setError(false);
                    msj.setMensaje("El envio fue resgistrado con éxito.");
                }else{
                    msj.setError(true);
                    msj.setMensaje("El envio no pudo ser registrado");
                }
            }catch (Exception e){
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            }
        }else{
            msj.setError(true);
            msj.setMensaje("Por le momento el servicio no esta disponible.");
        }
        return msj;
    }
    
    //actualizar envio
    public static Mensaje actualizarEnvio(Envio envio){
        Mensaje msj= new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD!=null){
            try {
                int filasAfectadas= conexionBD.update("envio.actualizarEnvio", envio);
                conexionBD.commit();
                if(filasAfectadas>0){
                    msj.setError(false);
                    msj.setMensaje("El envio fue actualizado con exito.");
                }
            }catch(Exception e){
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            }
        }else{
            msj.setError(true);
            msj.setMensaje("Por el momento el servicio no esta disponible.");
        }
        return msj;
    }
     
     
     
     
     
     
}
