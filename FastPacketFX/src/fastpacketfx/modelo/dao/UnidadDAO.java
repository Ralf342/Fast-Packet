package fastpacketfx.modelo.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fastpacketfx.modelo.ConexionWS;
import fastpacketfx.pojo.Colaborador;
import fastpacketfx.pojo.RespuestaHTTP;
import fastpacketfx.pojo.TipoUnidad;
import fastpacketfx.pojo.Unidad;
import fastpacketfx.utilidades.Constantes;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

public class UnidadDAO {
    public static List<Unidad> obtenerUnidades(){
        List<Unidad> colaboradores = null;
        String url = Constantes.URL_wS+"unidad/obtenerUnidades";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        try{
            if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
                Gson gson = new Gson();
                Type tipoLista = new TypeToken<List<Unidad>>(){}.getType();
                colaboradores = gson.fromJson(respuesta.getContenido(), tipoLista);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return colaboradores;
    }
    
    public static List <TipoUnidad> obtenerTiposUnidades(){
          List<TipoUnidad>tipos =null;
          String url = Constantes.URL_wS+"tipo/obtener-tipos-vehiculos";
          RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
          try{
              if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
                  Gson gson = new Gson();
                  Type tipoLista = new TypeToken<List<TipoUnidad>>(){}.getType();
                  tipos =gson.fromJson(respuesta.getContenido(), tipoLista);
              }
          }catch (Exception e){
              e.printStackTrace();
          }
          return tipos;
    }
}
