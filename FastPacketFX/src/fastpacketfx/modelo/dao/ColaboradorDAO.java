package fastpacketfx.modelo.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import fastpacketfx.modelo.ConexionWS;
import fastpacketfx.pojo.Colaborador;
import fastpacketfx.pojo.Mensaje;
import fastpacketfx.pojo.RespuestaHTTP;
import fastpacketfx.pojo.RolEmpleado;
import fastpacketfx.utilidades.Constantes;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

public class ColaboradorDAO {
    public static List<Colaborador> obtenerColaboradores(){
        List<Colaborador> colaboradores = null;
        String url = Constantes.URL_wS+"colaborador/obtenerColaboradores";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        try{
            if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
                Gson gson = new Gson();
                Type tipoLista = new TypeToken<List<Colaborador>>(){}.getType();
                colaboradores = gson.fromJson(respuesta.getContenido(), tipoLista);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return colaboradores;
    }
    
    public static List <RolEmpleado> obtenerRolesColaborador(){
          List<RolEmpleado>roles =null;
          String url = Constantes.URL_wS+"tipo/obtener-roles";
          RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
          try{
              if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
                  Gson gson = new Gson();
                  Type tipoLista = new TypeToken<List<RolEmpleado>>(){}.getType();
                  roles =gson.fromJson(respuesta.getContenido(), tipoLista);
              }
          }catch (Exception e){
              e.printStackTrace();
          }
          return roles;
    }
    
     public static Mensaje registrarColaborador(Colaborador colaborador){
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_wS+"colaborador/registro";
        Gson gson = new Gson();
        try{
            String parametros = gson.toJson(colaborador);
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
     
     public static List <Colaborador> obtenerConductores(){
          List<Colaborador>colaborador =null;
          String url = Constantes.URL_wS+"colaborador/obtener-conductores";
          RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
          try{
              if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
                  Gson gson = new Gson();
                  Type tipoLista = new TypeToken<List<Colaborador>>(){}.getType();
                  colaborador = gson.fromJson(respuesta.getContenido(), tipoLista);
              }
          }catch (Exception e){
              e.printStackTrace();
          }
          return colaborador;
    }
     
    public static String subirFotoColaborador(Integer idColaborador, String fotoBase64) {
    String url = Constantes.URL_wS + "colaborador/subirFoto" + idColaborador;
   // RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
            try {
                // Crear un objeto que represente los datos a enviar
                Colaborador colaborador = new Colaborador();
                colaborador.setIdColaborador(idColaborador);
                colaborador.setFotoBase64(fotoBase64);

                // Serializar el objeto a JSON usando Gson
                Gson gson = new Gson();
                String json = gson.toJson(colaborador);

                RespuestaHTTP respuesta = ConexionWS.peticionPUT(url, json);

                // Validar la respuesta del servidor
                if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK || 
                    respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_CREATED) {
                    System.out.println("Foto guardada exitosamente para el colaborador con ID: " + idColaborador);
                } else {
                    System.out.println("Error al guardar la foto: " + respuesta.getContenido());
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }
    
     public static String obtenerFotoColaborador(Integer idColaborador) {
        String url = Constantes.URL_wS + "colaborador/obtenerFoto/" + idColaborador;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            return respuesta.getContenido();  // Devuelve la foto en base64
        }
        return null;
    }


}
