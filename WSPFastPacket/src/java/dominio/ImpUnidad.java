/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.HashMap;
import java.util.LinkedHashMap;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Mensaje;
import pojo.Unidad;

/**
 *
 * @author Jossellin
 */
public class ImpUnidad {
     public static Mensaje registrarUnidad(Unidad unidad){;
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD != null){
            try{
                int filasAfectadas = conexionBD.insert("unidad.registrarUnidad", unidad);
                conexionBD.commit();
                if(filasAfectadas > 0){
                    msj.setError(false);
                    msj.setMensaje("La unidad" + unidad.getVin()+" "+ unidad.getNii()+" "+unidad.getModelo()+
                            " "+unidad.getMarca()+" "+unidad.getMotivo()+" "+unidad.getModelo()+
                            " "+unidad.getIdTipoUnidad()+" fue registrada exitosamente");
                    
                }else{
                    msj.setError(true);
                    msj.setMensaje("El colaborador no pudo ser registrado");
                }  
            }catch(Exception e){
                msj.setError(true);
               // msj.setMensaje(e.getMessage());
               msj.setMensaje("Por el momento el servicio no esta disponible");
            }
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
     
    
}
