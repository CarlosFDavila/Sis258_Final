/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cotes;

import cotesmodulos.cotesinterface;
import cotesmodulos.cotesinterfaceHelper;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

/**
 *
 * @author carlos
 */
public class ServidorCotes {
        public static void main(String args[]) {
        try {
            //crea instancia al ORB
            ORB orb = ORB.init(args, null);            
            //crea instancia a POA 
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));       
            //activa POA Manager
            rootpoa.the_POAManager().activate();       
            //instancia de clase Fibonacci
            facturasImpl factImpl = new facturasImpl();             
            //obtiene la referencia de la clase FiboImpl (servant)
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(factImpl);
            cotesinterface href = cotesinterfaceHelper.narrow(ref);
            
            //obtiene referencia
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt rootContext = NamingContextExtHelper.narrow(objRef);
             
            NameComponent nameComponent[] = rootContext.to_name("FacturasCotes");
            rootContext.rebind(nameComponent, href);
            
            //listo para recibir peticiones
            System.out.println("Servidor Cotes> listo y en espera");
            orb.run();            
        } catch (AdapterInactive | InvalidName | ServantNotActive | WrongPolicy | org.omg.CosNaming.NamingContextPackage.InvalidName | NotFound | CannotProceed e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }
}
