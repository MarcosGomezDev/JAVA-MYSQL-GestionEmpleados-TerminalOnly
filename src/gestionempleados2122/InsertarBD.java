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
public class InsertarBD {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Connection conexionMySQL = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            String urlConection = "jdbc:mysql://localhost/GestionEmpleados2122";
            conexionMySQL = DriverManager.getConnection(urlConection, "root", "");
            
            // Comprobamos si está en modo autocomit, en caso de no estarlo
            // lo activamos.
            if (!conexionMySQL.getAutoCommit()){
                conexionMySQL.setAutoCommit(true);
                System.out.println("AutoCommit establecido");
            }
            
            PreparedStatement ps = conexionMySQL.prepareStatement(
                "INSERT INTO empleados (codEmpleado, Nombre, Apellidos, Puesto, Salario)"
                        + "VALUES (1, 'Marcos', 'Gomez Medina', 'Desarrollador', 1900.00),"
                        + "(2, 'Manuel', 'Martinez', 'Contable', 1200.00),"
                        + "(3, 'Silvia', 'Marzo', 'Desarrolador', 1600.00),"
                        + "(4, 'Miguel', 'Cerezo', 'Analista', 1500.00),"
                        + "(5, 'Sara', 'Toro', 'Gerente', 2200.00);");
            ps.execute();
            
            System.out.println("Inserción completada");
            
            conexionMySQL.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InsertarBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
