/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author joska_
 */
public class Colaborador {
     
    private Integer idColaborador;
    private String correo;
    private String contrasenia;
    private String curp;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer noPersonal;
    private Integer idRol;
    private String rol;
    private String fotoBase64;
    

    public Colaborador() {
    }

    public Colaborador(Integer idColaborador, String correo, String contrasenia, String curp, String nombre, String apellidoPaterno, String apellidoMaterno, Integer noPersonal, Integer idRol, String fotoBase64, String rol) {
        this.idColaborador = idColaborador;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.curp = curp;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.noPersonal = noPersonal;
        this.idRol = idRol;
        this.fotoBase64 = fotoBase64;
        this.rol = rol;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(Integer noPersonal) {
        this.noPersonal = noPersonal;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getFotoBase64() {
        return fotoBase64;
    }

    public void setFotoBase64(String fotoBase64) {
        this.fotoBase64 = fotoBase64;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

   
}
