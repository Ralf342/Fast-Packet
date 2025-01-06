package fastpacketfx.modelo.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fastpacketfx.modelo.ConexionWS;
import fastpacketfx.pojo.Mensaje;
import fastpacketfx.pojo.Paquete;
import fastpacketfx.pojo.RespuestaHTTP;
import fastpacketfx.utilidades.Constantes;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

/**
 *
 * @author edgar
 */
public class PaqueteDAO {
    public static List<Paquete> obtenerPaquetes(){
        List<Paquete> paquetes = null;
        String url = Constantes.URL_wS+"paquete/obtenerPaquetes";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        try{
            if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
                Gson gson = new Gson();
                Type tipoLista = new TypeToken<List<Paquete>>(){}.getType();
                paquetes = gson.fromJson(respuesta.getContenido(), tipoLista);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return paquetes;
    }
    
    public static Mensaje registrarPaquete(Paquete paquete){
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_wS+"paquete/registrarPaquete";
        Gson gson = new Gson();
        try{
            String parametros = gson.toJson(paquete);
            RespuestaHTTP respuesta = ConexionWS.peticionPOSTJson(url, parametros);
            if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
                msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            }else{
                msj.setError(true);
                msj.setMensaje(respuesta.getContenido());
            }
        }catch (Exception e){
           msj.setError(true);
           msj.setMensaje(e.getMessage());
        }
        return msj;
    }
    public static List<Paquete> buscarPaquete(Integer numGuia){
        List<Paquete> paquetes = null;
        String url = Constantes.URL_wS+"paquete/consultarPaquetePorEnvio/"+numGuia;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        try{
            if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
                Gson gson = new Gson();
                Type tipoLista = new TypeToken<List<Paquete>>(){}.getType();
                paquetes = gson.fromJson(respuesta.getContenido(), tipoLista);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return paquetes;
    }
    
    public static Mensaje borrarPaquete(Integer idPaquete){
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_wS+"paquete/eliminarPaquete/" + idPaquete;
        Gson gson = new Gson();
        try{
            RespuestaHTTP respuesta = ConexionWS.peticionDELETE(url,null);
            if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
                System.out.print("Estoy borrando los datos");
                msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            }
        }catch(Exception e){
            msj.setError(true);
            msj.setMensaje(e.getMessage());
        }       
        return msj;
        }
    
    public static Mensaje editarPaquete(Paquete paquete){
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_wS+"paquete/actualizarPaquete";
        Gson gson = new Gson();
        try{
            String parametros = gson.toJson(paquete);
            RespuestaHTTP respuesta = ConexionWS.peticionPUTJson(url, parametros);
            if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
                msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            }else{
                msj.setError(true);
                msj.setMensaje(respuesta.getContenido());
            }
        }catch (Exception e){
           msj.setError(true);
           msj.setMensaje(e.getMessage());
        }
        return msj;
    }
}
