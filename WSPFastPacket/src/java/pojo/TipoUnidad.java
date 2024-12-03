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
public class TipoUnidad {
    private Integer idTipoUnidad;
    private String tipo;

    public TipoUnidad() {
    }

    public TipoUnidad(Integer idTipoUnidad, String tipo) {
        this.idTipoUnidad = idTipoUnidad;
        this.tipo = tipo;
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
