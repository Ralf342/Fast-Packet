package fastpacketfx.modelo.dao;

import com.google.gson.Gson;
import fastpacketfx.modelo.ConexionWS;
import fastpacketfx.pojo.Login;
import fastpacketfx.pojo.RespuestaHTTP;
import fastpacketfx.utilidades.Constantes;
import java.net.HttpURLConnection;

public class LoginDAO {
    public static Login iniciarSesion(String noPersonal, String contrasenia){
        Login respuesta = new Login();
        String url = Constantes.URL_wS+"Login/colaborador";
        String parametros = String.format("noPersonal=%s&contrasenia=%s", noPersonal, contrasenia);
        System.out.println("Impresion URL: "+url);
        RespuestaHTTP respuestaWS = ConexionWS.peticionPOST(url, parametros);
        if(respuestaWS.getCodigoRespuesta()== HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            respuesta = gson.fromJson(respuestaWS.getContenido(), Login.class);
        }else{
            respuesta.setError(true);
            respuesta.setMensaje("Lo sentimos, por el momento el servicio no esta disponible");
        }
        return respuesta;
    }
}
