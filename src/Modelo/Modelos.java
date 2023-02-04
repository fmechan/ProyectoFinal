/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import AccesoDatos.Conexion;
import Negocio.EComboBox;
import Negocio.EMarcas;
import Negocio.EModelos;
import Negocio.EUsuarios;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;


public class Modelos {
    
    Conexion objConex = new Conexion();
    
    CallableStatement stmt;
    ResultSet rs;
    Connection connect = null;
    
    public Modelos(){
        connect = objConex.getConexion();
    }
    
    public ArrayList getAll(){
        ArrayList arrayList = new ArrayList();
            
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_listar_modelo()");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                EModelos objMol = new EModelos();
                objMol.setId(rs.getInt("id"));
                objMol.setMarcaId(rs.getInt("id_marca"));
                objMol.setTipoMarca(rs.getString("role"));
                objMol.setNombres(rs.getString("nombre"));
                objMol.setColor(rs.getString("color"));

                arrayList.add(objMol);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    public ArrayList searchById(int id){
        ArrayList arrayList = new ArrayList();
            
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_buscar_modelo_id(?)");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                EModelos objMol = new EModelos();
                objMol.setId(rs.getInt("id"));
                objMol.setMarcaId(rs.getInt("id_marca"));
                objMol.setTipoMarca(rs.getString("role"));
                objMol.setNombres(rs.getString("nombre"));
                objMol.setColor(rs.getString("color"));

                arrayList.add(objMol);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    public ArrayList searchByName(String name){
        ArrayList arrayList = new ArrayList();
            
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_buscar_modelo_nombre(?)");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                EModelos objMol = new EModelos();
                objMol.setId(rs.getInt("id"));
                objMol.setMarcaId(rs.getInt("id_marca"));
                objMol.setNombres(rs.getString("nombre"));
                objMol.setColor(rs.getString("color"));

                arrayList.add(objMol);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    public String create(EModelos objModelo){
        String message = "";
        
        try{
            
            stmt = connect.prepareCall("call sp_registrar_modelo(?, ?, ?, ?)");
            stmt.setInt(1, objModelo.getMarcaId());
            stmt.setString(2, objModelo.getNombres());
            stmt.setString(3, objModelo.getColor());
            stmt.registerOutParameter(4, Types.VARCHAR, 100);
            stmt.executeUpdate();
            message = stmt.getString(4);
                    
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }
    
    public String update(EModelos objModelo){
        String message = "";
        
        try{
            
            stmt = connect.prepareCall("call sp_actualizar_modelo(?, ?, ?, ?, ?)");
            stmt.setInt(1, objModelo.getId());
            stmt.setInt(2, objModelo.getMarcaId());
            stmt.setString(3, objModelo.getNombres());
            stmt.setString(4, objModelo.getColor());
            stmt.registerOutParameter(5, Types.VARCHAR, 100);
            stmt.executeUpdate();
            message = stmt.getString(5);
                    
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }
    
    
        public ComboBoxModel fillCombobox(){
        DefaultComboBoxModel comboBox = new DefaultComboBoxModel();
        
        ArrayList arrayList = this.getAll();
        for (int i = 0; i < arrayList.size(); i++) {
            EModelos modelo = (EModelos)arrayList.get(i);
            comboBox.addElement(new EComboBox(modelo.getId(), modelo.getNombres()));
        }
        
        return comboBox;
    }
    
     
    
//    public ArrayList searchByEmail(String email){
//        ArrayList arrayList = new ArrayList();
//            
//        try{
//            
//            stmt = connect.prepareCall("SELECT * FROM func_buscar_usuario_color(?)");
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
