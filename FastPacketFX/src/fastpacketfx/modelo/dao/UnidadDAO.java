package fastpacketfx.modelo.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fastpacketfx.modelo.ConexionWS;
import fastpacketfx.pojo.Mensaje;
import fastpacketfx.pojo.RespuestaHTTP;
import fastpacketfx.pojo.TipoUnidad;
import fastpacketfx.pojo.Unidad;
import fastpacketfx.utilidades.Constantes;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

public class UnidadDAO {
    
    //Implementacion para mostrar todas las unidades que no se han dado de baja
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
    
    //Implemenacion para mostrar el catalogo de los tipos de unidades que existen
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
    
    //Implementacion para registrar una nueva unidad
    public static Mensaje registrarUnidad(Unidad unidad){
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_wS+"unidad/registrarUnidad";
        Gson gson = new Gson();
        try{
            String parametros = gson.toJson(unidad);
            System.out.println("JSON Enviado: " + parametros);
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
    
    //Implementacion para listar las unidades disponibles para un envio
    public static List <Unidad> listarUnidades(){
          List<Unidad>tipos =null;
          String url = Constantes.URL_wS+"unidad/listaUnidad";
          RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
          try{
              if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
                  Gson gson = new Gson();
                  Type tipoLista = new TypeToken<List<Unidad>>(){}.getType();
                  tipos =gson.fromJson(respuesta.getContenido(), tipoLista);
              }
          }catch (Exception e){
              e.printStackTrace();
          }
          return tipos;
    }
    
    //Implementacion para buscar una unidad por su NII
    public static List<Unidad> buscarUnidad(String nii){
        List<Unidad> unidades = null;
        String url = Constantes.URL_wS+"unidad/buscarUnidadNII/"+nii;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        try{
            if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
                Gson gson = new Gson();
                Type tipoLista = new TypeToken<List<Unidad>>(){}.getType();
                unidades = gson.fromJson(respuesta.getContenido(), tipoLista);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return unidades;
    }
    
    //Implementacion para editar una unidad existente
     public static Mensaje editarUnidad(Unidad unidad){
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_wS+"unidad/editarUnidad";
        Gson gson = new Gson();
        try{
            String parametros = gson.toJson(unidad);
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
    
    //Implementacion para dar de baja una unidad (No la elimina de la base de datos) 
    public static Mensaje darBaja(Unidad unidad){
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_wS+"unidad/darBaja";
        Gson gson = new Gson();
        try{
            String parametros = gson.toJson(unidad);
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
