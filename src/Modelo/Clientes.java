/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import AccesoDatos.Conexion;
import Negocio.EClientes;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.swing.ComboBoxModel;
//import javax.swing.DefaultComboBoxModel;


public class Clientes {
    
    Conexion objConex = new Conexion();
    
    CallableStatement stmt;
    ResultSet rs;
    Connection connect = null;
    
    public Clientes(){
        connect = objConex.getConexion();
    }
    
    public ArrayList getAll(){
        ArrayList arrayList = new ArrayList();
            
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_listar_cliente()");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                EClientes objCli = new EClientes();
                objCli.setId(rs.getInt("id"));
                objCli.setTipo_cliente(rs.getString("Tipo_cliente"));
                objCli.setTipo_documento(rs.getString("Tipo_documento"));
                objCli.setNum_documento(rs.getString("Numero_documento"));
                objCli.setNombres(rs.getString("Nombres"));
                objCli.setApellidos(rs.getString("Apellidos"));
                objCli.setGenero(rs.getString("Genero"));
                objCli.setFecha_nac(rs.getString("Fecha_nacimiento"));
                objCli.setDireccion(rs.getString("Direccion"));
                objCli.setTelefono(rs.getString("telefono"));
                arrayList.add(objCli);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    public ArrayList searchById(int id){
        ArrayList arrayList = new ArrayList();
            
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_buscar_cliente(?)");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                EClientes objCli = new EClientes();
                objCli.setId(rs.getInt("id"));
                objCli.setTipo_cliente(rs.getString("Tipo_cliente"));
                objCli.setTipo_documento(rs.getString("Tipo_documento"));
                objCli.setNum_documento(rs.getString("Numero_documento"));
                objCli.setNombres(rs.getString("Nombres"));
                objCli.setApellidos(rs.getString("Apellidos"));
                objCli.setGenero(rs.getString("Genero"));
                objCli.setFecha_nac(rs.getString("Fecha_nacimiento"));
                objCli.setDireccion(rs.getString("Direccion"));
                objCli.setTelefono(rs.getString("telefono"));
                arrayList.add(objCli);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    public ArrayList searchByName(String name){
        ArrayList arrayList = new ArrayList();
            
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_buscar_cliente_nombre(?)");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                EClientes objCli = new EClientes();
                objCli.setId(rs.getInt("id"));
                objCli.setTipo_cliente(rs.getString("Tipo_cliente"));
                objCli.setTipo_documento(rs.getString("Tipo_documento"));
                objCli.setNum_documento(rs.getString("Numero_documento"));
                objCli.setNombres(rs.getString("Nombres"));
                objCli.setApellidos(rs.getString("Apellidos"));
                objCli.setGenero(rs.getString("Genero"));
                objCli.setFecha_nac(rs.getString("Fecha_nacimiento"));
                objCli.setDireccion(rs.getString("Direccion"));
                objCli.setTelefono(rs.getString("Telefono"));
                arrayList.add(objCli);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    public String create(EClientes objCli){
        String message = "";
        
        try{
            
            stmt = connect.prepareCall("call sp_registrar_cliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
           // stmt.setInt(1, objCli.getId());
            stmt.setString(1,objCli.getTipo_cliente());
            stmt.setString(2, objCli.getTipo_documento());
            stmt.setString(3, objCli.getNum_documento());
            stmt.setString(4, objCli.getNombres());
            stmt.setString(5, objCli.getApellidos());
            stmt.setString(6, objCli.getGenero());
          
            stmt.setDate(7, Date.valueOf(objCli.getFecha_nac()));
            stmt.setString(8, objCli.getDireccion());
            stmt.setString(9, objCli.getTelefono());
            stmt.registerOutParameter(10, Types.VARCHAR, 100);
            stmt.executeUpdate();
            message = stmt.getString(10);
                    
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }
    
    public String update(EClientes objCli){
        String message = "";
        
        try{
            
            stmt = connect.prepareCall("call sp_actualizar_Cliente(?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?)");
            stmt.setInt(1, objCli.getId());
            stmt.setString(2, objCli.getTipo_cliente());
            stmt.setString(3, objCli.getTipo_documento());
            stmt.setString(4, objCli.getNum_documento());
            stmt.setString(5, objCli.getNombres());
            stmt.setString(6, objCli.getApellidos());
            stmt.setString(7, objCli.getGenero());
            stmt.setDate(8, Date.valueOf(objCli.getFecha_nac()));
            stmt.setString(9, objCli.getDireccion());
            stmt.setString(10, objCli.getTelefono());
            stmt.registerOutParameter(11, Types.VARCHAR, 100);
            stmt.executeUpdate();
            message = stmt.getString(11);
            
         
                    
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }
    
//    public ArrayList searchByEmail(String email){
//        ArrayList arrayList = new ArrayList();
//            
//        try{
//            
//            stmt = connect.prepareCall("SELECT * FROM func_buscar_usuario_email(?)");
//            stmt.setString(1, email);
//            rs = stmt.executeQuery();
//            
//            if(rs.next()){
//                EUsuarios objUsu = new EUsuarios();
//                objUsu.setId(rs.getInt("id"));
//                objUsu.setRolId(rs.getInt("idrole"));
//                objUsu.setNombres(rs.getString("nombres"));
//                objUsu.setUsuario(rs.getString("usuario"));
//                objUsu.setClave(rs.getString("clave"));
//                objUsu.setCorreo(rs.getString("correo"));
//                objUsu.setEstado(rs.getString("estado"));
//                arrayList.add(objUsu);
//            }
//            
//        }catch(SQLException ex){
//            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return arrayList;
//    }
//    
//    public String updateToken(EUsuarios objUsuario){
//        String message = "";
//        
//        try{
//            
//            stmt = connect.prepareCall("call sp_actualizar_usuario_token(?, ?, ?)");
//            stmt.setInt(1, objUsuario.getId());
//            stmt.registerOutParameter(3, Types.VARCHAR, 100);
//            stmt.executeUpdate();
//            message = stmt.getString(3);
//                    
//        }catch(SQLException ex){
//            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return message;
//    }
//    
//    public ArrayList searchByToken(String token){
//        ArrayList arrayList = new ArrayList();
//            
//        try{
//            
//            stmt = connect.prepareCall("SELECT * FROM func_buscar_usuario_token(?)");
//            stmt.setString(1, token);
//            rs = stmt.executeQuery();
//            
//            if(rs.next()){
//                EUsuarios objUsu = new EUsuarios();
//                objUsu.setId(rs.getInt("id"));
//                objUsu.setRolId(rs.getInt("idrole"));
//                objUsu.setNombres(rs.getString("nombres"));
//                objUsu.setUsuario(rs.getString("usuario"));
//                objUsu.setClave(rs.getString("clave"));
//                objUsu.setCorreo(rs.getString("correo"));
//                objUsu.setEstado(rs.getString("estado"));
//                arrayList.add(objUsu);
//            }
//            
//        }catch(SQLException ex){
//            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return arrayList;
//    }
//    
//    public String changePassword(EUsuarios objUsuario){
//        String message = "";
//        
//        try{
//            
//            stmt = connect.prepareCall("call sp_actualizar_usuario_clave(?, ?, ?)");
//            stmt.setInt(1, objUsuario.getId());
//            stmt.setString(2, objUsuario.getClave());
//            stmt.registerOutParameter(3, Types.VARCHAR, 100);
//            stmt.executeUpdate();
//            message = stmt.getString(3);
//                    
//        }catch(SQLException ex){
//            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return message;
//    }
}
        
//        return comboBox;
//    }

