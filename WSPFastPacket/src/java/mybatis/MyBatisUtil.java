/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybatis;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author Jossellin
 */
public class MyBatisUtil {
    private static final String RUTA = "mybatis/mybatisconfig.xml";
    private static final String AMBIENTE = "desarrollo";
    
    public static SqlSession obtenerConexion(){
        SqlSession conexion = null;
        try {
            Reader reader = Resources.getResourceAsReader(RUTA);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader,AMBIENTE);
            conexion = sqlMapper.openSession();
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return conexion;
    }
}
