package ws;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author HP
 */
public class dbconexion {
    Connection Conn = null;
    String url = "jdbc:postgresql://localhost:5432/banco";
    String user = "postgres";
    String password = "46433845cfdlr";
    
    public Connection databaseConn()
    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbconexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(dbconexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Conn;
    }
    
    public static void main(String [] args){
        dbconexion conndatabase = new dbconexion();
        conndatabase.databaseConn();
    }
}