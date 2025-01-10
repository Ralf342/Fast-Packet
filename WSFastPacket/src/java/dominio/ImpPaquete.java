package dominio;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Mensaje;
import pojo.Paquete;

/**
 *
 * @author Jossellin
 */
public class ImpPaquete {
    
    //Implementacion para obtener todos los paquetes
    public static List<Paquete> obtenerPaquetes(){
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        List<Paquete> paquete = conexionBD.selectList("paquete.obtenerPaquetes");
        return paquete;
    }
    
    //Implementacion para registrar un nuevo paquete
    public static Mensaje registrarPaquete(Paquete paquete){
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

        if(conexionBD!=null){
            try{
                int filasAfectadas=conexionBD.insert("paquete.registrarPaquete", paquete);
                conexionBD.commit();
                if(filasAfectadas>0){
                    msj.setError(false);
                    msj.setMensaje("El paquete con numero de guia: " + paquete.getNumeroDeGuia()+", fue resgistrado con éxito.");
                }else{
                    msj.setError(true);
                    msj.setMensaje("El paquete no pudo ser registrado");
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
    
    //Implementacion para actualizar un paquete existente
    public static Mensaje actualizarPaquete(Paquete paquete){
        Mensaje msj= new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();  
        
        if(conexionBD!=null){
            try {
                int filasAfectadas= conexionBD.update("paquete.actualizarPaquete", paquete);
                conexionBD.commit();
                if(filasAfectadas>0){
                    msj.setError(false);
                    msj.setMensaje("El paquete con numero de guia: "+ paquete.getNumeroDeGuia()+ ", fue actualizado con exito.");
                }else{
                    msj.setError(true);
                    msj.setMensaje("El Paquete no pudo ser actualizado");
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
    
    //Implementacion para eliminar un paquete
    public static Mensaje eliminarPaquete(Integer idPaquete){
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD!=null){
            try{
                int filasAfectadas=conexionBD.delete("paquete.eliminarPaquete", idPaquete);
                conexionBD.commit();
                if(filasAfectadas>0){
                    msj.setError(false);
                    msj.setMensaje("El paquete ha sido borrado.");
                }else{
                    msj.setError(true);
                    msj.setMensaje("El paquete no pudo ser borrado");
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
    
    //Implementacion para poder consultar un paquete por su numero de envio
    public static List<Paquete> consultarPaquetePorEnvio(Integer numeroDeGuia){
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
            List<Paquete> paquete = conexionBD.selectList("paquete.consultarPaquetePorEnvio", numeroDeGuia);
        return paquete;
    }
}
