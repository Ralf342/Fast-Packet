package fastpacketfx.pojo;

public class RolEmpleado {
   private Integer idRol;
   private String tipo;

    public RolEmpleado() {
    }

    public RolEmpleado(Integer idRol, String tipo) {
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
   
   @Override
    public String toString(){
        return "- "+tipo;
    }
}
