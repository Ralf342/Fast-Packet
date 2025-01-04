package fastpacketfx.modelo;

import fastpacketfx.pojo.RespuestaHTTP;
import fastpacketfx.utilidades.Constantes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ConexionWS {
    public static RespuestaHTTP peticionGET(String url){
        RespuestaHTTP respuesta = new RespuestaHTTP();
        try {
            URL urlDestino = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlDestino.openConnection();
            conexionHttp.setRequestMethod("GET");
            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);
            System.out.println("Codigo WS: "+codigoRespuesta);
            if(codigoRespuesta == HttpURLConnection.HTTP_OK){
                respuesta.setContenido(obtenerContenidoWS(conexionHttp.getInputStream()));
            }else{
                respuesta.setContenido("Código de respuesta HTTP: "+codigoRespuesta);
            }
        } catch (MalformedURLException e) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error el la dirección de conexión.");
        } catch (IOException io){
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: no se pudo realizar la solicitud correspondiente.");
        } 
        return respuesta;
    }
    
    public static RespuestaHTTP peticionPOST(String url, String parametros){
        RespuestaHTTP respuesta = new RespuestaHTTP();
        try {
            URL urlDestino = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlDestino.openConnection();
            conexionHttp.setRequestMethod("POST");
            conexionHttp.setRequestProperty("Content-Type", 
                    "application/x-www-form-urlencoded");
            conexionHttp.setDoOutput(true);
            OutputStream os = conexionHttp.getOutputStream();
            os.write(parametros.getBytes());
            os.flush();
            os.close();
            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);
            if(codigoRespuesta == HttpURLConnection.HTTP_OK){
                respuesta.setContenido(obtenerContenidoWS(conexionHttp.getInputStream()));
            }else{
                respuesta.setContenido("Código de respuesta HTTP: "+codigoRespuesta);
            }
        } catch (MalformedURLException e) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error el la dirección de conexión.");
        } catch (IOException io){
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: no se pudo realizar la solicitud correspondiente.");
        } 
        return respuesta;
    }
    
    public static RespuestaHTTP peticionPUT(String url, String parametros){
        RespuestaHTTP respuesta = new RespuestaHTTP();
        try {
            URL urlDestino = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlDestino.openConnection();
            conexionHttp.setRequestMethod("PUT");
            conexionHttp.setRequestProperty("Content-Type", 
                    "application/x-www-form-urlencoded");
            conexionHttp.setDoOutput(true);
            OutputStream os = conexionHttp.getOutputStream();
            os.write(parametros.getBytes());
            os.flush();
            os.close();
            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);
            if(codigoRespuesta == HttpURLConnection.HTTP_OK){
                respuesta.setContenido(obtenerContenidoWS(conexionHttp.getInputStream()));
            }else{
                respuesta.setContenido("Código de respuesta HTTP: "+codigoRespuesta);
            }
        } catch (MalformedURLException e) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error el la dirección de conexión.");
        } catch (IOException io){
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: no se pudo realizar la solicitud correspondiente.");
        } 
        return respuesta;
    }
    
   public static RespuestaHTTP peticionDELETE(String url, String parametros) {
    RespuestaHTTP respuesta = new RespuestaHTTP();
    try {
        URL urlDestino = new URL(url);
        HttpURLConnection conexionHttp = (HttpURLConnection) urlDestino.openConnection();
        conexionHttp.setRequestMethod("DELETE");
        conexionHttp.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conexionHttp.setDoOutput(parametros != null); // Solo usa OutputStream si hay parámetros

        if (parametros != null) {
            try (OutputStream os = conexionHttp.getOutputStream()) {
                os.write(parametros.getBytes());
                os.flush();
            }
        }
        int codigoRespuesta = conexionHttp.getResponseCode();
        respuesta.setCodigoRespuesta(codigoRespuesta);
        if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
            respuesta.setContenido(obtenerContenidoWS(conexionHttp.getInputStream()));
        } else {
            respuesta.setContenido("Código de respuesta HTTP: " + codigoRespuesta);
        }
    } catch (MalformedURLException e) {
        respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
        respuesta.setContenido("Error en la dirección de conexión.");
    } catch (IOException io) {
        respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
        respuesta.setContenido("Error: no se pudo realizar la solicitud correspondiente.");
    } 
    return respuesta;
}
    
     public static RespuestaHTTP peticionPOSTJson(String url, String parametros){
        RespuestaHTTP respuesta = new RespuestaHTTP();
        try {
            URL urlDestino = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlDestino.openConnection();
            conexionHttp.setRequestMethod("POST");
            conexionHttp.setRequestProperty("Content-Type", 
                    "application/json");
            conexionHttp.setDoOutput(true);
            OutputStream os = conexionHttp.getOutputStream();
            os.write(parametros.getBytes());
            os.flush();
            os.close();
            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);
            if(codigoRespuesta == HttpURLConnection.HTTP_OK){
                respuesta.setContenido(obtenerContenidoWS(conexionHttp.getInputStream()));
            }else{
                respuesta.setContenido("Código de respuesta HTTP: "+codigoRespuesta);
            }
        } catch (MalformedURLException e) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error el la dirección de conexión.");
        } catch (IOException io){
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: no se pudo realizar la solicitud correspondiente.");
        } 
        return respuesta;
    }
     
     public static RespuestaHTTP peticionPUTJson(String url, String parametros){
        RespuestaHTTP respuesta = new RespuestaHTTP();
        try {
            URL urlDestino = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlDestino.openConnection();
            conexionHttp.setRequestMethod("PUT");
            conexionHttp.setRequestProperty("Content-Type", 
                    "application/json");
            conexionHttp.setDoOutput(true);
            OutputStream os = conexionHttp.getOutputStream();
            os.write(parametros.getBytes());
            os.flush();
            os.close();
            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);
            if(codigoRespuesta == HttpURLConnection.HTTP_OK){
                respuesta.setContenido(obtenerContenidoWS(conexionHttp.getInputStream()));
            }else{
                respuesta.setContenido("Código de respuesta HTTP: "+codigoRespuesta);
            }
        } catch (MalformedURLException e) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error el la dirección de conexión.");
        } catch (IOException io){
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: no se pudo realizar la solicitud correspondiente.");
        } 
        return respuesta;
    }
    
    
    private static String obtenerContenidoWS(InputStream inputWS) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(inputWS));
        String inputLine;
        StringBuffer respuestaEntrada = new StringBuffer();
        while( (inputLine = in.readLine()) != null){
            respuestaEntrada.append(inputLine);
        }
        in.close();
        return respuestaEntrada.toString();
    }
    
    public static RespuestaHTTP peticionPUTImg (String url, byte[] parametros) {
    RespuestaHTTP respuesta = new RespuestaHTTP();
    try {
        // Convertir la URL
        URL urlDestino = new URL(url);
        HttpURLConnection conexionHttp = (HttpURLConnection) urlDestino.openConnection();
        
        // Configurar la solicitud como PUT
        conexionHttp.setRequestMethod("PUT");
        conexionHttp.setRequestProperty("Content-Type", "application/octet-stream");  // Enviar como datos binarios
        conexionHttp.setDoOutput(true);  // Para enviar datos en el cuerpo de la solicitud
        
        // Escribir los bytes de la foto en la salida
        OutputStream os = conexionHttp.getOutputStream();
        os.write(parametros);  // Escribir los bytes de la imagen
        os.flush();
        os.close();
        
        // Leer la respuesta
        int codigoRespuesta = conexionHttp.getResponseCode();
        respuesta.setCodigoRespuesta(codigoRespuesta);
        
        // Si la respuesta es OK, obtener el contenido
        if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
            respuesta.setContenido(obtenerContenidoWS(conexionHttp.getInputStream()));
        } else {
            respuesta.setContenido("Código de respuesta HTTP: " + codigoRespuesta);
        }
    } catch (MalformedURLException e) {
        respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
        respuesta.setContenido("Error en la dirección de conexión.");
    } catch (IOException io) {
        respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
        respuesta.setContenido("Error: no se pudo realizar la solicitud.");
    }
    return respuesta;
}

    
}
