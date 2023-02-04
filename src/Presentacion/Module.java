/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

import Negocio.EClientes;
import Negocio.EMedioPago;
import Negocio.ERoles;

/**
 *
 * @author Usuario
 */
public class Module {
    
     public static final String titleMessage = "Sistema de Ventas de Carros";
    public static int userId;
    public static String userName;
    public static String status;
    public static String tipoRol;
    
    public static ERoles rol = new ERoles();
    public static EClientes cli = new EClientes();
    public static EMedioPago mediopago = new EMedioPago();
    public static int id;
    public static EForm formActive = new EForm();
    public static EFormCart itemCart = new EFormCart();
    public static boolean editCart;
    
    public static String serieNI = "NI01";
    public static String serieNS = "NS01";
    
    /****** CONFIGURACION DE CORREOS ***********/
    public static String serverHost = "smtp.gmail.com";
    public static String serverPort = "587";
    public static String userNameMail = "tucorreo@gmail.com";
    public static String passwordMail = "***********";
    
    public boolean isInteger(String numero){
        try{
            Integer.valueOf(numero);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    
}
