package dominio;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import pojo.LoginColaborador;
import pojo.Mensaje;

/**
 *
 * @author joska_
 */
public class ImpColaborador {
    public static Mensaje agregarColaborador(Colaborador colaborador){

    Mensaje msj = new Mensaje();
    SqlSession conexionBD = MyBatisUtil.obtenerConexion();

       if(conexionBD!=null){
        try{
            int filasAfectadas=conexionBD.insert("colaborador.registro", colaborador);
            conexionBD.commit();
            if(filasAfectadas>0){
                msj.setError(false);
                msj.setMensaje("El colaborador " + colaborador.getNombre() + " " +colaborador.getApellidoPaterno() + " " + colaborador.getApellidoMaterno() +", fue resgistrado con éxito.");
            }else{
                msj.setError(true);
                msj.setMensaje("El colaborador no pudo ser registrado");
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
        
    public static Mensaje eliminarColaborador(String idColaborador) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD != null){
            try {
                HashMap<String,String> parametros = new LinkedHashMap<>();
                parametros.put("idColaborador", idColaborador);
                int editado = conexionBD.delete("colaborador.eliminar", parametros);
                conexionBD.commit();
                if(editado > 0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Colaborador eliminado");
                }else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se encontró ningun colaborador con ese N° de Personal");
                }
            }catch (Exception e){  
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje("Por el momento no se puede eliminar la información.");
        }
        return respuesta;
    }
    
    public static Mensaje editarColaborador(Colaborador colaborador){
     
        Mensaje msj = new Mensaje();   
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if(conexionBD != null){
            try{
                
                //validaciones para campos que no se editan
                // Obtener datos actuales del colaborador desde la base de datos
               
                
                //las otras validaciones  ala bd
                int resultado = conexionBD.update("colaborador.editar", colaborador);
                conexionBD.commit();
                if(resultado > 0){
                    msj.setError(false);
                    msj.setMensaje("Colaborador(a) actualizado con exito.");
                }else{
                    msj.setError(true);
                    msj.setMensaje("No se pudo actualizar al colaborador(a), inténtelo más tarde.");
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
    
    public static List<Colaborador> obtenerColaboradores(){
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
                List<Colaborador> colaboradores = conexionBD.selectList("colaborador.obtenerColaboradores");
        return colaboradores;
    }
    
    public static List<Colaborador> buscarNoPersonalColaborador(String noPersonal){
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
            List<Colaborador> colaborador = conexionBD.selectList("colaborador.obtenerColaboradorPorNoPersonal", noPersonal);
            return colaborador;    
    }
    
    public static Mensaje guardarFoto(Integer idColaborador, byte [] foto){
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD != null){
            try{
                HashMap<String, Object> parametros = new HashMap<>();
                parametros.put("idColaborador", idColaborador);
                parametros.put("foto", foto);
                int filasAfectadas = conexionBD.update("colaborador.guardarFoto", parametros);
                conexionBD.commit();
                if(filasAfectadas > 0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Fotografia guardada correctamente");
                }else{
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, no se pudo guardar la foto");
                }
                conexionBD.close();
            }catch (Exception e){
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }
            
        }else{
            respuesta.setError(true);
            respuesta.setMensaje("Lo sentimos, no se pudo almacenar a la Base de Datos");
        }
        return  respuesta;
    }
    
    public static Colaborador obtenerFoto(Integer idColaborador){
        Colaborador colaborador = null;
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD !=null){
            try{
                colaborador = conexionBD.selectOne("colaborador.obtenerFoto", idColaborador);
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return colaborador;
    }
    
    public static List<Colaborador> obtenerConductores(){
        List<Colaborador> conductores = null;
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD !=null){
            try{
                conductores = conexionBD.selectList("colaborador.obtenerConductores");
            
            }catch (Exception e){
                e.printStackTrace();
            }
        } 
        return conductores;
    }
}
