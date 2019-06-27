package Cessa;

import Factura.Factura;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.sql.Statement;


public class ServidorCessa 
    extends UnicastRemoteObject
    implements ICessa
	 
{
    ServidorCessa() throws java.rmi.RemoteException{
	super();
    }
    
    public Factura[] pedientes(int idcliente) throws RemoteException {
        if (idcliente==1)
        {
            Factura[] aux=new Factura[2];
            aux[0]=new Factura("Cessa",154,50.00);
            aux[1]=new Factura("Cessa",326,70.00);
            
            return aux;
        }
        else if (idcliente==2)
        {
            Factura[] aux=new Factura[2];
            aux[0]=new Factura("Cessa",325,60.00);
            aux[1]=new Factura("Cessa",457,80.00);
            
            return aux;
        }
        else if (idcliente==3)
        {
            Factura[] aux=new Factura[3];
            aux[0]=new Factura("Cessa",420,90.00);
            aux[1]=new Factura("Cessa",430,100.00);
            aux[2]=new Factura("Cessa",485,105.00);
            return aux;
        }
        else
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

        public String pagar(int id_factura) throws RemoteException {
            String sql = "delete *from facturas WHERE id_factura='"+id_factura+"'";
            String mongo = "consulta enmongodb";
            return "SI";
    }    
    
    
    
    public static void main(String args[]) { 
	try {
	    ServidorCessa cessa;
	    LocateRegistry.createRegistry(1099);
	    cessa=new ServidorCessa(); 
	    Naming.bind("Cessa", cessa); 
            System.out.println("El servidor esta listo\n");
        }
	catch (Exception e){
	    e.printStackTrace();
	}
    } 
   
}


