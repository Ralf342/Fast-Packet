package fastpacketfx.modelo.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fastpacketfx.modelo.ConexionWS;
import fastpacketfx.pojo.Envio;
import fastpacketfx.pojo.Estatus;
import fastpacketfx.pojo.Mensaje;
import fastpacketfx.pojo.RespuestaHTTP;
import fastpacketfx.utilidades.Constantes;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

public class EnvioDAO {
    public static List<Envio> obtenerEnvios(){
        List<Envio> clientes = null;
        String url = Constantes.URL_wS+"envio/obtenerEnvios";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        try{
            if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
                Gson gson = new Gson();
                Type tipoLista = new TypeToken<List<Envio>>(){}.getType();
                clientes = gson.fromJson(respuesta.getContenido(), tipoLista);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return clientes;
    }
    
    public static List <Envio> obtenerNumeroGuia(){
          List<Envio>numero =null;
          String url = Constantes.URL_wS+"envio/numeroGuia";
          RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
          try{
              if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
                  Gson gson = new Gson();
                  Type tipoLista = new TypeToken<List<Envio>>(){}.getType();
                  numero = gson.fromJson(respuesta.getContenido(), tipoLista);
              }
          }catch (Exception e){
              e.printStackTrace();
          }
          return numero;
    }
    
    public static List <Estatus> obtenerEstatus(){
          List<Estatus>estatus =null;
          String url = Constantes.URL_wS+"tipo/obtener-estatus";
          RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
          try{
              if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
                  Gson gson = new Gson();
                  Type tipoLista = new TypeToken<List<Estatus>>(){}.getType();
                  estatus =gson.fromJson(respuesta.getContenido(), tipoLista);
              }
          }catch (Exception e){
              e.printStackTrace();
          }
          return estatus;
    }
    
    public static Mensaje registrarEnvio(Envio envio){
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_wS+"envio/registrarEnvio";
        Gson gson = new Gson();
        try{
            String parametros = gson.toJson(envio);
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
    
    public static List<Envio> buscarEnvio(Integer numeroGuia){
        List<Envio> paquetes = null;
        String url = Constantes.URL_wS+"envio/obtenerEnviosPorNumeroGuia/"+numeroGuia;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        try{
            if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
                Gson gson = new Gson();
                Type tipoLista = new TypeToken<List<Envio>>(){}.getType();
                paquetes = gson.fromJson(respuesta.getContenido(), tipoLista);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return paquetes;
    }
    
}
