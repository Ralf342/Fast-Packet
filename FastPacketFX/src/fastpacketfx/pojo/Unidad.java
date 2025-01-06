package fastpacketfx.pojo;

public class Unidad {
    private Integer idUnidad;
    private String vin;
    private String nii;
    private String anio;
    private String modelo;
    private String marca;
    private String motivo;
    private Integer idTipoUnidad;
    private Integer idColaborador;
    
    //Para los alias
    private String conductor;
    private String tipo;

    public Unidad() {
    }

    public Unidad(Integer idUnidad, String vin, String nii, String anio, String modelo, String marca, String motivo, Integer idTipoUnidad, Integer idColaborador, String conductor, String tipo) {
        this.idUnidad = idUnidad;
        this.vin = vin;
        this.nii = nii;
        this.anio = anio;
        this.modelo = modelo;
        this.marca = marca;
        this.motivo = motivo;
        this.idTipoUnidad = idTipoUnidad;
        this.idColaborador = idColaborador;
        this.conductor = conductor;
        this.tipo = tipo;
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getNii() {
        return nii;
    }

    public void setNii(String nii) {
        this.nii = nii;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getIdTipoUnidad() {
        return idTipoUnidad;
    }

    public void setIdTipoUnidad(Integer idTipoUnidad) {
        this.idTipoUnidad = idTipoUnidad;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String toString(){
        return "- " + marca + " " +modelo;
    }
   
}
