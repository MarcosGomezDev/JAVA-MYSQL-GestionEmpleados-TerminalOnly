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
public class ConsultarBD {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Connection conexionMySQL = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/GestionEmpleados2122";
            conexionMySQL = DriverManager.getConnection(url, "root", "");
            
            // Comprobamos si está en modo autocomit, en caso de no estarlo
            // lo activamos.
            if (!conexionMySQL.getAutoCommit()){
                conexionMySQL.setAutoCommit(true);
                System.out.println("AutoCommit establecido");
            }
            
            PreparedStatement ps = conexionMySQL.prepareStatement(""
                    + "SELECT * FROM empleados WHERE salario > 1500");
            ResultSet resultado = ps.executeQuery();
            imprimirDatos(resultado);
            
            conexionMySQL.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultarBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public static void imprimirDatos(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                System.out.println("Nombre: " + resultSet.getString("Nombre"));
                System.out.println("Apellidos: " + resultSet.getString("Apellidos"));
                System.out.println("Puesto: " + resultSet.getString("Puesto"));
                System.out.println("Salario: " + resultSet.getString("Salario") + "\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultarBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
