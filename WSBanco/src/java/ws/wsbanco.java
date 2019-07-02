/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author carlos
 */
@WebService(serviceName = "wsbanco")
public class wsbanco {

PreparedStatement pst = null;
            ResultSet rst = null;
            Connection conndbc = null;
            dbconexion dbc = new dbconexion();
    
    //consulta un select a la base de datos
    private double obtenercotizacion(String fecha){
        try {
            conndbc = dbc.databaseConn();
            String sql = "SELECT *FROM cotizacion where fecha='"+fecha+"'";
            pst = conndbc.prepareStatement(sql);
            rst = pst.executeQuery();
            while(rst.next()){
                int id_cotizacion = Integer.parseInt(rst.getString("id_cotizacion"));
                String fecha_cotizacion = rst.getString("fecha");
                double valor = Double.parseDouble(rst.getString("valor"));
                return valor;
            }

            
        } catch (SQLException ex) {
            Logger.getLogger(wsbanco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    //hace un insert a la base de datos 
    private void insertarcotizacion(String fecha,Double valor) throws SQLException{

        String sql ="INSERT INTO cotizacion (fecha,valor) VALUES('"+fecha+"',"+valor+")";
        Statement statement = conndbc.createStatement ();
        statement.executeUpdate (sql);
    }
    

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getCotizacion")
    public Double getCotizacion(@WebParam(name = "fecha") String fecha) {
        Double resultado = obtenercotizacion(fecha);
        return resultado;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "setCotizacion")
    public Boolean setCotizacion(@WebParam(name = "fecha") String fecha, @WebParam(name = "valor") double valor) throws SQLException {
        insertarcotizacion(fecha,valor);
        return true;
    }

}
