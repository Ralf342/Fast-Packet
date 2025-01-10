package dominio;

import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Cliente;
import pojo.ListaClientes;
import pojo.Mensaje;

/**
 *
 * @author Jossellin
 */
public class ImpCliente {
    
    //Implementacion de registrar cliente
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
                    msj.setMensaje("El Cliente no pudo ser registrado");
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
    
    
    //Implementacion de editar cliente
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
                else{
                    msj.setError(true);
                    msj.setMensaje("El Cliente no pudo ser actualizado");
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
    
    //Implementacion para eliminar cliente
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
                    msj.setMensaje("El cliente no ha podido ser borrado");
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
    
    //implementacion para buscar a los clientes por su correo
    public static List<Cliente> buscarClientePorCorreo(String correo){
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
            List<Cliente> cliente = conexionBD.selectList("cliente.buscarClientePorCorreo", correo);
        return cliente;
    }
    
    //Implementacion para Mostrar a todos los clientes 
    public static List<Cliente> obtenerCliente(){
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
                List<Cliente> cliente = conexionBD.selectList("cliente.buscarCliente");
        return cliente;
    }
    
    //Implementacion para listar los datos del cliente en el formulario de envio
    public static List<ListaClientes> obtenerListaClientes(){
        SqlSession conexionBD = MyBatisUtil.obtenerConexion();
                List<ListaClientes> cliente = conexionBD.selectList("cliente.buscarListaClientes");
        return cliente;
    }
    
}
