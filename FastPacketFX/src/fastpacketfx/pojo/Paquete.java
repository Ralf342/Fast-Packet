package fastpacketfx.pojo;

/**
 *
 * @author edgar
 */
public class Paquete {
    private Integer idPaquete;
    private float peso;
    private float alto;
    private float ancho;
    private float profundidad;
    private String descripcion;
    private Integer numeroDeGuia;

    public Paquete() {
    }

    public Paquete(Integer idPaquete, float peso, float alto, float ancho, float profundidad, String descripcion, Integer numeroDeGuia) {
        this.idPaquete = idPaquete;
        this.peso = peso;
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad = profundidad;
        this.descripcion = descripcion;
        this.numeroDeGuia = numeroDeGuia;
    }

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAlto() {
        return alto;
    }

    public void setAlto(float alto) {
        this.alto = alto;
    }

    public float getAncho() {
        return ancho;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    public float getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(float profundidad) {
        this.profundidad = profundidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNumeroDeGuia() {
        return numeroDeGuia;
    }

    public void setNumeroDeGuia(Integer numeroDeGuia) {
        this.numeroDeGuia = numeroDeGuia;
    }

}