/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import AccesoDatos.Conexion;
import Negocio.EAutos;
import Negocio.EUsuarios;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Autos {
    
    Conexion objConex = new Conexion();
    //es un comentario
    
    CallableStatement stmt;
    ResultSet rs;
    Connection connect = null;
    
    public Autos(){
        connect = objConex.getConexion();
    }
    
    public ArrayList getAll(){
        ArrayList arrayList = new ArrayList();
            
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_listar_auto()");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                EAutos objAut = new EAutos();
                objAut.setId(rs.getInt("id"));
                objAut.setModeloId(rs.getInt("id_modelo"));
                objAut.setTipoModelo(rs.getString("role"));
                objAut.setPlaca(rs.getString("placa"));
                objAut.setPrecio(rs.getDouble("precio"));
                objAut.setDescripcion(rs.getString("descripcion"));
                arrayList.add(objAut);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    
    
    
    public ArrayList searchById(int id){
        ArrayList arrayList = new ArrayList();
            
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_buscar_auto_id(?)");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                EAutos objAut = new EAutos();
                objAut.setId(rs.getInt("id"));
                objAut.setModeloId(rs.getInt("id_modelo"));
                objAut.setTipoModelo(rs.getString("role"));
                objAut.setPlaca(rs.getString("placa"));
                objAut.setPrecio(rs.getDouble("precio"));
                objAut.setDescripcion(rs.getString("descripcion"));

                arrayList.add(objAut);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
//    
    public ArrayList searchByName(String name){
        ArrayList arrayList = new ArrayList();
            
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_buscar_auto_nombre(?)");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                EAutos objAut = new EAutos();
                objAut.setId(rs.getInt("id"));
                objAut.setModeloId(rs.getInt("id_modelo"));
                objAut.setPlaca(rs.getString("placa"));
                objAut.setPrecio(rs.getDouble("precio"));
                objAut.setDescripcion(rs.getString("descripcion"));

                arrayList.add(objAut);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
//    
    public String create(EAutos objAutos){
        String message = "";
        
        try{
            
            stmt = connect.prepareCall("call sp_registrar_auto(?, ?, ?, ?, ?)");
            stmt.setInt(1, objAutos.getModeloId());
            stmt.setString(2, objAutos.getPlaca());
            stmt.setBigDecimal(3, new BigDecimal(objAutos.getPrecio()));
            stmt.setString(4, objAutos.getDescripcion());
            stmt.registerOutParameter(5, Types.VARCHAR, 100);
            stmt.executeUpdate();
            message = stmt.getString(5);
                    
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }
//    
    public String update(EAutos objAuto){
        String message = "";
        
        try{
            
            stmt = connect.prepareCall("call sp_actualizar_auto(?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, objAuto.getId());
            stmt.setInt(2, objAuto.getModeloId());
            stmt.setString(3, objAuto.getPlaca());
            stmt.setBigDecimal(4, new BigDecimal(objAuto.getPrecio()));
            stmt.setString(5, objAuto.getDescripcion());
            stmt.registerOutParameter(6, Types.VARCHAR, 100);
            stmt.executeUpdate();
            message = stmt.getString(6);
                    
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }
//    
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
//                objUsu.setToken(rs.getString("token"));
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
//            stmt.setString(2, objUsuario.getToken());
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
//                objUsu.setToken(rs.getString("token"));
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
