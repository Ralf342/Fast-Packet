/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.List;
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
        Mensaje msj= new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD!=null){
            try {
                int filasAfectadas= conexionBD.update("cliente.editarCliente", cliente);
                conexionBD.commit();
                if(filasAfectadas>0){
                    msj.setError(false);
                    msj.setMensaje("El cliente "+cliente.getNombre()+" "+cliente.getApellidoPaterno()+" "+cliente.getApellidoMaterno()+", fue actualizado con exito.");
                }
            }catch(Exception e){
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            }
        }else{
            msj.setError(true);
            msj.setMensaje("Por el momento el servicio no esta disponible.");
        }
        return msj;
    }
    
    //eliminar cliente
    public static Mensaje eliminarCliente(Integer idCliente){
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
        if(conexionBD!=null){
            try{
                int filasAfectadas=conexionBD.delete("cliente.eliminarCliente", idCliente);
                conexionBD.commit();
                if(filasAfectadas>0){
                    msj.setError(false);
                    msj.setMensaje("El clienter ha sido borrado.");
                }else{
                    msj.setError(true);
                    msj.setMensaje("El cliente no pudo ser borrado");
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
    
    //buscar cliente por correo
    public static List<Cliente> buscarClientePorCorreo(String correo){
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
            List<Cliente> clientes = conexionBD.selectList("cliente.buscarClientePorCorreo", correo);
        return clientes;
    }
    
}
