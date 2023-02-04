/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import AccesoDatos.Conexion;
import Negocio.EClientes;
import Negocio.EComboBox;
import Negocio.ERoles;
import Negocio.EVendedor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Usuario
 */
public class Vendedor {
    
    Conexion objConex = new Conexion();
    CallableStatement stmt;
    ResultSet rs;
    Connection connect = null;
    
    public Vendedor(){
        connect = objConex.getConexion();
    }
    
    public ArrayList getAll(){
        ArrayList arrayList = new ArrayList();
            
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_listar_vendedor()");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                EVendedor objVen = new EVendedor();
                objVen.setId(rs.getInt("id"));
                objVen.setDni(rs.getString("dni"));
                objVen.setNombres(rs.getString("nombres"));
                objVen.setApellidos(rs.getString("apellidos"));
                objVen.setTelefono(rs.getString("telefono"));
                arrayList.add(objVen);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    public ArrayList searchById(int id){
        ArrayList arrayList = new ArrayList();
            
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_buscar_vendedor(?)");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                EVendedor objVen = new EVendedor();
                objVen.setId(rs.getInt("id"));
                objVen.setDni(rs.getString("dni"));
                objVen.setNombres(rs.getString("nombres"));
                objVen.setApellidos(rs.getString("apellidos"));
                objVen.setTelefono(rs.getString("telefono"));
                arrayList.add(objVen);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    public ArrayList searchByName(String name){
        ArrayList arrayList = new ArrayList();
        
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_buscar_vendedor_nombre(?)");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                EVendedor objVen = new EVendedor();
                objVen.setId(rs.getInt("id"));
                objVen.setDni(rs.getString("dni"));
                objVen.setNombres(rs.getString("nombres"));
                objVen.setApellidos(rs.getString("apellidos"));
                objVen.setTelefono(rs.getString("telefono"));
                arrayList.add(objVen);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    public String create(EVendedor objVen){
        String message = "";
        
        try{
            
            stmt = connect.prepareCall("call sp_registrar_vendedor(?, ?, ?, ?, ?)");
            stmt.setString(1, objVen.getDni());
            stmt.setString(2, objVen.getNombres());
            stmt.setString(3, objVen.getApellidos());
            stmt.setString(4, objVen.getTelefono());
            stmt.registerOutParameter(5, Types.VARCHAR, 100);
            stmt.executeUpdate();
            message = stmt.getString(5);
                    
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }
    
    public String update(EVendedor objVen){
        String message = "";
        
        try{
            
            stmt = connect.prepareCall("call sp_actualizar_vendedor(?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, objVen.getId());
            stmt.setString(2, objVen.getDni());
            stmt.setString(3, objVen.getNombres());
            stmt.setString(4, objVen.getApellidos());
            stmt.setString(5, objVen.getTelefono());
            stmt.registerOutParameter(6, Types.VARCHAR, 100);
            stmt.executeUpdate();
            message = stmt.getString(6);
                    
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }
    
    public ComboBoxModel fillCombobox(){
        DefaultComboBoxModel comboBox = new DefaultComboBoxModel();
        
        ArrayList arrayList = this.getAll();
        for (int i = 0; i < arrayList.size(); i++) {
            EVendedor vendedor = (EVendedor)arrayList.get(i);
            comboBox.addElement(new EComboBox(vendedor.getId(), vendedor.getNombres()));
        }
        
        return comboBox;
    }
    
}
