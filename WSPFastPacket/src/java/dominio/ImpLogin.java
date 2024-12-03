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

/**
 *
 * @author joska_
 */
public class ImpLogin {
    public static LoginColaborador validarSesionColaborador(String noPersonal, String contrasenia){
        LoginColaborador respuesta = new LoginColaborador();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD!= null){
            try {
                HashMap<String,Object> parametros = new LinkedHashMap<>();
                parametros.put("noPersonal", noPersonal);
                parametros.put("contrasenia",contrasenia);
             Colaborador colaborador = conexionBD.selectOne("sesion.loginColaborador",parametros);
                if(colaborador!=null){
                  respuesta.setError(false);
                  respuesta.setMensaje("Credenciales correctas del colaborador: "+colaborador.getNombre());
                  respuesta.setColaborador(colaborador);
                }else{
                respuesta.setError(true);
                respuesta.setMensaje("No. de personal y/o password incorrectos");
              } 
   
           }catch(Exception e){
               respuesta.setError(true);
               respuesta.setMensaje(e.getMessage());
           } 
        }else{
            respuesta.setMensaje("Por el momento no hay servicio a la base de datos");
        }
        return respuesta;
    }
}
