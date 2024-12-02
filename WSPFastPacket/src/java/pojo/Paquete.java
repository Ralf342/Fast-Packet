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
public class Paquete {
    private Integer idPaquete;
    private float peso;
    private float dimensiones;
    private String descripcion;
    private Integer numeroDeGuia;
    private Integer idUnidad;

    public Paquete() {
    }

    public Paquete(Integer idPaquete, float peso, float dimensiones, String descripcion, Integer numeroDeGuia, Integer idUnidad) {
        this.idPaquete = idPaquete;
        this.peso = peso;
        this.dimensiones = dimensiones;
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

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(float dimensiones) {
        this.dimensiones = dimensiones;
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
