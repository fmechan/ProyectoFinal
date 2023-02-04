/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import AccesoDatos.Conexion;
import Negocio.EClientes;
import Negocio.EComboBox;
import Negocio.ERoles;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;


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
                objCli.setTipo_cliente(rs.getString("tipo_cliente"));
                objCli.setTipo_documento(rs.getString("tipo_documento"));
                objCli.setNum_documento(rs.getString("numero_documento"));
                objCli.setNombres(rs.getString("nombres"));
                objCli.setApellidos(rs.getString("apellidos"));
                objCli.setGenero(rs.getString("genero"));
                objCli.setFecha_nac(rs.getString("fecha_nacimiento"));
                objCli.setDireccion(rs.getString("direccion"));
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
                objCli.setTipo_cliente(rs.getString("tipo_cliente"));
                objCli.setTipo_documento(rs.getString("tipo_documento"));
                objCli.setNum_documento(rs.getString("numero_documento"));
                objCli.setNombres(rs.getString("nombres"));
                objCli.setApellidos(rs.getString("apellidos"));
                objCli.setGenero(rs.getString("genero"));
                objCli.setFecha_nac(rs.getString("fecha_nacimiento"));
                objCli.setDireccion(rs.getString("direccion"));
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
                objCli.setTipo_cliente(rs.getString("tipo_cliente"));
                objCli.setTipo_documento(rs.getString("tipo_documento"));
                objCli.setNum_documento(rs.getString("numero_documento"));
                objCli.setNombres(rs.getString("nombres"));
                objCli.setApellidos(rs.getString("apellidos"));
                objCli.setGenero(rs.getString("genero"));
                objCli.setFecha_nac(rs.getString("fecha_nacimiento"));
                objCli.setDireccion(rs.getString("direccion"));
                objCli.setTelefono(rs.getString("telefono"));
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
            stmt.setString(1, objCli.getTipo_cliente());
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
            
            stmt = connect.prepareCall("call sp_actualizar_cliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
    
    public ComboBoxModel fillCombobox(){
        DefaultComboBoxModel comboBox = new DefaultComboBoxModel();
        
        ArrayList arrayList = this.getAll();
        for (int i = 0; i < arrayList.size(); i++) {
            ERoles role = (ERoles)arrayList.get(i);
            comboBox.addElement(new EComboBox(role.getId(), role.getNombre()));
        }
        
        return comboBox;
    }
}
