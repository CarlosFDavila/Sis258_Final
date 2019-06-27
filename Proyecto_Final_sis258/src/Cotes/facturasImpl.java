/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cotes;

import cotesmodulos.cotesinterfacePOA;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 */
public class facturasImpl extends cotesinterfacePOA{

    @Override
    public String facturaspendientes(int numero) {
         PreparedStatement pst = null;
        ResultSet rst = null;
        String sql = "Select *from facturas where id_cliente="+numero;
        String aux="";
        try {
            Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/cotes","root", "46433845cfdlr"); 
            pst = conexion.prepareStatement(sql);
            rst = pst.executeQuery();
                while(rst.next()){
                String id_f = rst.getString("id_factura");
                String id_c = rst.getString("id_cliente");
                String monto = rst.getString("monto");
                String estado = rst.getString("estado");
                //DefaultTableModel dftable = (DefaultTableModel) TablaU.getModel();
                //Object obj[] = {apellidos,nombres,ci,fecha_de_nacimiento,genero};
                //dftable.addRow(obj);
                aux+=id_f+"-"+monto+",";
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(facturasImpl.class.getName()).log(Level.SEVERE, null, ex);
        }


        //return "2256-36,3216-26,4547-44";
        return aux;
    }

    @Override
    public void pagar(int idfactura) {
        PreparedStatement pst = null;
        ResultSet rst = null;
        String sql = "delete from facturas where id_factura = "+idfactura;
        //String sql = "delete from facturas where id_factura = 550";
            Connection conexion; 
        try {
            conexion = DriverManager.getConnection ("jdbc:mysql://localhost/cotes","root", "46433845cfdlr");
            pst = conexion.prepareStatement(sql);
            pst.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(facturasImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
