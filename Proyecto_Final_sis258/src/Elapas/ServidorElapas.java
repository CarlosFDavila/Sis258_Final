package Elapas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServidorElapas {
    PreparedStatement pst = null;
    ResultSet rst = null;
    Connection conndbc = null;
    dbconexion dbc = new dbconexion();
    ServidorElapas(){
        conndbc = dbc.databaseConn();
    }
    public String consulta_factura(String id) throws SQLException{
            String sql = "Select *from facturas where id_cliente="+id;
            pst = conndbc.prepareStatement(sql);
            rst = pst.executeQuery();
            String aux="";
            while(rst.next()){
                String id_f = rst.getString("id_factura");
                String id_c = rst.getString("id_cliente");
                String monto = rst.getString("monto");
                String estado = rst.getString("estado");

                aux+=id_f+"-"+monto+",";
            }
        
        return aux.substring(0, aux.length()-1);
    }
    
    public void pagar(int id_f) throws SQLException{
        String sql = "delete from facturas WHERE id_factura='"+id_f+"'";
        Statement statement = conndbc.createStatement ();
        statement.executeUpdate(sql);
    }
    
    public static void main(String[] args) throws SQLException {
        int port = 5001; // puerto en el que escuchara el socket
        String Respuesta="";
        try 
        {
            ServerSocket server = new ServerSocket(port); //instanciamos un servidor socket
            System.out.println("El servidor esta listo\n");
            Socket client;
            BufferedReader fromClient;  // buffer de lectura
            PrintStream toClient;       // stream para escritura
            while (true) {   // ciclo al infinito para elfuncionamiento del server
                client = server.accept(); // aceptala conexion
                fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
                String cadena;
                String resultado="";
                cadena = fromClient.readLine(); //cadena obtenida desde el lector
                toClient = new PrintStream(client.getOutputStream()); //prepara el objetopara devolver
                System.out.println("Comando Recibido"+cadena);
                if (cadena.substring(3, 4).equals("-")) {
                    String  comando = cadena.substring(0, 3);
                    
                    if (comando.equals("fac")) {
                        Integer idcliente  = Integer.parseInt(cadena.substring(4));
                        ServidorElapas se = new ServidorElapas();
                        resultado = se.consulta_factura(String.valueOf(idcliente));
                        
                        
                        if (idcliente==1)
                        {
                            Respuesta="2256-36,3216-26,4547-44";
                        }
                        if (idcliente==2)
                        {
                            Respuesta="1354-25,3252-17";
                        }
                        }
                    if (comando.equals("pag")) {
                            Respuesta="SI";
                    }
                    
                    toClient.flush();
                    toClient.println(resultado);
                } else {
                    if(cadena.substring(0, 6).equals("Pagar-")){
                        //delete facturas
                        int id_facturaelapas = Integer.parseInt(cadena.substring(6,7));
                        ServidorElapas serverelapas = new ServidorElapas();
                        serverelapas.pagar(id_facturaelapas);
                        toClient.flush();
                        toClient.println(resultado);
                        
                    }
                    else{
                        toClient.flush();
                        toClient.println("No se reconoce el comando");    
                    }

                }
            }

        }
        catch(IOException e

    
        ){
            System.out.println(e.getMessage());
    }
    }
}
