package pojo;

/**
 *
 * @author Jossellin
 */
public class Envio {
    private Integer numeroDeGuia;
    private float costo;
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
    private String cliente;
    private String contacto_ciente;
    private String direccion_de_cliente;
    private String direccion_origen;

    public Envio() {
    }

    public Envio(Integer numeroDeGuia, float costo, String ciudadOrigen, String estadoOrigen, String calleOrigen, String coloniaOrigen, Integer numeroCasaOrigen, Integer codigoPostalOrigen, Integer idClienteDestino, String fechaModificacion, Integer idEstatus, Integer idUnidad, Integer idColaboradorModificacion, String destino, String estatus, String conductor, String cliente, String contacto_ciente, String direccion_de_cliente, String direccion_origen) {
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
        this.cliente = cliente;
        this.contacto_ciente = contacto_ciente;
        this.direccion_de_cliente = direccion_de_cliente;
        this.direccion_origen = direccion_origen;
    }

    public Integer getNumeroDeGuia() {
        return numeroDeGuia;
    }

    public void setNumeroDeGuia(Integer numeroDeGuia) {
        this.numeroDeGuia = numeroDeGuia;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getContacto_ciente() {
        return contacto_ciente;
    }

    public void setContacto_ciente(String contacto_ciente) {
        this.contacto_ciente = contacto_ciente;
    }

    public String getDireccion_de_cliente() {
        return direccion_de_cliente;
    }

    public void setDireccion_de_cliente(String direccion_de_cliente) {
        this.direccion_de_cliente = direccion_de_cliente;
    }

    public String getDireccion_origen() {
        return direccion_origen;
    }

    public void setDireccion_origen(String direccion_origen) {
        this.direccion_origen = direccion_origen;
    }

    
}