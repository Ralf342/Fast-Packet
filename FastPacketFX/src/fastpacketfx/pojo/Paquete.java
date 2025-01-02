package fastpacketfx.pojo;

/**
 *
 * @author edgar
 */
public class Paquete {
    private Integer idPaquete;
    private Float peso;
    private Float alto;
    private Float ancho;
    private Float profundidad;
    private String descripcion;
    private Integer numeroDeGuia;
    private Integer idUnidad;

    public Paquete() {
    }

    public Paquete(Integer idPaquete, Float peso, Float alto, Float ancho, Float profundidad, String descripcion, Integer numeroDeGuia, Integer idUnidad) {
        this.idPaquete = idPaquete;
        this.peso = peso;
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad = profundidad;
        this.descripcion = descripcion;
        this.numeroDeGuia = numeroDeGuia;
        this.idUnidad = idUnidad;
    }

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getAlto() {
        return alto;
    }

    public void setAlto(Float alto) {
        this.alto = alto;
    }

    public Float getAncho() {
        return ancho;
    }

    public void setAncho(Float ancho) {
        this.ancho = ancho;
    }

    public Float getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Float profundidad) {
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

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }
    
    
    
}

