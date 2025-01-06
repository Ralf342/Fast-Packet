package fastpacketfx.modelo.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fastpacketfx.modelo.ConexionWS;
import fastpacketfx.pojo.Cliente;
import fastpacketfx.pojo.ListaClientes;
import fastpacketfx.pojo.Mensaje;
import fastpacketfx.pojo.RespuestaHTTP;
import fastpacketfx.utilidades.Constantes;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

public class ClienteDAO {
    public static List<Cliente> obtenerClientes(){
        List<Cliente> clientes = null;
        String url = Constantes.URL_wS+"cliente/obtenerClientes";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        try{
            if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
                Gson gson = new Gson();
                Type tipoLista = new TypeToken<List<Cliente>>(){}.getType();
                clientes = gson.fromJson(respuesta.getContenido(), tipoLista);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return clientes;
    }
    
    public static Mensaje registrarClientes(Cliente cliente){
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_wS+"cliente/registrarCliente";
        Gson gson = new Gson();
        try{
            String parametros = gson.toJson(cliente);
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
    
    public static List<ListaClientes> obtenerListaClientes(){
        List<ListaClientes> clientes = null;
        String url = Constantes.URL_wS+"cliente/obtenerListaClientes";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        try{
            if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
                Gson gson = new Gson();
                Type tipoLista = new TypeToken<List<ListaClientes>>(){}.getType();
                clientes = gson.fromJson(respuesta.getContenido(), tipoLista);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return clientes;
    }
    
     public static List<Cliente> buscarCliente(String correo){
        List<Cliente> paquetes = null;
        String url = Constantes.URL_wS+"cliente/buscarClientePorCorreo/"+correo;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        try{
            if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
                Gson gson = new Gson();
                Type tipoLista = new TypeToken<List<Cliente>>(){}.getType();
                paquetes = gson.fromJson(respuesta.getContenido(), tipoLista);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return paquetes;
    }
    
     public static Mensaje borrarCliente(Integer idCliente){
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_wS+"cliente/eliminarCliente/" + idCliente;
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
}
