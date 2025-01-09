package dominio;

import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
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
    
     
    //consultare envio info de clientes, origen y destino
    public static List<Envio> obtenerEnviosInfo(Integer numeroDeGuia ){
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
            List<Envio> envio = conexionBD.selectList("envio.obtenerEnviosInfo", numeroDeGuia);
        return envio;
    }
    
    
    //actualizar status
    public static Mensaje actualizarEnvio(Envio envio){
        Mensaje msj= new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD!=null){
            try {
                int filasAfectadas= conexionBD.update("envio.actualizarEnvio", envio);
                conexionBD.commit();
                if(filasAfectadas>0){
                    msj.setError(false);
                    msj.setMensaje("El paquete fue actualizado con exito.");
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
     
    //actualizar estatus envio
    public static Mensaje actualizarEstatusEnvio(Envio envio){
        Mensaje msj= new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD!=null){
            try {
                int filasAfectadas= conexionBD.update("envio.actualizarEstatusEnvio", envio);
                conexionBD.commit();
                if(filasAfectadas>0){
                    msj.setError(false);
                    msj.setMensaje("El estatus fue actualizado con exito.");
                }else{
                    msj.setError(true);
                    msj.setMensaje("No se encontró el envío con ese número de guía para actualizar.");
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
    
    public static List<Envio> buscarEnvioPorNumeroGuia(Integer numeroGuia){
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
            List<Envio> envio = conexionBD.selectList("envio.obtenerEnviosPorNumeroGuia", numeroGuia);
        return envio;
    }
    
    public static List<Envio> obtenerNumeroDeGuia(){
        List<Envio> numeros = null;
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD !=null){
            try{
                numeros = conexionBD.selectList("envio.obtenerNumerosDeGuia");
            
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return numeros;
    }
    
    //movil
    //lista de envíos asignados
    public static List<Envio> obtenerListaEnviosAsignados() {
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        List<Envio> listaEnvios = null;
        if (conexionBD != null) {
            try {
                listaEnvios = conexionBD.selectList("envio.listaEnviosAsignados");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return listaEnvios;
    }

    // detalles de los envíos
    public static List<Envio> obtenerDetalleEnvios(Integer numeroDeGuia) {
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        List<Envio> detallesEnvios = null;
        if (conexionBD != null) {
            try {
                detallesEnvios = conexionBD.selectList("envio.detalleEnvios", numeroDeGuia);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return detallesEnvios;
    }
    
    public static List<Envio> obtenerEnviosConductores(Integer idColaborador){
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
            List<Envio> envio = conexionBD.selectList("envio.obtenerEnviosConductores", idColaborador);
        return envio;
    }
}
