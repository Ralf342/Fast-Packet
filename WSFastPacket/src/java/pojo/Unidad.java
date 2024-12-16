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
public class Unidad {
    private Integer idUnidad;
    private String vin;
    private String nii;
    private String anio;
    private String modelo;
    private String marca;
    private String motivo;
    private Integer idTipoUnidad;
    private String tipo;

    public Unidad() {
    }

    public Unidad(Integer idUnidad, String vin, String nii, String anio, String modelo, String marca, String motivo, Integer idTipoUnidad, String tipo) {
        this.idUnidad = idUnidad;
        this.vin = vin;
        this.nii = nii;
        this.anio = anio;
        this.modelo = modelo;
        this.marca = marca;
        this.motivo = motivo;
        this.idTipoUnidad = idTipoUnidad;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}

