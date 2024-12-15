/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Cliente;
import pojo.Colaborador;
import pojo.Mensaje;

/**
 *
 * @author Jossellin
 */
public class ImpCliente {
    
    //registrar cliente
    public static Mensaje registrarCliente(Cliente cliente){

        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();

                if(conexionBD!=null){
            try{
                int filasAfectadas=conexionBD.insert("cliente.registrarCliente", cliente);
                conexionBD.commit();
                if(filasAfectadas>0){
                    msj.setError(false);
                    msj.setMensaje("El cliente " + cliente.getNombre() + " " 
                    +cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno() 
                    +", fue resgistrado con éxito.");
                    
                }else{
                    msj.setError(true);
                    msj.setMensaje("El colaborador no pudo ser registrado");
                }
            }catch (Exception e){
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            }
        }else{
            msj.setError(true);
            msj.setMensaje("Por le momento el servicio no esta disponible.");
        }
        return msj;

    }
    
    
    //editar cliente
    public static Mensaje editarCliente(Cliente cliente){
        Mensaje respuesta = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD != null){
            try {
                int editado = conexionBD.update("cliente.editar", cliente);
                conexionBD.commit();
                if(editado > 0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Información del cliente: " + cliente.getNombre() + " " 
                        + cliente.getApellidoMaterno() + " " + cliente.getApellidoPaterno() + " "
                        + "se actualizo exitosamente.");
                }else {
                    respuesta.setError(true);
                    respuesta.setMensaje("No es posible editar la información");
                    
                }
                
            } catch (Exception e){  
                    respuesta.setError(true);
                    respuesta.setMensaje(e.getMessage());
            }
        }else{
                    respuesta.setError(true);
                    respuesta.setMensaje("Por el momento no se puede editar la información.");
        }
        return respuesta;
    }
    
    
}
