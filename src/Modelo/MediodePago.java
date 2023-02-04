/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import AccesoDatos.Conexion;
import Negocio.EComboBox;
import Negocio.EMedioPago;
import Negocio.ERoles;
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

/**
 *
 * @author Usuario
 */
public class MediodePago {
    
    Conexion objConex = new Conexion();
    CallableStatement stmt;
    ResultSet rs;
    Connection connect = null;
    
    public MediodePago(){
        connect = objConex.getConexion();
    }
    
    public ArrayList getAll(){
        ArrayList arrayList = new ArrayList();
            
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_listar_medio_pago()");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                EMedioPago objMediopago = new EMedioPago();
                objMediopago.setId(rs.getInt("id"));
                objMediopago.setDescripcion(rs.getString("descripcion"));
                arrayList.add(objMediopago);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    public ArrayList searchById(int id){
        ArrayList arrayList = new ArrayList();
            
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_buscar_medio_pago(?)");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                EMedioPago objMediopago = new EMedioPago();
                objMediopago.setId(rs.getInt("id"));
                objMediopago.setDescripcion(rs.getString("descripcion"));
                arrayList.add(objMediopago);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    public ArrayList searchByName(String name){
        ArrayList arrayList = new ArrayList();
        
        try{
            
            stmt = connect.prepareCall("SELECT * FROM func_buscar_medio_pago_nombre(?)");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                EMedioPago objMediopago = new EMedioPago();
                objMediopago.setId(rs.getInt("id"));
                objMediopago.setDescripcion(rs.getString("descripcion"));
                arrayList.add(objMediopago);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arrayList;
    }
    
    public String create(EMedioPago objMediopago){
        String message = "";
        
        try{
            
            stmt = connect.prepareCall("call sp_registrar_medio_pago(?, ?)");
            stmt.setString(1, objMediopago.getDescripcion());
            stmt.registerOutParameter(2, Types.VARCHAR, 100);
            stmt.executeUpdate();
            message = stmt.getString(2);
                    
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }
    
    public String update(EMedioPago objMediopago){
        String message = "";
        
        try{
            
            stmt = connect.prepareCall("call sp_actualizar_medio_pago(?, ?, ?)");
            stmt.setInt(1, objMediopago.getId());
            stmt.setString(2, objMediopago.getDescripcion());
            stmt.registerOutParameter(3, Types.VARCHAR, 100);
            stmt.executeUpdate();
            message = stmt.getString(3);
                    
        }catch(SQLException ex){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return message;
    }
    
    public ComboBoxModel fillCombobox(){
        DefaultComboBoxModel comboBox = new DefaultComboBoxModel();
        
        ArrayList arrayList = this.getAll();
        for (int i = 0; i < arrayList.size(); i++) {
            EMedioPago medipago = (EMedioPago)arrayList.get(i);
            comboBox.addElement(new EComboBox(medipago.getId(), medipago.getDescripcion()));
        }
        
        return comboBox;
    }
    
}
