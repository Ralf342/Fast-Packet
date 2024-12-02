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
import pojo.Colaborador;
import pojo.LoginColaborador;
import pojo.Mensaje;

/**
 *
 * @author Jossellin
 */
public class ImpColaborador {
    
    /*Editar colaborador
    hecho por Jossellin Herrera*/
    public static Mensaje editarColaborador(Colaborador colaborador){
     
        Mensaje msj = new Mensaje();   
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        
        if(conexionBD != null){
            try{
                
                //validaciones para campos que no se editan
                // Obtener datos actuales del colaborador desde la base de datos
                Colaborador colaboradorExistente = conexionBD.selectOne("colaborador.obtenerColaboradorPorId", colaborador.getIdColaborador());
                if (colaboradorExistente == null) {
                    msj.setError(true);
                    msj.setMensaje("El colaborador no existe en el sistema.");
                    return msj;
                }
                // Validar que no se modifiquen noPersonal e idRol
                if (!colaboradorExistente.getNoPersonal().equals(colaborador.getNoPersonal())) {
                    msj.setError(true);
                    msj.setMensaje("No está permitido modificar el número de personal.");
                    return msj;
                }

                if (!colaboradorExistente.getIdRol().equals(colaborador.getIdRol())) {
                    msj.setError(true);
                    msj.setMensaje("No está permitido modificar el rol del colaborador.");
                    return msj;
                }
                
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
    
    public static LoginColaborador buscarNombreColaborador(String nombre){
        
        LoginColaborador respuesta = new LoginColaborador();
        
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD != null){
            try{
                HashMap<String,String> parametros = new LinkedHashMap<>();
                parametros.put("nombre",nombre);
                Colaborador colaborador = conexionBD.selectOne("colaborador.buscarNombreColaborador",parametros);
                if(colaborador != null){
                    respuesta.setError(false);
                    respuesta.setMensaje("Datos del colaborador: " +colaborador.getNombre());
                    respuesta.setColaborador(colaborador);
                }else{
                    respuesta.setError(true);
                    respuesta.setMensaje("Nombre de colaborador incorrecto");
                }
            }catch(Exception e){
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
               //intStackTrace();
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje("Por el momento no se puede consultar la información.");
        }
        return respuesta;
    }
    
}
