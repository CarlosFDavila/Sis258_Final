package Banco;

import Factura.Factura;
import Cessa.ICessa;
import cotesmodulos.cotesinterface;
import cotesmodulos.cotesinterfaceHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
//librerias corba

import java.util.Scanner;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import Cotes.facturasImpl;

public class ServidorBanco 
    extends UnicastRemoteObject
    implements IOperacionesEmpresa
	 
{
    ServidorBanco() throws java.rmi.RemoteException{
	super();
    }
    
    public Factura[] calcular(int idcliente) throws RemoteException {
        ICessa cessa;
        try {
        cessa=(ICessa)Naming.lookup("rmi://localhost/Cessa");
        //Factura[] FacturasPendientesCessa=cessa.pedientes(idcliente);
        Factura[] FacturasPendientesCessa = cessa.pedientes(idcliente);
        String [] pendientesElapas=llamarElapas("fac-"+String.valueOf(idcliente)).split(",");
        Factura[] FacturasPendientesElapas=new Factura[pendientesElapas.length];
        ///corba
        String [] pendientesCotes=llamarCotes(idcliente).split(",");
        Factura[] FacturasPendientesCotes=new Factura[pendientesCotes.length];
        ///
        //Factura[] FacturasPendientes = new Factura[ pendientesElapas.length ];
        Factura[] FacturasPendientes = new Factura[ FacturasPendientesCessa.length + pendientesElapas.length + pendientesCotes.length];
        int i=0;
        for (String f:pendientesElapas )
        {
            String[] factu=f.split("-");
            int IdFactura=Integer.parseInt(factu[0]);
            double monto=Integer.parseInt(factu[1]);
            FacturasPendientesElapas[i]=new Factura("Elapas",IdFactura,monto);
            i++;
        }
        int j=0;
        for (String f:pendientesCotes )
        {
            String[] factu=f.split("-");
            int IdFactura=Integer.parseInt(factu[0]);
            double monto=Integer.parseInt(factu[1]);
            FacturasPendientesCotes[j]=new Factura("Cotes",IdFactura,monto);
            j++;
        }
        System.arraycopy( FacturasPendientesCessa, 0, FacturasPendientes, 0, FacturasPendientesCessa.length );
        System.arraycopy( FacturasPendientesElapas, 0, FacturasPendientes, FacturasPendientesCessa.length, FacturasPendientesElapas.length );
        System.arraycopy( FacturasPendientesCotes, 0, FacturasPendientes,FacturasPendientesCessa.length + FacturasPendientesElapas.length, FacturasPendientesCotes.length);
        return FacturasPendientes; 
        }
        catch (Exception e){
	    e.printStackTrace();
	return null;
        }
    }    
    public String pagar(Factura[] facturas) throws RemoteException {
        ICessa cessa;
        try 
        {
        cessa=(ICessa)Naming.lookup("rmi://localhost/Cessa");  
        String empresa="";
        int id_factura=0;
        for (Factura f:facturas)
        {
            id_factura=f.getIdFactura();
            empresa=f.getEmpresa();
            if (empresa.equals("Cessa"))
            {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
                //pagarcessa
                cessa.pagar(id_factura);
            }
            if (empresa.equals("Elapas"))
            {
                //pagar elapas
                String reselapas = llamarElapas("Pagar-"+id_factura);
            }
            if (empresa.equals("Cotes")){
                //pagar cotes
                pagarCotes(id_factura);
            }
            
        return "Se pago con exito";
        //return " ";
        }}
        catch (Exception e){
	    e.printStackTrace();
	return null;
        }
        return null;
        
    }
    
    
    
    public static void main(String args[]) { 
	try {
	    ServidorBanco Banco;
	    //LocateRegistry.createRegistry(1099);
	    Banco=new ServidorBanco(); 
	    Naming.bind("Operaciones", Banco); 
            System.out.println("El servidor esta listo\n");
        }
	catch (Exception e){
	    e.printStackTrace();
	}
    } 

    private String llamarElapas(String cadena) {
                int port = 5001; // puerto de comunicacion
        try{
            Socket client = new Socket("localhost", port); //conectarse al socket
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            toServer.println(cadena);  //mandar alservidor 
            String result = fromServer.readLine();  // devolver del servidor
            return result;
                    }
        catch(IOException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    private String llamarCotes(int id_cliente){
        try {
            String[] args = null;
            ORB orb = ORB.init(args ,null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            
            cotesinterface facturasImpl = cotesinterfaceHelper.narrow(ncRef.resolve_str("FacturasCotes"));
            for (;;) {//ciclo infinito
                //String response = fiboImpl.generar(numero);
                String response = facturasImpl.facturaspendientes(id_cliente);
                return response;
            }             
                    
            } catch (InvalidName | NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName e) {
            System.out.println("Error: " + e);
            e.printStackTrace(System.out);
            }
                //return "2200-30,3200-25,4540-48";
                return null;
    }
    
    private void pagarCotes(int id_factura){
            try {
            String[] args = null;
            ORB orb = ORB.init(args ,null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            
            cotesinterface facturasImpl = cotesinterfaceHelper.narrow(ncRef.resolve_str("FacturasCotes"));
            for (;;) {//ciclo infinito
                facturasImpl.pagar(id_factura);
                break;
            }             
                    
            } catch (InvalidName | NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName e) {
            System.out.println("Error: " + e);
            e.printStackTrace(System.out);
            }
    }



    
}


