package pojo;

/**
 *
 * @author Jossellin
 */
public class Envio {
    private Integer numeroDeGuia;
    private Float costo;
    private String ciudadOrigen;
    private String estadoOrigen;
    private String calleOrigen;
    private String coloniaOrigen;
    private Integer numeroCasaOrigen;
    private Integer codigoPostalOrigen;
    private Integer idClienteDestino;
    private String fechaModificacion;
    private Integer idEstatus;
    private Integer idUnidad;
    private Integer idColaboradorModificacion;
    
    //por los alias
    private String destino;
    private String estatus;
    private String conductor;
    private String calle;
    private String numeroCasa;
    private String codigoPostal;
    private String ciudad;
    private String estado;

    public Envio() {
    }

    public Envio(Integer numeroDeGuia, Float costo, String ciudadOrigen, String estadoOrigen, String calleOrigen, String coloniaOrigen, Integer numeroCasaOrigen, Integer codigoPostalOrigen, Integer idClienteDestino, String fechaModificacion, Integer idEstatus, Integer idUnidad, Integer idColaboradorModificacion, String destino, String estatus, String conductor, String calle, String numeroCasa, String codigoPostal, String ciudad, String estado) {
        this.numeroDeGuia = numeroDeGuia;
        this.costo = costo;
        this.ciudadOrigen = ciudadOrigen;
        this.estadoOrigen = estadoOrigen;
        this.calleOrigen = calleOrigen;
        this.coloniaOrigen = coloniaOrigen;
        this.numeroCasaOrigen = numeroCasaOrigen;
        this.codigoPostalOrigen = codigoPostalOrigen;
        this.idClienteDestino = idClienteDestino;
        this.fechaModificacion = fechaModificacion;
        this.idEstatus = idEstatus;
        this.idUnidad = idUnidad;
        this.idColaboradorModificacion = idColaboradorModificacion;
        this.destino = destino;
        this.estatus = estatus;
        this.conductor = conductor;
        this.calle = calle;
        this.numeroCasa = numeroCasa;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.estado = estado;
    }

    public Integer getNumeroDeGuia() {
        return numeroDeGuia;
    }

    public void setNumeroDeGuia(Integer numeroDeGuia) {
        this.numeroDeGuia = numeroDeGuia;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getEstadoOrigen() {
        return estadoOrigen;
    }

    public void setEstadoOrigen(String estadoOrigen) {
        this.estadoOrigen = estadoOrigen;
    }

    public String getCalleOrigen() {
        return calleOrigen;
    }

    public void setCalleOrigen(String calleOrigen) {
        this.calleOrigen = calleOrigen;
    }

    public String getColoniaOrigen() {
        return coloniaOrigen;
    }

    public void setColoniaOrigen(String coloniaOrigen) {
        this.coloniaOrigen = coloniaOrigen;
    }

    public Integer getNumeroCasaOrigen() {
        return numeroCasaOrigen;
    }

    public void setNumeroCasaOrigen(Integer numeroCasaOrigen) {
        this.numeroCasaOrigen = numeroCasaOrigen;
    }

    public Integer getCodigoPostalOrigen() {
        return codigoPostalOrigen;
    }

    public void setCodigoPostalOrigen(Integer codigoPostalOrigen) {
        this.codigoPostalOrigen = codigoPostalOrigen;
    }

    public Integer getIdClienteDestino() {
        return idClienteDestino;
    }

    public void setIdClienteDestino(Integer idClienteDestino) {
        this.idClienteDestino = idClienteDestino;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Integer getIdColaboradorModificacion() {
        return idColaboradorModificacion;
    }

    public void setIdColaboradorModificacion(Integer idColaboradorModificacion) {
        this.idColaboradorModificacion = idColaboradorModificacion;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}