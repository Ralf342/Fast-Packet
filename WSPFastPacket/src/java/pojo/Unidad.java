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
    private Integer vin;
    private String nii;
    private String modelo;
    private String marca;
    private String motivo;
    private Integer idTipoUnidad;

    public Unidad() {
    }

    public Unidad(Integer idUnidad, Integer vin, String nii, String modelo, String marca, String motivo, Integer idTipoUnidad) {
        this.idUnidad = idUnidad;
        this.vin = vin;
        this.nii = nii;
        this.modelo = modelo;
        this.marca = marca;
        this.motivo = motivo;
        this.idTipoUnidad = idTipoUnidad;
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Integer getVin() {
        return vin;
    }

    public void setVin(Integer vin) {
        this.vin = vin;
    }

    public String getNii() {
        return nii;
    }

    public void setNii(String nii) {
        this.nii = nii;
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
    
    
}
