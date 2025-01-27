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
    
    //Implementacion para el login de colaboraborador por parte del escritorio
    public static LoginColaborador validarSesionColaborador(String noPersonal, String contrasenia){
        LoginColaborador respuesta = new LoginColaborador();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD!= null){
            try {
                HashMap<String,Object> parametros = new LinkedHashMap<>();
                parametros.put("noPersonal", noPersonal);
                parametros.put("contrasenia",contrasenia);
             Colaborador colaborador = conexionBD.selectOne("login.LoginColaborador",parametros);
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
    
    //Implementacion del login de conductores para la app movil
    public static LoginColaborador validarSesionConductor(String noPersonal, String contrasenia){
        LoginColaborador respuesta = new LoginColaborador();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD!= null){
            try {
                HashMap<String,Object> parametros = new LinkedHashMap<>();
                parametros.put("noPersonal", noPersonal);
                parametros.put("contrasenia",contrasenia);
             Colaborador conductor = conexionBD.selectOne("login.LoginConductor",parametros);
                if(conductor!=null){
                    if(conductor.getIdColaborador()!=3){
                        respuesta.setError(false);
                        respuesta.setMensaje("La aplicación solo puede ser utilizada por conductores");
                    }else{
                        respuesta.setError(false);
                        respuesta.setMensaje("Credenciales correctas del conductor: "+conductor.getNombre());
                        respuesta.setColaborador(conductor);
                    }
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
