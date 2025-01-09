package dominio;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Mensaje;
import pojo.Unidad;

public class ImpUnidad {
    //obtener unidades
    public static List<Unidad> obtenerUnidades(){
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        List<Unidad> unidad = conexionBD.selectList("unidad.obtenerUnidades");
        return unidad;
    }
    
    public static Mensaje agregarUnidad(Unidad unidad){

    Mensaje msj = new Mensaje();
    SqlSession conexionBD = MyBatisUtil.obtenerConexion();

       if(conexionBD!=null){
        try{
            int filasAfectadas=conexionBD.insert("unidad.registrarUnidad", unidad);
            conexionBD.commit();
            if(filasAfectadas>0){
                msj.setError(false);
                msj.setMensaje("La unidad " + unidad.getMarca() + ", fue resgistrado con éxito.");
            }else{
                msj.setError(true);
                msj.setMensaje("La unidad no pudo ser registrada");
            }
        }catch (Exception e){
            msj.setError(true);
            msj.setMensaje(e.getMessage());
        }
    }else{
        msj.setError(true);
        msj.setMensaje("Por le momento el servicio de unidad no esta disponible.");
    }
    return msj;

}
    
public static Mensaje eliminarUnidad(String idUnidad) {
    Mensaje respuesta = new Mensaje();
    SqlSession conexionBD = MyBatisUtil.obtenerConexion();
    if(conexionBD != null){
        try {
            HashMap<String,String> parametros = new LinkedHashMap<>();
            parametros.put("idUnidad", idUnidad);
            int editado = conexionBD.delete("unidad.eliminar", parametros);
            conexionBD.commit();
            if(editado > 0){
                respuesta.setError(false);
                respuesta.setMensaje("Unidad eliminado");
            }else {
                respuesta.setError(true);
                respuesta.setMensaje("No se encontró ninguna unidad con ese N° de unidad");

            }

        } catch (Exception e){  
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
        }
    }else{
                respuesta.setError(true);
                respuesta.setMensaje("Por el momento no se puede eliminar la información.");
    }
    return respuesta;
    }

    public static List<Unidad> mostrarUnidad(){
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        List<Unidad> unidades = conexionBD.selectList("unidad.obtenerUnidades");
        return unidades;
    }
    
    public static List<Unidad> buscarUnidadPorNII(String nii){
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        List<Unidad> unidad = conexionBD.selectList("unidad.obtenerUnidadesPorNII", nii);
        return unidad;
    }
    
    public static List<Unidad> listarUnidades(){
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        List<Unidad> unidad = conexionBD.selectList("unidad.obtenerListaUnidades");
        return unidad;
    }
    
    public static Mensaje editarUnidad(Unidad unidad){
        Mensaje msj= new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD!=null){
            try {
                int filasAfectadas= conexionBD.update("unidad.actualizarUnidad", unidad);
                conexionBD.commit();
                if(filasAfectadas>0){
                    msj.setError(false);
                    msj.setMensaje("La unidad "+unidad.getMarca()+ " fue actualizado con exito.");
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
    public static Mensaje darBaja(Unidad unidad){
        Mensaje msj= new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD!=null){
            try {
                int filasAfectadas= conexionBD.update("unidad.darBaja", unidad);
                conexionBD.commit();
                if(filasAfectadas>0){
                    msj.setError(false);
                    msj.setMensaje("La unidad fue dada de baja con exito.");
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
