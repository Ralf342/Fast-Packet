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
public class Rol {
    private Integer idRol;
    private String tipo;

    public Rol() {
    }

    public Rol(Integer idRol, String tipo) {
        this.idRol = idRol;
        this.tipo = tipo;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}

