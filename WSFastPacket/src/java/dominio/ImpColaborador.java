package dominio;

import java.util.HashMap;
import java.util.LinkedHashMap;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
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
}
