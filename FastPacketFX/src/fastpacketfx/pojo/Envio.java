package fastpacketfx.pojo;

public class Envio {
    private Integer numeroDeGuia;
    private float costo;
    private String destino;
    private String ciudad;
    private String estado;
    private String calle;
    private String colonia;
    private Integer numero;
    private Integer codigoPostal;
    private Integer idCliente;
    private String estatus;
    //por los alias
    private String cliente;
    private String contacto_ciente;
    private String direccion_de_cliente;
    private String direccion_origen;

    public Envio() {
    }

    public Envio(Integer numeroDeGuia, float costo, String destino, String ciudad, String estado, String calle, String colonia, Integer numero, Integer codigoPostal, Integer idCliente, String estatus, String cliente, String contacto_ciente, String direccion_de_cliente, String direccion_origen) {
        this.numeroDeGuia = numeroDeGuia;
        this.costo = costo;
        this.destino = destino;
        this.ciudad = ciudad;
        this.estado = estado;
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
        this.idCliente = idCliente;
        this.estatus = estatus;
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

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
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

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
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
