package dominio;

import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Estatus;
import pojo.RolEmpleado;
import pojo.TipoUnidad;

public class ImpTipos {
    public static List<RolEmpleado> obtenerRoles(){
        List<RolEmpleado> roles = null;
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD !=null){
            try{
                roles = conexionBD.selectList("tipos.rolEmpleado");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return roles;
    }
    public static List<TipoUnidad> obtenerUnidades(){
        List<TipoUnidad> unidades = null;
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD !=null){
            try{
                unidades = conexionBD.selectList("tipos.tipoUnidad");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return unidades;
    }
    
    public static List<Estatus> obtenerEstatus(){
        List<Estatus> estatus = null;
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD !=null){
            try{
                estatus = conexionBD.selectList("tipos.estatus");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return estatus;
    }
}
