/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author Jossellin
 */
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

    public Envio() {
    }

    public Envio(Integer numeroDeGuia, float costo, String destino, String ciudad, String estado, String calle, String colonia, Integer numero, Integer codigoPostal, Integer idCliente) {
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
    
    
    
}
