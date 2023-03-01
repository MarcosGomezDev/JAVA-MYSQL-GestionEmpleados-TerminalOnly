/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionempleados2122;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MarcosGM
 */
public class CrearBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Connection conexionMySQL = null;
        String tablaEmpleado;
        Statement query;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexionMySQL = DriverManager.getConnection(
                    "jdbc:mysql://localhost", "root", "");
            
            // Comprobamos si está en modo autocomit, en caso de no estarlo
            // lo activamos.
            if (!conexionMySQL.getAutoCommit()){
                conexionMySQL.setAutoCommit(true);
                System.out.println("AutoCommit establecido");
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error en conexión");
        }
        
        // Creamos la base de datos. En caso de existir saltara el catch 
        // que nos avisara por consola que ya existe.
        try {
            
            query = conexionMySQL.createStatement();
            query.executeUpdate("CREATE DATABASE GestionEmpleados2122");
            System.out.println("Base de datos creada");
            conexionMySQL.close();
            
        } catch (SQLException ex) {
            System.out.println("La base de datos ya existe.");
            try {
                conexionMySQL.close();
            } catch (SQLException ex1) {
                System.out.println("Fallo al cerrar la conexión.");
            }
        } catch (NullPointerException e) {
            System.out.println("No hay conexion con la base de datos.");
        }
        
        // Conexion directa con la base de datos.
        try {
            conexionMySQL = DriverManager.getConnection(
                    "jdbc:mysql://localhost/GestionEmpleados2122", "root", "");
            System.out.println("Conexion establecida");
        } catch (SQLException ex) {
            System.out.println("Error en conexión");
        }
            
        // String para la creacion de la tabla empleado
        tablaEmpleado = "CREATE TABLE IF NOT EXISTS empleados (\n"
                + "codEmpleado INT PRIMARY KEY,\n"
                + "Nombre VARCHAR (60),\n"
                + "Apellidos VARCHAR (120),\n"
                + "Puesto VARCHAR (50),\n"
                + "Salario FLOAT (6,2));";
        
        //Statement crearTabla;
        try {
            query = conexionMySQL.createStatement();
            query.executeUpdate(tablaEmpleado);
            System.out.println("Tabla creada.");
            
            conexionMySQL.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(CrearBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
