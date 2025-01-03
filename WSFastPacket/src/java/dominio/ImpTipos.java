package dominio;

import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.RolEmpleado;

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
}
