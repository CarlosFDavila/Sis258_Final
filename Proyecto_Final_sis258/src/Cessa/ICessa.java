/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cessa;

import Factura.Factura;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Carlos
 */
public interface ICessa extends Remote {
    public Factura[] pedientes(int idcliente) throws RemoteException;
    public String pagar(int id_factura) throws RemoteException;
    
}
