package fastpacketfx.pojo;

public class RespuestaHTTP {
    private Integer codigoRespuesta;
    private String contenido;

    public RespuestaHTTP() {
    }

    public RespuestaHTTP(Integer codigoRespuesta, String contenido) {
        this.codigoRespuesta = codigoRespuesta;
        this.contenido = contenido;
    }

    public Integer getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(Integer codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
