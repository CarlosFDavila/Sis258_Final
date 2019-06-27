package Cliente;

import Factura.Factura;
import Banco.IOperacionesEmpresa;
import java.rmi.Naming;
import java.util.Scanner;



public class ClienteBanco{
    public static void main(String args[]){
	IOperacionesEmpresa operaciones;
      int idcliente;
      String pago="";
	try {
	    operaciones=(IOperacionesEmpresa)Naming.lookup("rmi://localhost/Operaciones");
            System.out.println("Introduzca IdCliente");
            Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
            idcliente = entradaEscaner.nextInt(); //Invocamos un método sobre un objeto Scanner
	    System.out.println("------------Facturas Pendientes -----------");
	    Factura [] pendientes=operaciones.calcular(idcliente);
            for(Factura f:pendientes)
            { System.out.print(f.getEmpresa()+"   ");
            System.out.print(f.getIdFactura()+"   ");
            System.out.println(f.getMonto());
            }
            
            if (pendientes.length!=0)
            {
                pago=operaciones.pagar(pendientes);
                System.out.println("Se llamo a pagar con exito "+pago);
            }
                System.out.println();
	}
	catch (Exception e){
	    e.printStackTrace();
	}
    }
}

